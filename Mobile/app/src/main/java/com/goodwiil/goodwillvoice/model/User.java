package com.goodwiil.goodwillvoice.model;

public class User {
    private String year;
    private String gender;
    private String career;
    private String nickName;
    private String number;
    private String creditRating;

    public User() {}

    public User(String year, String gender, String career, String nickName, String number, String creditRating) {
        this.year = year;
        this.gender = gender;
        this.career = career;
        this.nickName = nickName;
        this.number = number;
        this.creditRating = creditRating;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCreditRating() { return creditRating; }

    public void setCreditRating(String creditRating){ this.creditRating = creditRating;}

}
