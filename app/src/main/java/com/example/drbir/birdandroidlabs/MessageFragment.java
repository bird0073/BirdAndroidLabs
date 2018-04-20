package com.example.drbir.birdandroidlabs;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by drbir on 4/3/2018.
 */

public class MessageFragment extends Fragment {
    protected static final String ACTIVITY_NAME = "basic";

    long messPosition;
    String messText;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.i(ACTIVITY_NAME, "onCreate");

        messPosition = getArguments().getLong("messPosition");
        messText = getArguments().getString("messText");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View gui = inflater.inflate(R.layout.activity_fragment_layout, null);
        TextView messTV = (TextView) gui.findViewById(R.id.messageTV);
        TextView positionTV = (TextView) gui.findViewById(R.id.idTV);
        messTV.setText(messText);

        Button delBtn = (Button)gui.findViewById(R.id.deleteBtn);
        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Delete Button", "Delete");
                getActivity().setResult(5, getActivity().getIntent());
                getActivity().finish();
            }
        });
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
