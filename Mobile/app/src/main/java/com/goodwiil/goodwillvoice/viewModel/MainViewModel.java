package com.goodwiil.goodwillvoice.viewModel;

import android.view.View;

import com.goodwiil.goodwillvoice.util.AppDataManager;
import com.goodwiil.goodwillvoice.util.ScreenManager;
import com.goodwiil.goodwillvoice.view.ActivityCallLog;
import com.goodwiil.goodwillvoice.view.ActivityMain2;
import com.goodwiil.goodwillvoice.view.ActivityMenu;

public class MainViewModel extends BaseViewModel {

    public void menuBtnClick(View view) {
        ScreenManager.startActivity(view.getContext(), ActivityMenu.class);
    }

    public void callLogBtnClick(View view) {
        ScreenManager.startActivity(view.getContext(), ActivityCallLog.class);
    }

    public void sampleBtnClick(View view){
        ScreenManager.startActivity(view.getContext(), ActivityMain2.class);
    }

}
