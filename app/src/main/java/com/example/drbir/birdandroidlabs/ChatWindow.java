package com.example.drbir.birdandroidlabs;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class ChatWindow extends Activity {

    ArrayList<String> chatListArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);


        Button sendBtn = (Button) findViewById(R.id.sendButton);
        final EditText messageBx = (EditText) findViewById(R.id.messageBox);
        ListView chatList = (ListView) findViewById(R.id.chatList);

        sendBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //final EditText textEmail = (EditText)findViewById(R.id.emailAddress);
                chatListArray.add(messageBx.getText().toString());
                messageBx.setText("");
            }

        }); //end sendBtn onClickListner


    }

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
            //step 9

            return parent;

        }

        public long getId(int position){

            return position;
        }
    }
}
