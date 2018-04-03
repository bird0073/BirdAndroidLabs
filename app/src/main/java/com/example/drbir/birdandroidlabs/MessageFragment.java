package com.example.drbir.birdandroidlabs;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by drbir on 4/3/2018.
 */

public class MessageFragment extends Fragment {
    protected static final String ACTIVITY_NAME = "basic";

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.i(ACTIVITY_NAME, "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View gui = inflater.inflate(R.layout.activity_fragment_layout, null);
        //TextView tv =(TextView) gui.findViewById(R.id.fragment_to);
        //tv.setText("To:" + userText);
        return gui;
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.i(ACTIVITY_NAME, "onStart");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.i(ACTIVITY_NAME, "onPause");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.i(ACTIVITY_NAME, "onResume");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.i(ACTIVITY_NAME, "onStop");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "onDestroy");


    }
}
