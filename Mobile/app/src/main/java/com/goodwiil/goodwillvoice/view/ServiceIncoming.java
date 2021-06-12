package com.goodwiil.goodwillvoice.view;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.goodwiil.goodwillvoice.R;
import com.goodwiil.goodwillvoice.databinding.ServiceIncomingBinding;
import com.goodwiil.goodwillvoice.model.CallNumber;
import com.goodwiil.goodwillvoice.model.IncomingNumber;
import com.goodwiil.goodwillvoice.model.User;
import com.goodwiil.goodwillvoice.util.AppDataManager;
import com.goodwiil.goodwillvoice.viewModel.IncomingViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ServiceIncoming extends Service {

    WindowManager wm;
    View mView;
    IncomingNumber model;
    CallNumber callNumber;
    WindowManager.LayoutParams params;
    public static FirebaseFirestore db;
    private float START_Y;                            //움직이기 위해 터치한 시작 점
    private int PREV_Y;                                //움직이기 이전에 뷰가 위치한 점

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        createBinding();
        getData(intent);
        addWindowManager();

        db = FirebaseFirestore.getInstance();

        User user = AppDataManager.getUserModel();
        DocumentReference docRef = db.collection("UserCallLog").document(user.getNumber()).collection("CallNumbers").document(model.getNumber());


        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    callNumber = documentSnapshot.toObject(CallNumber.class);
                    System.out.println(callNumber.getType());
                    if(callNumber.getType() != null) mBinding.tvName.setText(callNumber.getType());
                    else mBinding.tvName.setText("등록되지 않은 번호");
                }
                else{
                    mBinding.tvName.setText(getString(R.string.unknown_number));
                }
            }


        });





        return super.onStartCommand(intent, flags, startId);
    }

    private void getData(Intent intent) {
        model = (IncomingNumber) intent.getSerializableExtra("incomingNumber");
        mBinding.getModel().setNumber(model.getNumber());
    }

    private void addWindowManager() {
        mView = mBinding.getRoot();
        mView.setOnTouchListener(mViewTouchListener);
        wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        params = setParams();
        wm.addView(mView, params);
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

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    private ServiceIncomingBinding mBinding;

    private void createBinding() {
        mBinding = ServiceIncomingBinding.inflate(LayoutInflater.from(this));
        mBinding.setViewModel(new IncomingViewModel());
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

}

