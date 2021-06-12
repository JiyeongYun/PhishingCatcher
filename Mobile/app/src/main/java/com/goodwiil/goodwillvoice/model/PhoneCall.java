package com.goodwiil.goodwillvoice.model;

public class PhoneCall {

    private User user;
    private CallLogInfo callLogInfo;

    public PhoneCall(User user, CallLogInfo callLogInfo) {
        this.user = user;
        this.callLogInfo = callLogInfo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CallLogInfo getCallLogInfo() {
        return callLogInfo;
    }

    public void setCallLogInfo(CallLogInfo callLogInfo) {
        this.callLogInfo = callLogInfo;
    }




}
