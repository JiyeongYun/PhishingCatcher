package com.goodwiil.goodwillvoice.application;

import android.app.Application;
import android.content.Context;

public class GoodWillApplication extends Application {

    private static GoodWillApplication instance;

    //앱 자체의 자신을 불러오기 위해 instance 에서 앱 전체의 context 저장
    public GoodWillApplication() { instance = this; }

    // 앱 자체의 instance 받기
    public static Context getContext() { return instance; }


    @Override
    public void onCreate() {
        super.onCreate();
    }

}
