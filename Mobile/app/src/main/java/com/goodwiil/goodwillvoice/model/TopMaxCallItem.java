package com.goodwiil.goodwillvoice.model;

public class TopMaxCallItem {

    private int id;
    private String number;
    private String type;
    private String length;


    public TopMaxCallItem(int id, String number, String type, String length) {
        this.id = id;
        this.number = number;
        this.type = type;
        this.length = length;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }
}
