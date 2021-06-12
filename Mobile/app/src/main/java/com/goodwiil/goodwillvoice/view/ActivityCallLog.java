package com.goodwiil.goodwillvoice.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.goodwiil.goodwillvoice.R;
import com.goodwiil.goodwillvoice.databinding.ActivityCallLogBinding;
import com.goodwiil.goodwillvoice.model.CallLogInfo;
import com.goodwiil.goodwillvoice.util.CallLogDataManager;
import com.goodwiil.goodwillvoice.util.ScreenManager;
import com.goodwiil.goodwillvoice.viewModel.CallLogViewModel;

import java.util.ArrayList;

public class ActivityCallLog extends AppCompatActivity {
    //data binding
    private ActivityCallLogBinding mBinding;
    private int numberOfIncomingCall;
    private int numberOfknown;
    private int numberOfUnknown;
    private int unknownRejected;
    private int unknownReceived;
    private int unknownMissed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createBinding();
        processingData();

        ScreenManager.startCountAnimation(numberOfIncomingCall, mBinding.tvTotal);
        ScreenManager.startCountAnimation(numberOfUnknown, mBinding.tvTotalUnknown);
        ScreenManager.startCountAnimation(numberOfUnknown, mBinding.tvTotalUnknownMore);

        ScreenManager.startCountAnimation(numberOfknown, mBinding.tvTotalKnown);
        ScreenManager.startCountAnimation(unknownReceived, mBinding.tvReceived);
        ScreenManager.startCountAnimation(unknownRejected, mBinding.tvRejected);
        ScreenManager.startCountAnimation(unknownMissed, mBinding.tvMissed);

        mBinding.tvMaxNumber.setText(CallLogDataManager.callAnalysisInfo.getUnknownCallMaxNumber());
        mBinding.tvMinNumber.setText(CallLogDataManager.callAnalysisInfo.getUnknownCallMinNumber());
        ScreenManager.startCountAnimationTime(CallLogDataManager.callAnalysisInfo.getUnknownCallMax(), mBinding.tvMax);
        ScreenManager.startCountAnimationTime(CallLogDataManager.callAnalysisInfo.getUnknownCallMin(), mBinding.tvMin);
        ScreenManager.startCountAnimationTime(CallLogDataManager.unknownCallTotal/CallLogDataManager.unknownCallTotalNum, mBinding.tvAverage);



    }

    //Data 분별하기
    public void processingData() {

        // 사용자 통화내역 기록 가져오기
        ArrayList<CallLogInfo> callLogList = CallLogDataManager.getCallLog(this, 1);

        numberOfIncomingCall = 0;
        numberOfknown = 0;
        numberOfUnknown = 0;
        unknownRejected = 0;
        unknownReceived = 0;
        unknownMissed = 0;

        for (CallLogInfo info : callLogList) {

            if (info.getType() != null && !info.getType().equals("OUTGOING")) {
                numberOfIncomingCall++;
                if (info.getName().equals("unknown")) {
                    numberOfUnknown++;
                    if(info.getDuration() > 0){
                        unknownReceived++;
                    }
                }
            }
            if (info.getType() != null && info.getName().equals("unknown")) {
                if(info.getType().equals("REJECTED")){
                    unknownRejected++;
                }
                else if(info.getType().equals("MISSED")){
                    unknownMissed++;
                }
            }
        }
        numberOfknown = numberOfIncomingCall - numberOfUnknown;
    }

    private void createBinding() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_call_log);
        mBinding.setViewModel(new CallLogViewModel());
    }

}
