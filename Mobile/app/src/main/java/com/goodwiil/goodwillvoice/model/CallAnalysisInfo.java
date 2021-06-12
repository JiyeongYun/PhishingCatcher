package com.goodwiil.goodwillvoice.model;

public class CallAnalysisInfo {

    private int numOutgoing;
    private int numIncoming;
    private int numMissed;
    private int numRejected;
    private int unknownCallMax;
    private int unknownCallMin;
    private int unknownCallAverage;
    private String unknownCallMaxNumber;
    private String unknownCallMinNumber;
    private int knownCallMax;
    private int knownCallMin;
    private int knownCallAverage = 0;

    public CallAnalysisInfo() {
        numOutgoing = 0;
        numIncoming = 0;
        numMissed = 0;
        numRejected = 0;
        unknownCallMax = Integer.MIN_VALUE;
        unknownCallMin = Integer.MAX_VALUE;
        unknownCallAverage = 0;
        knownCallMax = Integer.MIN_VALUE;
        knownCallMin = Integer.MAX_VALUE;
        knownCallAverage = 0;
    }


    public int getNumOutgoing() {
        return numOutgoing;
    }

    public void setNumOutgoing(int numOutgoing) {
        this.numOutgoing = numOutgoing;
    }

    public int getNumIncoming() {
        return numIncoming;
    }

    public void setNumIncoming(int numIncoming) {
        this.numIncoming = numIncoming;
    }

    public int getNumMissed() {
        return numMissed;
    }

    public void setNumMissed(int numMissed) {
        this.numMissed = numMissed;
    }

    public int getNumRejected() {
        return numRejected;
    }

    public void setNumRejected(int numRejected) {
        this.numRejected = numRejected;
    }

    public int getUnknownCallMax() {
        return unknownCallMax;
    }

    public void setUnknownCallMax(int unknownCallMax) {
        this.unknownCallMax = unknownCallMax;
    }

    public int getUnknownCallMin() {
        return unknownCallMin;
    }

    public void setUnknownCallMin(int unknownCallMin) {
        this.unknownCallMin = unknownCallMin;
    }

    public int getUnknownCallAverage() {
        return unknownCallAverage;
    }

    public void setUnknownCallAverage(int unknownCallAverage) {
        this.unknownCallAverage = unknownCallAverage;
    }

    public int getKnownCallMax() {
        return knownCallMax;
    }

    public void setKnownCallMax(int knownCallMax) {
        this.knownCallMax = knownCallMax;
    }

    public int getKnownCallMin() {
        return knownCallMin;
    }

    public void setKnownCallMin(int knownCallMin) {
        this.knownCallMin = knownCallMin;
    }

    public int getKnownCallAverage() {
        return knownCallAverage;
    }

    public void setKnownCallAverage(int knownCallAverage) {
        this.knownCallAverage = knownCallAverage;
    }

    public String getUnknownCallMaxNumber() {
        return unknownCallMaxNumber;
    }

    public void setUnknownCallMaxNumber(String unknownCallMaxNumber) {
        this.unknownCallMaxNumber = unknownCallMaxNumber;
    }

    public String getUnknownCallMinNumber() {
        return unknownCallMinNumber;
    }

    public void setUnknownCallMinNumber(String unknownCallMinNumber) {
        this.unknownCallMinNumber = unknownCallMinNumber;
    }
}
