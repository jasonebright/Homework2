package com.example.homework2;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class TimeFragment extends Fragment implements View.OnClickListener {
    Button start, stop, lap, reset, lapView;
    TextView time;
    private OnFragmentInteractionListener mListener;



    public TimeFragment() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_time, container, false);

        start = (Button) view.findViewById(R.id.start);
        stop = (Button) view.findViewById(R.id.stop);
        lap = (Button) view.findViewById(R.id.lap);
        reset = (Button) view.findViewById(R.id.reset);
        lapView = (Button) view.findViewById(R.id.lapView);
        time = (TextView) view.findViewById(R.id.time);

        start.setOnClickListener(this);
        stop.setOnClickListener(this);
        lap.setOnClickListener(this);
        reset.setOnClickListener(this);
        lapView.setOnClickListener(this);

        return view;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof OnFragmentInteractionListener){
            this.mListener= (OnFragmentInteractionListener) context;
        }else{
            throw new RuntimeException(context.toString()+" must implement OnFragmentInteractionListener");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == start.getId()){
            mListener.onButtonClicked(0);
        }
        else if(view.getId() == stop.getId()){
            mListener.onButtonClicked(1);
        }
        else if(view.getId() == lap.getId()){
            mListener.onButtonClicked(2);
        }
        else if(view.getId() == reset.getId()){
            mListener.onButtonClicked(3);
        }
        else if(view.getId() == lapView.getId()){
            mListener.onButtonClicked(4);
        }
    }

    public interface OnFragmentInteractionListener{
        void onButtonClicked(int infoID);
    }

}