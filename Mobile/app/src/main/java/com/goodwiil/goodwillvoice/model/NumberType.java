package com.goodwiil.goodwillvoice.model;

public class NumberType {

    private int internetNumberTotal;
    private int phoneNumberTotal;
    private int[] locationNumberTotal;

    public NumberType() {
        this.internetNumberTotal = 0;
        this.phoneNumberTotal = 0;
        this.locationNumberTotal = new int[17];
    }


    public int getInternetNumberTotal() {
        return internetNumberTotal;
    }


    public int getPhoneNumberTotal() {
        return phoneNumberTotal;
    }


    public int[] getLocationNumberTotal() {
        return locationNumberTotal;
    }


    public void addInternetNumber(){
        this.internetNumberTotal++;
    }

    public void addPhoneNumber(){
        this.phoneNumberTotal++;
    }

    public void addlocationNumber(int location){
        this.locationNumberTotal[location]++;
    }





}
