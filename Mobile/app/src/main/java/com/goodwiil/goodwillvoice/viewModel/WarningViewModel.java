package com.goodwiil.goodwillvoice.viewModel;

import android.content.Intent;
import android.view.View;

import com.goodwiil.goodwillvoice.view.ServiceWarning;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class WarningViewModel {

    //팝업창 닫기 버튼 함수
    public void backBtnClick(View view) {
        view.getContext().stopService(new Intent(view.getContext(), ServiceWarning.class));
    }

//    public void sample(){
//        state = state + 1;
//    }

    public String updateState(String state){



        return state;
    }
}
