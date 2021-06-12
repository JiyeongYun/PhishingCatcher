package com.goodwiil.goodwillvoice.viewModel;

import android.content.Intent;
import android.view.View;

import com.goodwiil.goodwillvoice.view.ServiceIncoming;


public class IncomingViewModel {

    //팝업창 닫기 버튼 함수
    public void backBtnClick(View view) {
        view.getContext().stopService(new Intent(view.getContext(), ServiceIncoming.class));
    }

}
