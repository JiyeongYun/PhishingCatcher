package com.goodwiil.goodwillvoice.util;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.formatter.ValueFormatter;
import com.goodwiil.goodwillvoice.model.CallNumber;
import com.goodwiil.goodwillvoice.model.IncomingNumber;

import static com.github.mikephil.charting.utils.ColorTemplate.rgb;

public class ScreenManager {

    //Activity 화면 이동 함수
    public static void startActivity(Context context, Class c){
        Intent intent = new Intent(context, c);
        context.startActivity(intent);
    }

    //Service 시작 (이름과 번호도 같이 보내기)
    public static void startService(Context context, Class c, IncomingNumber incomingNumber){
        Intent serviceIntent = new Intent(context, c);
        serviceIntent.putExtra("incomingNumber",incomingNumber);
        context.startService(serviceIntent);
    }

    //Toast 출력 함수
    public static void printToast(Context context, String string){
        Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
    }

    public static void startCountAnimation(int number, final TextView textView) {
        ValueAnimator animator = ValueAnimator.ofInt(0, number);
        animator.setDuration(1000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                textView.setText(animation.getAnimatedValue().toString());
            }
        });
        animator.start();
    }

    public static void startCountAnimationTime(int number, final TextView textView) {
        ValueAnimator animator = ValueAnimator.ofInt(0, number);
        animator.setDuration(2000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                textView.setText(secondsToString(animation.getAnimatedValue().toString()));
            }
        });
        animator.start();
    }

    //초를 mm:ss 형식으로 바꾸기
    public static String secondsToString(String pTime) {
        return String.format("%02d:%02d", Integer.parseInt(pTime) / 60, Integer.parseInt(pTime) % 60);
    }

    public static class MyValueFormatter extends ValueFormatter {

        public MyValueFormatter() {
            super();
        }

        @Override
        public String getFormattedValue(float value) {
            return "" + ((int) value);
        }
    }



    public static final int[] PIE_CHART_COLOR = {
            rgb("#ffe875"), rgb("#fe9d66"), rgb("#ff7d7d")
    };

    public static final int[] BAR_CHART_COLOR = {
            rgb("#71bbe8"), rgb("#1c84d3"), rgb("#034893")
    };

}

