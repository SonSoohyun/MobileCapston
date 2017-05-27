package com.example.soohyun.capston;

/**
 * Created by JaeBeome on 2017. 5. 25..
 */

public class Record {
    String date;
    double distance;
    int minute;

    Record(){

    }

    Record(String date, double distance, int minute){
        this.date = date;
        this.distance = distance;
        this.minute = minute;
    }
}
