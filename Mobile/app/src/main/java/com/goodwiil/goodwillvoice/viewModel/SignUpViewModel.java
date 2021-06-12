package com.goodwiil.goodwillvoice.viewModel;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.goodwiil.goodwillvoice.R;
import com.goodwiil.goodwillvoice.model.User;
import com.goodwiil.goodwillvoice.util.AppDataManager;
import com.goodwiil.goodwillvoice.util.DBManager;
import com.goodwiil.goodwillvoice.util.ScreenManager;
import com.goodwiil.goodwillvoice.view.ActivityMain;
import com.goodwiil.goodwillvoice.view.ActivityMain2;

import static android.content.Context.TELEPHONY_SERVICE;


public class SignUpViewModel extends BaseViewModel {

    public void signUpBtnClick(View view, ConstraintLayout cl) {

        String year = ((Spinner) cl.getChildAt(2)).getSelectedItem().toString();
        String gender = ((Spinner) cl.getChildAt(3)).getSelectedItem().toString();
        String career = ((Spinner) cl.getChildAt(4)).getSelectedItem().toString();
        String creditRating = ((Spinner) cl.getChildAt(5)).getSelectedItem().toString();
        EditText nickNameEdit = cl.findViewById(R.id.et_nick_name);
        String nickName = nickNameEdit.getText().toString();

        TelephonyManager telManager = (TelephonyManager) view.getContext().getSystemService(TELEPHONY_SERVICE);
        if (
                ActivityCompat.checkSelfPermission(view.getContext(), Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(view.getContext(), Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(view.getContext(), Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        String phoneNum = telManager.getLine1Number();
        if(phoneNum != null && phoneNum.startsWith("+82")){
            phoneNum = phoneNum.replace("+82", "0");}

        if (year.equals("출생년도") || gender.equals("성별") || career.equals("직업") || creditRating.equals("신용등급")) {
            Toast.makeText(view.getContext(), "다시 입력해주세요.", Toast.LENGTH_SHORT).show();
        } else {
            User user = new User(year, gender, career, nickName, phoneNum, creditRating);
            int permissionCheck = ContextCompat.checkSelfPermission(view.getContext(), Manifest.permission.INTERNET);

            if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                DBManager dbManager = new DBManager();
                dbManager.insertData(user);
            }

            AppDataManager.setSharedPrefs(AppDataManager.SP_NAME, user);
            ScreenManager.startActivity(view.getContext(), ActivityMain2.class);
            ((Activity) view.getContext()).finish();

            ScreenManager.printToast(view.getContext(), "회원정보가 등록되었습니다.");
        }


    }

    public void setPermissionPref(String name, Boolean auth) {
        AppDataManager.setSharedPrefs(AppDataManager.PERMISSION_KEY, name, auth);
    }

}
