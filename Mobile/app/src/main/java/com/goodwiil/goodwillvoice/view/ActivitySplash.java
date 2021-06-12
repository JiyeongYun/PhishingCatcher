package com.goodwiil.goodwillvoice.view;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.goodwiil.goodwillvoice.R;
import com.goodwiil.goodwillvoice.databinding.ActivitySplashBinding;
import com.goodwiil.goodwillvoice.util.AppDataManager;
import com.goodwiil.goodwillvoice.viewModel.SplashViewModel;

import android.os.PowerManager;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Iterator;

public class ActivitySplash extends AppCompatActivity {

    private final int PERMISSION_MULTI_CODE = 100;

    String[] permission_list = {
            Manifest.permission.READ_PHONE_STATE,
//            Manifest.permission.RECEIVE_SMS,
            Manifest.permission.READ_CALL_LOG,
            Manifest.permission.READ_CONTACTS,
//            Manifest.permission.READ_SMS,
            Manifest.permission.ACCESS_FINE_LOCATION
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createBinding();

        //mBinding.getViewModel().startApp(this);


        if (!AppDataManager.isConnected(this)) {
            noService();
            return;
        }

        else{
            if(checkAndRequestPermissions(this)){
                mBinding.getViewModel().startApp(this);
                finish();
            }
        }


    }

    private ActivitySplashBinding mBinding;

    private void createBinding() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        mBinding.setViewModel(new SplashViewModel());

    }


    /**
     * 현재 인터넷에 접속할 수 없기 때문에 서비스를 사용할 수 없다는 메시지와
     * 화면 종료 버튼을 보여준다.
     */
    private void noService() {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_network, viewGroup, false);


        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyDialogTheme);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);

        alertDialog.show();

        Button networkDialogCancelBtn = (Button)alertDialog.findViewById(R.id.btn_yes);

        networkDialogCancelBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }


    //권한 받기
    public void checkPermission(String[] listPermissionsNeeded) {


        ActivityCompat.requestPermissions(
                this,
                listPermissionsNeeded,
                PERMISSION_MULTI_CODE
        );


//        for (String permission : permission_list) {
//            //권한 허용 여부를 확인한다.
//            int chk = checkCallingOrSelfPermission(permission);
//
//            if (chk == PackageManager.PERMISSION_DENIED) {
//                //권한 허용을여부를 확인하는 창을 띄운다
//
//                ActivityCompat.requestPermissions(this,permission_list, 0);
//            }
//        }
    }


    //배터리 최적화 권한 받기
    private void checkPermissionBattery() {
        Intent intent = new Intent();
        String packageName = getPackageName();
        PowerManager pm = (PowerManager) this.getSystemService(POWER_SERVICE);
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


    /**
     * 권한을 확인하고 권한이 부여되어 있지 않다면 권한을 요청한다.
     * @return 필요한 권한이 모두 부여되었다면 true, 그렇지 않다면 false
     */
    public Boolean checkAndRequestPermissions(Context context){


        String[] permissions = {
                Manifest.permission.READ_PHONE_STATE,
//            Manifest.permission.RECEIVE_SMS,
                Manifest.permission.READ_CALL_LOG,
                Manifest.permission.READ_CONTACTS,
//            Manifest.permission.READ_SMS,
                Manifest.permission.ACCESS_FINE_LOCATION};


        ArrayList<String> listPermissionsNeeded = new ArrayList<>();


        for(int i = 0 ; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(context, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(permissions[i]);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {

            String[] list = new String[listPermissionsNeeded.size()];

            int i = 0;
            for (Iterator<String> iterator = listPermissionsNeeded.iterator(); iterator.hasNext(); i++) {
                list[i] = iterator.next();
            }

            showPermissionInfoDialog(list);
            return false;
        }

        return true;
    }


    /**
     * 권한 설정 화면으로 이동할 지를 선택하는 다이얼로그를 보여준다.
     */
    public void showPermissionInfoDialog(final String[] listPermissionsNeeded) {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_permission, viewGroup, false);


        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyDialogTheme);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
        final AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);

        alertDialog.show();

        Button networkDialogCancelBtn = (Button)alertDialog.findViewById(R.id.alertdialog_button);

        networkDialogCancelBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                alertDialog.cancel();

                checkPermission(listPermissionsNeeded);

                //배터리 최적화 권한 받기
                checkPermissionBattery();

                //앱 위에 그리기 권한 받기
                checkPermissionOverlay();

            }
        });
    }



    private void checkPermissionResult(String[] permissions, int[] grantResults) {
        Boolean isAllGranted = true;
        for (int i = 0; i < permissions.length; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                isAllGranted = false;
            }
        }

        //권한이 부여되었다면
        if (isAllGranted) {
            mBinding.getViewModel().startApp(this);
            finish();

            //권한이 부여되어 있지 않다면
        } else {
            mBinding.getViewModel().checkAndRequestPermissions(this);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length == 0) return;

        switch (requestCode){
            case PERMISSION_MULTI_CODE:
                checkPermissionResult(permissions, grantResults);

        }
    }
}
