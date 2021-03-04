package com.example.homework2;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class ListFragment extends Fragment {

    TextView lapList;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        lapList = (TextView) view.findViewById(R.id.lapList);


        return view;
    }

    public void setText(ArrayList<String> laps){
        String result = "";
        for(int i = 0; i< laps.size(); i++){
            Log.i("results", laps.get(i));
            result += "   " + (i+1) + "    " + laps.get(i)+"\n";
        }

        lapList.setText(result);
    }


}