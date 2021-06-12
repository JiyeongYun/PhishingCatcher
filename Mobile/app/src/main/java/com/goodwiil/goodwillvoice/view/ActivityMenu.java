package com.goodwiil.goodwillvoice.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.goodwiil.goodwillvoice.R;
import com.goodwiil.goodwillvoice.databinding.ActivityMenuBinding;
import com.goodwiil.goodwillvoice.model.User;
import com.goodwiil.goodwillvoice.util.AppDataManager;
import com.goodwiil.goodwillvoice.viewModel.MenuViewModel;

public class ActivityMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createBinding();
        User user = AppDataManager.getUserModel();
        mBinding.tvUserName.setText(user.getNickName());
//        mBinding.tvUserGender.setText(user.getGender());
//        mBinding.tvUserCareer.setText(user.getCareer());
    }


    private ActivityMenuBinding mBinding;
    private void createBinding() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_menu);
        mBinding.setViewModel(new MenuViewModel());
    }


}
