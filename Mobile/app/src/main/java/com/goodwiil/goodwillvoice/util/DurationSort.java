package com.goodwiil.goodwillvoice.util;

import com.goodwiil.goodwillvoice.model.CallLogInfo;

import java.util.Comparator;

public class DurationSort implements Comparator<CallLogInfo> {
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
