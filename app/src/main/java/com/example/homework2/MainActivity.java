package com.example.homework2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TimeFragment.OnFragmentInteractionListener{

    ListFragment listFragment;
    TimeFragment timeFragment;
    boolean running = false;
    Timer timer;
    MyAsyncTask asyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listFragment = (ListFragment) getSupportFragmentManager().findFragmentById(R.id.listFrag);
        timeFragment = (TimeFragment) getSupportFragmentManager().findFragmentById(R.id.timeFrag);
        timer = new Timer();
        asyncTask = new MyAsyncTask();

    }


    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt("second", timer.getSeconds());
        outState.putInt("minute", timer.getMinutes());
        outState.putInt("hour", timer.getHours());
        outState.putStringArrayList("lapList", timer.getTimeList());
        Log.i("Saved", ""+running);
        outState.putBoolean("running", running);

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        running = savedInstanceState.getBoolean("running");
        Log.i("Restore", ""+running);
        timer.setHr(savedInstanceState.getInt("hour"));
        timer.setMin(savedInstanceState.getInt("minute"));
        timer.setSec(savedInstanceState.getInt("second"));
        timer.setTimeList(savedInstanceState.getStringArrayList("lapList"));
        timer.calc();
        timeFragment.time.setText(String.format("%02d:%02d:%02d",timer.getHours(), timer.getMinutes(), timer.getSeconds()));
        if(running) {
            asyncTask = new MyAsyncTask();
            asyncTask.execute(1);
            Log.i("Start Timer", "here");
        }

    }


    @Override
    protected void onDestroy() {
        //checking if asynctask is still runnning
        if(asyncTask!=null && asyncTask.getStatus()== AsyncTask.Status.RUNNING){
            //cancel the task before destroying activity
            asyncTask.cancel(true);
            asyncTask= null;
        }
        super.onDestroy();
    }



    @Override
    protected void onResume() {
        super.onResume();
        if(getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE){
            timeFragment.lapView.setVisibility(View.GONE);
            listFragment.setText(timer.getTimeList());
        }
        else{
            timeFragment.lapView.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onButtonClicked(int infoID){

        if(infoID == 0){

                running = true;
                timeFragment.start.setVisibility(View.GONE);
                timeFragment.stop.setVisibility(View.VISIBLE);
                asyncTask = new MyAsyncTask();
                asyncTask.execute(1);


        }
        else if(infoID == 1){
            timeFragment.start.setVisibility(View.VISIBLE);
            timeFragment.stop.setVisibility(View.GONE);
            running = false;
        }
        else if(infoID == 2){
            timer.addTime(timeFragment.time.getText().toString());
            if(listFragment != null && listFragment.isInLayout()){
                listFragment.setText(timer.getTimeList());
            }
        }
        else if(infoID == 3){
            timeFragment.start.setVisibility(View.VISIBLE);
            timeFragment.stop.setVisibility(View.GONE);
            running = false;
            timer.reset();
            timer.calc();
            timeFragment.time.setText("00:00:00");
            if(listFragment != null && listFragment.isInLayout()){
                listFragment.lapList.setText("");
            }
        }
        else if(infoID == 4){
            Intent i1 = new Intent(this, ListActivity.class);
            i1.putExtra("lapList", timer.getTimeList());
            startActivity(i1);

        }




    }




    private class MyAsyncTask extends AsyncTask<Integer, Integer, Void> {



        @Override
        protected Void doInBackground(Integer... integers) {
            while(running){
                try{
                    if(isCancelled()) break;

                    timer.calc();
                    publishProgress(timer.getHours(), timer.getMinutes(), timer.getSeconds());

                    Thread.sleep(1000);
                }
                catch(Exception e){
                    e.printStackTrace();

                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values){
            super.onProgressUpdate(values);
            String curr_time = String.format("%02d:%02d:%02d", values[0], values[1], values[2]);
            timeFragment.time.setText(curr_time);
        }
    }
}

