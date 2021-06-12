package com.goodwiil.goodwillvoice.view;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import com.goodwiil.goodwillvoice.databinding.ServiceCallBinding;
import com.goodwiil.goodwillvoice.model.IncomingNumber;
import com.goodwiil.goodwillvoice.viewModel.CallViewModel;

public class ServiceCall extends Service {

    WindowManager wm;
    View mView;
    WindowManager.LayoutParams params;
    private int MAX_X = -1;
    private float START_Y;          //움직이기 위해 터치한 시작 점
    private int PREV_Y;             //움직이기 이전에 뷰가 위치한 점

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        createBinding();
        getData(intent);
        addWindowManager();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (wm != null) {
            if (mView != null) {
                wm.removeView(mView);
                mView = null;
            }
            wm = null;
        }
    }

    private void getData(Intent intent) {
        IncomingNumber model = (IncomingNumber) intent.getSerializableExtra("incomingNumber");
        mBinding.getModel().setNumber(model.getNumber());
    }

    private void addWindowManager() {
        mView = mBinding.getRoot();
        mView.setOnTouchListener(mViewTouchListener);
        wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        params = setParams();
        wm.addView(mView, params);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    private ServiceCallBinding mBinding;

    private void createBinding() {
        mBinding = ServiceCallBinding.inflate(LayoutInflater.from(this));
        mBinding.setViewModel(new CallViewModel());
        mBinding.setModel(new IncomingNumber());

    }

    private WindowManager.LayoutParams setParams() {
        WindowManager.LayoutParams params;

        params = new WindowManager.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                        | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                        | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                PixelFormat.TRANSLUCENT);

        params.gravity = Gravity.CENTER_VERTICAL | Gravity.TOP;

        return params;
    }

    private View.OnTouchListener mViewTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:                //사용자 터치 다운이면
                    if (MAX_X == -1)
                        setMaxPosition();
                    START_Y = event.getRawY();                    //터치 시작 점
                    PREV_Y = params.y;                            //뷰의 시작 점
                    break;
                case MotionEvent.ACTION_MOVE:
                    int y = (int) (event.getRawY() - START_Y);    //이동한 거리

                    //터치해서 이동한 만큼 이동 시킨다
                    params.y = PREV_Y + y;

                    //optimizePosition();        //뷰의 위치 최적화
                    wm.updateViewLayout(mView, params);    //뷰 업데이트
                    break;
            }

            return true;
        }
    };

    /**
     * 뷰의 위치가 화면 안에 있게 최대값을 설정한다
     */
    private void setMaxPosition() {
        DisplayMetrics matrix = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(matrix);        //화면 정보를 가져와서
    }
}
