package com.example.soohyun.capston;

/**
 * Created by sooHyun on 2017-05-25.
 */

public class dashboardDatabase {
    private String userName;
    private String date;
    private double distance;
    private int timeMinutes;
    private int score;

    public dashboardDatabase(){}

    public dashboardDatabase(String userName, String date, double distance, int timeMinutes, int score){
        this.userName = userName;
        this.date = date;
        this.distance = distance;
        this.timeMinutes = timeMinutes;
        this.score = score;
    }

}
