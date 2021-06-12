package com.goodwiil.goodwillvoice.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Network;

import com.goodwiil.goodwillvoice.application.GoodWillApplication;
import com.goodwiil.goodwillvoice.model.User;
import com.google.gson.Gson;


public class AppDataManager {

    public static final String SP_NAME = "userInfo";
    public static final String SP_KEY_USER = "userInfoKey";

    public static final String SETTING_VIBRATE = "vibration_alarm";
    public static final String SETTING_VOICE = "voice_alarm";
    public static final String SETTING_LEVEL = "level_list";

    public static final String PERMISSION_KEY = "permissionInfo";
    public static final String PERMISSION_BATTERY = "battery_optimization";
    public static final String PERMISSION_OVERLAY = "overlay_permission";

    public static Boolean incomingCall = false;

    //shared preference에 값을 저장하기
    public static void setSharedPrefs(String name, User user) {
        SharedPreferences prefs = GoodWillApplication.getContext().getSharedPreferences(name, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        editor.putString(SP_KEY_USER, json);
        editor.apply();
    }

    public static void setSharedPrefs(String name, String permission, Boolean accessibility) {
        SharedPreferences prefs = GoodWillApplication.getContext().getSharedPreferences(name, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(permission, accessibility);
        editor.apply();
    }

    //shared preference에 값을 불러오기
    public static SharedPreferences getSharedPrefs(String name) {
        return GoodWillApplication.getContext().getSharedPreferences(name, Activity.MODE_PRIVATE);
    }

    public static User getUserModel() {
        Gson gson = new Gson();
        return (gson.fromJson(getSharedPrefs(SP_NAME).getString(SP_KEY_USER, null), User.class));
    }


    /**
     * 네트워크 연결 여부를 반환한다.
     * @param context 컨텍스트
     * @return 네트워크 연결여부
     */
    public static Boolean isConnected(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager)context.getSystemService(context.CONNECTIVITY_SERVICE);
            Network info = cm.getActiveNetwork();

            return info != null;

        } catch (Exception e) {
            return false;
        }

    }


}
