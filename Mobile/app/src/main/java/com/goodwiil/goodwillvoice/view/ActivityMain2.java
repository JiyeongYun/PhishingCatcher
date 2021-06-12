package com.goodwiil.goodwillvoice.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.MenuItem;

import com.goodwiil.goodwillvoice.R;
import com.goodwiil.goodwillvoice.databinding.ActivityMain2Binding;
import com.goodwiil.goodwillvoice.databinding.ActivityMenuBinding;
import com.goodwiil.goodwillvoice.viewModel.Main2ViewModel;
import com.goodwiil.goodwillvoice.viewModel.MenuViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static com.goodwiil.goodwillvoice.application.GoodWillApplication.getContext;

public class ActivityMain2 extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


//        AudioManager audioManager;
//        audioManager = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
//        audioManager.setStreamVolume(audioManager.STREAM_VOICE_CALL,0,0);

        loadFragment(new FragmentMyStat());
        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(this);


    }

    private ActivityMain2Binding mBinding;
    private void createBinding() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main2);
        mBinding.setViewModel(new Main2ViewModel());
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.page_1:
                fragment = new FragmentMyStat();
                break;

            case R.id.page_2:
                fragment = new FragmentCallLog();
                break;

            case R.id.page_3:
                fragment = new FragmentNews();
                break;

            case R.id.page_4:
                fragment = new FragmentSetting();
                break;
        }


        return loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frameLayout, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}