package com.goodwiil.goodwillvoice.viewModel;

import android.content.Intent;
import android.view.View;

import com.goodwiil.goodwillvoice.CallBroadcast;
import com.goodwiil.goodwillvoice.R;
import com.goodwiil.goodwillvoice.model.CallNumber;
import com.goodwiil.goodwillvoice.model.PhoneCall;
import com.goodwiil.goodwillvoice.model.User;
import com.goodwiil.goodwillvoice.util.AppDataManager;
import com.goodwiil.goodwillvoice.util.DBManager;
import com.goodwiil.goodwillvoice.util.ScreenManager;
import com.goodwiil.goodwillvoice.view.ServiceCall;
import com.goodwiil.goodwillvoice.view.ServiceEnd;

import androidx.constraintlayout.widget.ConstraintLayout;

public class EndViewModel {

    private void insertData(View view){
        DBManager dbManager = new DBManager();
        User user = AppDataManager.getUserModel();
        PhoneCall phoneCall = new PhoneCall(user,CallBroadcast.callLogInfo);
        dbManager.insertData(phoneCall);

        CallNumber callNumber = new CallNumber(CallBroadcast.callLogInfo.getNumber(), CallBroadcast.callLogInfo.getType());

        dbManager.insertUserCallLogData(callNumber, user);
        ScreenManager.printToast(view.getContext(), "통화기록이 등록되었습니다.");
    }

    //팝업창 닫기 버튼 함수
    public void backBtnClick(View view){
        view.getContext().stopService(new Intent(view.getContext(), ServiceEnd.class));
        CallBroadcast.callLogInfo.setType(view.getContext().getString(R.string.unknown));
        insertData(view);
    }

    public void friendBtnClick(View view, ConstraintLayout cl) {
        ScreenManager.printToast(view.getContext(), view.getContext().getString(R.string.friend) + "으로 등록되어있습니다.");
        CallBroadcast.callLogInfo.setType(view.getContext().getString(R.string.friend));
        insertData(view);
        off(view);
    }

    public void loanBtnClick(View view, ConstraintLayout cl) {
        ScreenManager.printToast(view.getContext(), view.getContext().getString(R.string.loan) + "으로 등록되어있습니다.");
        CallBroadcast.callLogInfo.setType(view.getContext().getString(R.string.loan));
        insertData(view);
        off(view);
    }

    public void researchBtnClick(View view, ConstraintLayout cl) {
        ScreenManager.printToast(view.getContext(), view.getContext().getString(R.string.research) + "으로 등록되어있습니다.");
        CallBroadcast.callLogInfo.setType(view.getContext().getString(R.string.research));
        insertData(view);
        off(view);
    }

    public void insuranceBtnClick(View view, ConstraintLayout cl) {
        ScreenManager.printToast(view.getContext(), view.getContext().getString(R.string.insurance) + "으로 등록되어있습니다.");
        CallBroadcast.callLogInfo.setType(view.getContext().getString(R.string.insurance));
        insertData(view);
        off(view);
    }

    public void deliveryBtnClick(View view, ConstraintLayout cl) {
        ScreenManager.printToast(view.getContext(), view.getContext().getString(R.string.delivery) + "으로 등록되어있습니다.");
        CallBroadcast.callLogInfo.setType(view.getContext().getString(R.string.delivery));
        insertData(view);
        off(view);
    }

    public void parcelBtnClick(View view, ConstraintLayout cl) {
        ScreenManager.printToast(view.getContext(), view.getContext().getString(R.string.parcel) + "으로 등록되어있습니다.");
        CallBroadcast.callLogInfo.setType(view.getContext().getString(R.string.parcel));
        insertData(view);
        off(view);
    }

    public void bankBtnClick(View view, ConstraintLayout cl) {
        ScreenManager.printToast(view.getContext(), view.getContext().getString(R.string.bank) + "으로 등록되어있습니다.");
        CallBroadcast.callLogInfo.setType(view.getContext().getString(R.string.bank));
        insertData(view);
        off(view);
    }

    public void policeBtnClick(View view, ConstraintLayout cl) {
        ScreenManager.printToast(view.getContext(), view.getContext().getString(R.string.police) + "으로 등록되어있습니다.");
        CallBroadcast.callLogInfo.setType(view.getContext().getString(R.string.police));
        insertData(view);
        off(view);
    }

    public void telecomBtnClick(View view, ConstraintLayout cl) {
        ScreenManager.printToast(view.getContext(), view.getContext().getString(R.string.telecom) + "으로 등록되어있습니다.");
        CallBroadcast.callLogInfo.setType(view.getContext().getString(R.string.telecom));
        insertData(view);
        off(view);
    }

    public void othersBtnClick(View view, ConstraintLayout cl) {
        ScreenManager.printToast(view.getContext(), view.getContext().getString(R.string.others) + "으로 등록되어있습니다.");
        CallBroadcast.callLogInfo.setType(view.getContext().getString(R.string.others));
        insertData(view);
        off(view);
    }
    public void off(View view){
        view.getContext().stopService(new Intent(view.getContext(), ServiceEnd.class));
    }
}
