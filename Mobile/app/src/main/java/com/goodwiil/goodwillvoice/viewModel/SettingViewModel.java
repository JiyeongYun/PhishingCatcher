package com.goodwiil.goodwillvoice.viewModel;

import android.app.Activity;
import android.view.View;

import com.goodwiil.goodwillvoice.util.ScreenManager;
import com.goodwiil.goodwillvoice.view.ActivitySignUp;

public class SettingViewModel {

    public void backBtnClick(View view) { ((Activity) view.getContext()).finish(); }

    public void userBtnClick(View view) {
        ScreenManager.startActivity(view.getContext(), ActivitySignUp.class);
    }

}
