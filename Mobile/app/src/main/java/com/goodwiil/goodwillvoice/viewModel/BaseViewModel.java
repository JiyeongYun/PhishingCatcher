package com.goodwiil.goodwillvoice.viewModel;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

public class BaseViewModel {

    private long backKeyPressedTime = 0;

    public void backClick(View view){
        ((Activity) view.getContext()).finish();
    }

    public void onBackClick(Context context) {

        //1번째 백버튼 클릭
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            Toast.makeText(context, "뒤로 버튼을 한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
        }

        //2번째 백버튼 클릭 (종료)
        else {
            ((Activity) context).finish();
            System.exit(0);
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }

}
