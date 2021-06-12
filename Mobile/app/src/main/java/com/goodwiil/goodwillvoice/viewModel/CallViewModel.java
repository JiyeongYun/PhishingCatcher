package com.goodwiil.goodwillvoice.viewModel;

import android.content.Intent;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.goodwiil.goodwillvoice.CallBroadcast;
import com.goodwiil.goodwillvoice.R;
import com.goodwiil.goodwillvoice.util.ScreenManager;
import com.goodwiil.goodwillvoice.view.ServiceCall;

public class CallViewModel {

    //팝업창 닫기 버튼 함수
    public void backBtnClick(View view) {
        view.getContext().stopService(new Intent(view.getContext(), ServiceCall.class));
    }


    public void friendBtnClick(View view, ConstraintLayout cl) {
        ScreenManager.printToast(view.getContext(), view.getContext().getString(R.string.friend) + "으로 등록되어있습니다.");
        CallBroadcast.callLogInfo.setType(view.getContext().getString(R.string.friend));
        off(view);
    }

    public void loanBtnClick(View view, ConstraintLayout cl) {
        ScreenManager.printToast(view.getContext(), view.getContext().getString(R.string.loan) + "으로 등록되어있습니다.");
        CallBroadcast.callLogInfo.setType(view.getContext().getString(R.string.loan));
        off(view);
    }

    public void researchBtnClick(View view, ConstraintLayout cl) {
        ScreenManager.printToast(view.getContext(), view.getContext().getString(R.string.research) + "으로 등록되어있습니다.");
        CallBroadcast.callLogInfo.setType(view.getContext().getString(R.string.research));
        off(view);
    }

    public void insuranceBtnClick(View view, ConstraintLayout cl) {
        ScreenManager.printToast(view.getContext(), view.getContext().getString(R.string.insurance) + "으로 등록되어있습니다.");
        CallBroadcast.callLogInfo.setType(view.getContext().getString(R.string.insurance));
        off(view);
    }

    public void deliveryBtnClick(View view, ConstraintLayout cl) {
        ScreenManager.printToast(view.getContext(), view.getContext().getString(R.string.delivery) + "으로 등록되어있습니다.");
        CallBroadcast.callLogInfo.setType(view.getContext().getString(R.string.delivery));
        off(view);
    }

    public void parcelBtnClick(View view, ConstraintLayout cl) {
        ScreenManager.printToast(view.getContext(), view.getContext().getString(R.string.parcel) + "으로 등록되어있습니다.");
        CallBroadcast.callLogInfo.setType(view.getContext().getString(R.string.parcel));
        off(view);
    }

    public void bankBtnClick(View view, ConstraintLayout cl) {
        ScreenManager.printToast(view.getContext(), view.getContext().getString(R.string.bank) + "으로 등록되어있습니다.");
        CallBroadcast.callLogInfo.setType(view.getContext().getString(R.string.bank));
        off(view);
    }

    public void policeBtnClick(View view, ConstraintLayout cl) {
        ScreenManager.printToast(view.getContext(), view.getContext().getString(R.string.police) + "으로 등록되어있습니다.");
        CallBroadcast.callLogInfo.setType(view.getContext().getString(R.string.police));
        off(view);
    }

    public void telecomBtnClick(View view, ConstraintLayout cl) {
        ScreenManager.printToast(view.getContext(), view.getContext().getString(R.string.telecom) + "으로 등록되어있습니다.");
        CallBroadcast.callLogInfo.setType(view.getContext().getString(R.string.telecom));
        off(view);
    }

    public void othersBtnClick(View view, ConstraintLayout cl) {
        ScreenManager.printToast(view.getContext(), view.getContext().getString(R.string.others) + "으로 등록되어있습니다.");
        CallBroadcast.callLogInfo.setType(view.getContext().getString(R.string.others));
        off(view);
    }

    public void off(View view) {
        view.getContext().stopService(new Intent(view.getContext(), ServiceCall.class));
    }

}
