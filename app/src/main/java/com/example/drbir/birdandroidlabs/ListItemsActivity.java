package com.example.drbir.birdandroidlabs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

public class ListItemsActivity extends Activity {
    protected static final String ACTIVITY_NAME = "ListItemsActivity";

    CharSequence toastText;
    int duration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);
        Log.i(ACTIVITY_NAME, "In onCreate()");

        //-------------------------------------------------------------------------------------

        final ImageButton imageBtn = (ImageButton) findViewById(R.id.imageButton);
            imageBtn.setOnClickListener(new View.OnClickListener() {
                static final int REQUEST_IMAGE_CAPTURE = 1;

                @Override
                public void onClick(View view) {
                    Intent photoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (photoIntent.resolveActivity(getPackageManager()) != null) {
                        startActivityForResult(photoIntent, REQUEST_IMAGE_CAPTURE);
                    }
                }
                protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                    if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
                        Bundle extras = data.getExtras();
                        Bitmap imageBitmap = (Bitmap) extras.get("data");
                        // setting the image to thumbnail
                        //findViewById(R.id.imageButton).setImageBitmap(imageBitmap);
                        //ImageView.setImageIcon(imageBitmap);
                        imageBtn.setImageBitmap(imageBitmap);
                    }
                }
            }); //end imageBtn onClickListner

        //-------------------------------------------------------------------------------------

        Switch theSwitch = (Switch) findViewById(R.id.switch1);
        theSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true) {
                    toastText = "Switch is On";
                    duration = Toast.LENGTH_SHORT;
                }
                else if(b == false){
                    toastText = "Switch is Off";
                    duration = Toast.LENGTH_LONG;
                }
                //Toast toast = Toast.makeText(this, toastText, duration); //=
                Toast toast = Toast.makeText(getApplicationContext(), toastText, duration);
                toast.show();
            }
        }); //end switch ChangeListener

        //-------------------------------------------------------------------------------------

        CheckBox checkBx = (CheckBox) findViewById(R.id.checkBox);
        checkBx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ListItemsActivity.this);
                // 2. Chain together various setter methods to set the dialog characteristics
                builder.setMessage(R.string.dialog_message) //Add a dialog message to strings.xml

                        .setTitle(R.string.dialog_title)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User clicked OK button
                                Intent resultIntent = new Intent(  );
                                resultIntent.putExtra("Response", "Here is my response");
                                setResult(Activity.RESULT_OK, resultIntent);
                                finish();
                            }
                        }) //positive response
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        }) //negative response
                        .show();
            } //checkbox onCheckedChanged
        }); //end checkBox ChangeListener

        //-------------------------------------------------------------------------------------

    } //onCreate



    @Override
    protected void onResume(){
        super.onResume();
        Log.i(ACTIVITY_NAME, "In onResume()");
    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.i(ACTIVITY_NAME, "In onStart()");
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.i(ACTIVITY_NAME, "In onPause()");
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.i(ACTIVITY_NAME, "In onStop()");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "In onDestroy()");
    }
}
