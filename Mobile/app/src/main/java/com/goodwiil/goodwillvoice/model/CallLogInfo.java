package com.goodwiil.goodwillvoice.model;

import java.util.Comparator;

public class CallLogInfo implements Comparator< CallLogInfo > {

    private String name;
    private String number;
    private String type;
    private String date;
    private int duration;
    private double longitude;
    private double latitude;

    public CallLogInfo() {
        this.duration = 0;
    }





    public CallLogInfo(String name, String number, String type, String date, int duration, double longitude, double latitude) {
        this.name = name;
        this.number = number;
        this.type = type;
        this.date = date;
        this.duration = duration;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }


    @Override
    public int compare(CallLogInfo o1, CallLogInfo o2) {
        int returnVal = 0;

        if(o1.getDuration() < o2.getDuration()){
            returnVal =  -1;
        }else if(o1.getDuration() > o2.getDuration()){
            returnVal =  1;
        }else if(o1.getDuration() == o1.getDuration()){
            returnVal =  0;
        }
        return returnVal;
    }

}
