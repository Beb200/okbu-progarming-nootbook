package edu.okbu;

public class HealthData {
    int userAgeYears;
    int userAgeDays;

    public HealthData(int y, int d){
        userAgeDays = d;
        userAgeYears = y;
    }

     public int totalHeartbeats() {
        return 0;

    }

    public int ageinMin(){
        return 0;
    }

    public int AgeDays(){
        userAgeDays = userAgeYears * 365;
        return userAgeDays;
    }       

    }

