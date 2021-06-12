package com.goodwiil.goodwillvoice.model;

import java.util.Comparator;

public class CallNumber {

    private String number;
    private String type;

    public CallNumber() {
    }



    public CallNumber(String number, String type) {
        this.number = number;
        this.type = type;
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






}
