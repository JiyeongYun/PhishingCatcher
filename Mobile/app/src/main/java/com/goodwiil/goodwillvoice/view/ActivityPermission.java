package com.goodwiil.goodwillvoice.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.goodwiil.goodwillvoice.R;
import com.goodwiil.goodwillvoice.databinding.ActivityPermissionBinding;
import com.goodwiil.goodwillvoice.model.User;
import com.goodwiil.goodwillvoice.viewModel.PermissionViewModel;
import com.goodwiil.goodwillvoice.viewModel.SignUpViewModel;

public class ActivityPermission extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createBinding();
    }

    private ActivityPermissionBinding mBinding;
    private void createBinding() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_permission);
        mBinding.setViewModel(new PermissionViewModel());
    }
}