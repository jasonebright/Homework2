package com.example.homework2;

import java.util.ArrayList;

public class Timer {

    private ArrayList<String> timeList;
    private int sec;
    private int min;
    private int hr;

    public Timer() {
        timeList = new ArrayList<String>();
        sec = 0;
        min = 0;
        hr = 0;
    }

    public ArrayList<String> getTimeList() {
        return timeList;
    }

    public int getSeconds() {
        return sec;
    }

    public int getMinutes() {
        return min;
    }

    public int getHours() {
        return hr;
    }

    public void setHr(int hr) {
        this.hr = hr;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setSec(int sec) {
        this.sec = sec;
    }

    public void setTimeList(ArrayList<String> timeList) {
        this.timeList = timeList;
    }

    public void calc() {
        sec++;
        if (sec == 60) {
            sec = 0;
            min++;
        }
        if (min == 60) {
            min = 0;
            hr++;
        }
    }

    public ArrayList<String> addTime(String time) {
        timeList.add(time);
        return timeList;
    }

    public void reset() {
        sec = 0;
        min = 0;
        hr = 0;
        timeList = new ArrayList<String>();
    }
}