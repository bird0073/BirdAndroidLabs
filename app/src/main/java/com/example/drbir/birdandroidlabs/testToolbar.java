package com.example.drbir.birdandroidlabs;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class testToolbar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_toolbar);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(myToolbar);
    }

    public boolean onCreateOptionsMenu(Menu m){
        getMenuInflater().inflate(R.menu.toolbar_menu, m);
        return true;
    }

    public boolean onOptionItemSelected(MenuItem mi){
        switch (mi.getItemId()) {

            case R.id.action_one:
                Log.i("Toolbar", "Option 1 selected");
                break;

            case R.id.action_two:
                Log.i("Toolbar", "Option 2 selected");
                break;

            case R.id.action_three:
                Log.i("Toolbar", "Option 3 selected");
                break;
        }
        return true;
    }
}
