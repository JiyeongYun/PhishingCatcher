package com.goodwiil.goodwillvoice.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.os.PowerManager;
import android.provider.Settings;
import android.speech.tts.TextToSpeech;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import com.dinuscxj.progressbar.CircleProgressBar;
import com.goodwiil.goodwillvoice.R;
import com.goodwiil.goodwillvoice.databinding.ActivityMainBinding;
import com.goodwiil.goodwillvoice.model.User;
import com.goodwiil.goodwillvoice.util.AppDataManager;
import com.goodwiil.goodwillvoice.util.CallLogDataManager;
import com.goodwiil.goodwillvoice.util.MyView;
import com.goodwiil.goodwillvoice.viewModel.MainViewModel;

public class ActivityMain extends AppCompatActivity implements CircleProgressBar.ProgressFormatter {
    private static final String DEFAULT_PATTERN = "%d%%";
    static CircleProgressBar circleProgressBar;
    public static ProgressBar progressBar;
    private MyView myView;
    private Thread myThread;
    private MyRunnable myRunnable;
    private DisplayMetrics dm;
    public static int width, height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createBinding();

        //현재 회원 등록 정보 화면에 출력하기
        getData();

        //최근기록 불러오기
        //CallLogDataManager.callLogInfos = CallLogDataManager.getCallLog(this);

        //프로그레스 바 세팅
        circleProgressBar = findViewById(R.id.cpb_circlebar);
        circleProgressBar.setProgress(80);


        //CallLogDataManager.getCallLog(this);

        //현재 회원 등록 정보 화면에 출력하기
        getData();

        //전화 상태 권한 받기
        checkPermission();

        //배터리 최적화 권한 받기
        checkPermissionBattery();

        //앱 위에 그리기 권한 받기
        checkPermissionOverlay();


        //startSubThread();

        //Progress bar 세팅
        circleProgressBar=findViewById(R.id.cpb_circlebar);
        circleProgressBar.setProgress(80);


//        myView = (MyView) findViewById(R.id.myView);
//        progressBar = (ProgressBar) findViewById(R.id.progressBarBG);
//
//        DisplayMetrics displayMetrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//
//        Display display = getWindowManager().getDefaultDisplay();
//        Point size = new Point();
//        display.getSize(size);
//        width = size.x;
//        height = size.y;

    }

    private ActivityMainBinding mBinding;

    private void createBinding() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.setViewModel(new MainViewModel());
    }

    private void getData() {
        User user = AppDataManager.getUserModel();

//        mBinding.tv.setText(
//                "Year : "+user.getYear() + "\n"
//                        +"Gender : "+user.getGender() + "\n"
//                        +"Career : "+user.getCareer() + "\n"
//                        +"City : "+user.getCity() + "\n"
//        );
    }

    @Override
    public void onBackPressed() {
        mBinding.getViewModel().onBackClick(this);
    }

    @Override
    public CharSequence format(int progress, int max) {
        return String.format(DEFAULT_PATTERN, (int) ((float) progress / (float) max * 100));
    }


    public void startSubThread() {
        myRunnable = new MyRunnable();
        myThread = new Thread(myRunnable);
        myThread.setDaemon(true);
        myThread.start();
    }

    android.os.Handler mainHandler = new android.os.Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                myView.invalidate();
            }
        }
    };

    public class MyRunnable implements Runnable {
        @Override
        public void run() {
            while (mainHandler != null) {
                try {
                    myView.i = myView.i + 0.6f;
                    if(myView.i == 45) mainHandler = null;
                    Message msg = Message.obtain();
                    msg.what = 0;
                    mainHandler.sendMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    Thread.sleep(3);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    String[] permission_list = {
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.RECEIVE_SMS,
            Manifest.permission.READ_CALL_LOG,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.READ_SMS,
            Manifest.permission.ACCESS_FINE_LOCATION
    };

    //권한 받기
    public void checkPermission() {

        for (String permission : permission_list) {
            //권한 허용 여부를 확인한다.
            int chk = checkCallingOrSelfPermission(permission);

            if (chk == PackageManager.PERMISSION_DENIED) {
                //권한 허용을여부를 확인하는 창을 띄운다

                requestPermissions(permission_list, 0);
            }
        }
    }

    //배터리 최적화 권한 받기
    private void checkPermissionBattery() {
        Intent intent = new Intent();
        String packageName = getPackageName();
        PowerManager pm = (PowerManager) getSystemService(POWER_SERVICE);
        Boolean battery = pm.isIgnoringBatteryOptimizations(packageName);
        AppDataManager.setSharedPrefs(AppDataManager.PERMISSION_KEY, AppDataManager.PERMISSION_BATTERY, battery);
        if (!pm.isIgnoringBatteryOptimizations(packageName)) {
            intent.setAction(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
            intent.setData(Uri.parse("package:" + packageName));
            startActivity(intent);
        }
    }

    //overlay 권한받기
    public void checkPermissionOverlay() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Boolean overlay = Settings.canDrawOverlays(this);// 마시멜로우 이상일 경우
            AppDataManager.setSharedPrefs(AppDataManager.PERMISSION_KEY, AppDataManager.PERMISSION_OVERLAY, overlay);
            if (!Settings.canDrawOverlays(this)) {              // 체크
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, 1);
            }
        }
    }


}
