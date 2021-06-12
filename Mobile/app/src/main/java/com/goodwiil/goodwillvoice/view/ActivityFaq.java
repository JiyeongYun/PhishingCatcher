package com.goodwiil.goodwillvoice.view;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.goodwiil.goodwillvoice.R;
import com.goodwiil.goodwillvoice.databinding.ActivityFaqBinding;
import com.goodwiil.goodwillvoice.viewModel.FaqViewModel;

public class ActivityFaq extends AppCompatActivity {

    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createBinding();

    }

    private ActivityFaqBinding mBinding;
    private void createBinding() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_faq);
        mBinding.setViewModel(new FaqViewModel());
    }
}
