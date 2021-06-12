package com.goodwiil.goodwillvoice.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.goodwiil.goodwillvoice.R;
import com.goodwiil.goodwillvoice.view.ActivityMain;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;

public class MyView extends View {
    private int width, height;
    public float i;

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        width = ActivityMain.width;
        height = ActivityMain.height;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint pnt = new Paint();
        pnt.setStrokeWidth(110f);

        pnt.setColor(Color.parseColor("#333CCC"));
        pnt.setStyle(Paint.Style.STROKE);
        pnt.setStrokeCap(Paint.Cap.ROUND);

        System.out.println(ActivityMain.progressBar.getX());


        RectF rect = new RectF();
        rect.set(ActivityMain.progressBar.getX()+ 100,
                ActivityMain.progressBar.getY() + 100,
                ActivityMain.progressBar.getX() +1000,
                ActivityMain.progressBar.getY() + 1000);
        canvas.drawArc(rect, 270, i, false, pnt);


//        rect.set(100, 100, 100, 100);
//
//        if(i >= 270) i = 270;
//        canvas.drawArc(rect, 270, i, false, pnt);

    }

}
