package com.example.homework2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity  {
    ListFragment listFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            finish();
            return;
        }

        Bundle b1 = getIntent().getExtras();

        ArrayList<String> laps = b1.getStringArrayList("lapList");



        listFragment = (ListFragment) getSupportFragmentManager().findFragmentById(R.id.listFrag);

        listFragment.setText(laps);




    }

}

