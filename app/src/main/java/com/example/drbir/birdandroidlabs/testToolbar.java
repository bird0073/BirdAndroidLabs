package com.example.drbir.birdandroidlabs;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import static com.example.drbir.birdandroidlabs.R.id.fab;
import static com.example.drbir.birdandroidlabs.R.id.tb_snack_message;

public class testToolbar extends AppCompatActivity {

    private String option1_String = "You selected item 1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_toolbar);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(myToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu m){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, m);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Yay! the Snackbar is working", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem mi){
        AlertDialog.Builder builder;
        AlertDialog dialog;
        LayoutInflater inflater = (testToolbar.this).getLayoutInflater();
        View buildView = inflater.inflate(R.layout.custom_alert_dialog, null);
        final EditText newMessage = (EditText) buildView.findViewById(R.id.custom_alert_et);
        switch (mi.getItemId()) {

//***************************************************************************************
            case R.id.action_one:
                Log.i("Toolbar", "Option 1 selected");
                Snackbar.make(findViewById(R.id.fab), option1_String, Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
                break;
//***************************************************************************************
            case R.id.action_two:
                Log.i("Toolbar", "Option 2 selected");
                builder = new AlertDialog.Builder(testToolbar.this);
                builder.setTitle(R.string.tb_alertTitle);
                // Add the buttons
                builder.setPositiveButton(R.string.tb_alertPositive, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                        Log.i("Positive","Positive" );
                        finish();
                    }
                });
                builder.setNegativeButton(R.string.tb_alertNegative, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        Log.i("Negative","Negative" );
                    }
                });
                // Create the AlertDialog
                dialog = builder.create();
                dialog.show();

                break;
//***************************************************************************************
            case R.id.action_three:
                Log.i("Toolbar", "Option 3 selected");
                builder = new AlertDialog.Builder(testToolbar.this);
                builder.setTitle(R.string.alert_Message);

                builder.setView(buildView);
                // Add the buttons
                builder.setPositiveButton(R.string.alert_positive, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                        Log.i("Positive","Positive" );
                        option1_String = newMessage.getText().toString();
                    }
                });
                builder.setNegativeButton(R.string.alert_negative, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        Log.i("Negative","Negative" );
                    }
                });
                // Create the AlertDialog
                dialog = builder.create();
                dialog.show();
                break;
//***************************************************************************************
            case R.id.about:
                Toast.makeText(testToolbar.this, "Version 1.0 by Dan Bird",
                        Toast.LENGTH_LONG).show();
                break;
        }
        return true;
    }
}
