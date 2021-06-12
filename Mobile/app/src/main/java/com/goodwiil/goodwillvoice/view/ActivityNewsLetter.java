package com.goodwiil.goodwillvoice.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.goodwiil.goodwillvoice.R;
import com.goodwiil.goodwillvoice.databinding.ActivityNewsLetterBinding;
import com.goodwiil.goodwillvoice.viewModel.FaqViewModel;

public class ActivityNewsLetter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        createBinding();

        Intent intent = getIntent();
        String url = intent.getExtras().getString("url");

        System.out.println(url);

        WebSettings webSettings = mBinding.wvNews.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);



        mBinding.wvNews.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });

        mBinding.wvNews.loadUrl(url);

    }


    private ActivityNewsLetterBinding mBinding;
    private void createBinding(){
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_news_letter);
    }
}