package com.example.drbir.birdandroidlabs;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class ChatWindow extends Activity {

    ArrayList<String> chatListArray = new ArrayList<>();
    protected static final String ACTIVITY_NAME = "ChatWindow";
    //private ChatDatabaseHelper chatDBHelper = new ChatDatabaseHelper(this);
    private SQLiteDatabase db;
    private boolean isTablet;
    private Cursor results;
    private ListView chatList;
    private ChatDatabaseHelper chatDBHelper;
    private ChatAdapter messageAdapter;

    @Override
    protected void onDestroy(){
        Log.i(ACTIVITY_NAME, "onDestroy");
    super.onDestroy();
    db.close();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);

        isTablet = (findViewById(R.id.frameLayout) != null);

        //database****************************************************************************
        chatDBHelper = new ChatDatabaseHelper(this);
        db = chatDBHelper.getWritableDatabase();
        results = db.query(false, chatDBHelper.TABLE_NAME,
                new String[] { chatDBHelper.KEY_ID, chatDBHelper.KEY_MESSAGE},
                //"MESSAGE != ? ", new String [] {""},
                null, null,null, null, null, null);
        //Cursor results = db.rawQuery("SELECT * FROM ? ", new String[] {chatDBHelper.TABLE_NAME});
        results.moveToFirst();

        if(!results.isAfterLast()) {
            do{
                chatListArray.add(results.getString(results.getColumnIndex(chatDBHelper.KEY_MESSAGE)));
                Log.i(ACTIVITY_NAME, "SQL MESSAGE:" + results.getString(
                        results.getColumnIndex(ChatDatabaseHelper.KEY_MESSAGE)));
            }while(results.moveToNext());
        }else{ Log.i(ACTIVITY_NAME, "no entry here");}

        Log.i(ACTIVITY_NAME, "Column names: " + results.getColumnName(0)
                    + ", " + results.getColumnName(1));
        Log.i(ACTIVITY_NAME, "Cursor's column count= " + results.getColumnCount());

        //database****************************************************************************

        Button sendBtn = (Button) findViewById(R.id.sendButton);
        final EditText messageBx = (EditText) findViewById(R.id.messageBox);
        chatList = (ListView) findViewById(R.id.chatList);

        messageAdapter = new ChatAdapter(this);
        chatList.setAdapter(messageAdapter);

        sendBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //db
                ContentValues newData = new ContentValues();
                newData.put(chatDBHelper.KEY_MESSAGE, messageBx.getText().toString());
                db.insert(chatDBHelper.TABLE_NAME, null, newData);
                //db.insert(chatDBHelper.TABLE_NAME, chatDBHelper.KEY_MESSAGE, newData);
                //db
                chatListArray.add(messageBx.getText().toString());
                messageBx.setText("");
                messageAdapter.notifyDataSetChanged();
            }
        }); //end sendBtn onClickListner

        chatList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
                Bundle infoBundle = new Bundle();
                infoBundle.putLong("messPosition", messageAdapter.getItemId(position));
                infoBundle.putString("messText", chatListArray.get(position));

                if(isTablet){
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    MessageFragment messFrag = new MessageFragment();

                    messFrag.setArguments(infoBundle);

                    ft.replace(R.id.frameLayout, messFrag);
                    ft.addToBackStack("");
                    ft.commit();

                }else{
                    Intent intent = new Intent(ChatWindow.this, fragmentLayout.class);
                    intent.putExtras(infoBundle);
                    startActivityForResult(intent, 0);
                }
            }
            public void onActivityResult(int requestCode, int resultCode, Intent data){
                if(requestCode == 0){
                    if(resultCode == 5){ //delete selected
                        Log.i(ACTIVITY_NAME, "delete");
                        long dbPosition = messageAdapter.getItemId((int)getIntent().getExtras().getLong("messPosition"));
                        db.delete(chatDBHelper.TABLE_NAME,"WHERE ID ==" +dbPosition, null);

                        //messageAdapter
                        //getIntent().getExtras();
                    }
                }
            }
        }); //end chatList onClickListner
} //end onCreate

    public class ChatAdapter extends ArrayAdapter<String>{

        public ChatAdapter(Context ctx){
            super(ctx,0);
        }

        public int getCount(){
            //returns number of rows that will be in listView
            return chatListArray.size();
        }

        public String getItem(int position){
            //returns item to show in the list at the specific position
            return chatListArray.get(position);
        }

        public View getView(int position, View convertView, ViewGroup parent){
            //this returns the layout that will be positioned at the specified row in the list
            LayoutInflater inflater = ChatWindow.this.getLayoutInflater();
            View result = null;
            if(position%2==0)
                    result = inflater.inflate(R.layout.chat_row_incoming, null);
            else
                    result = inflater.inflate(R.layout.chat_row_outgoing, null);
            TextView message = (TextView) result.findViewById(R.id.message_text);
            message.setText(getItem(position));
            return result;

        }

        public long getItemId(int position){
            results.moveToPosition(position);
            Log.i("id podition" , ""+results.getLong(results.getColumnIndex("ID")));
            return results.getLong(results.getColumnIndex("ID"));
        }
    }
}