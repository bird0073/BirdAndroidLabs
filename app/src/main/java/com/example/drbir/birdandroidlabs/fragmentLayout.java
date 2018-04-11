package com.example.drbir.birdandroidlabs;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

public class fragmentLayout extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_fragment_layout);
        setContentView(R.layout.message_details);

        Bundle infoBundle = getIntent().getExtras();
        //Bundle infoBundle = getArguments();

        //final long number = infoBundle.getLong("messPosition");
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        MessageFragment messFrag = new MessageFragment();


        LayoutInflater inflater = (fragmentLayout.this).getLayoutInflater();
        View fragView = inflater.inflate(R.layout.activity_fragment_layout, null);

        Button delBtn = (Button)fragView.findViewById(R.id.deleteBtn);
            delBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("Delete", "Delete");
                    setResult(5, getIntent());
                    finish();
                }
            });

        messFrag.setArguments(infoBundle);

        ft.replace(R.id.frameLayout, messFrag);
        ft.commit();

    }
}
