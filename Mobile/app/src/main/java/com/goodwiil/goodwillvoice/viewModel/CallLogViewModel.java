package com.goodwiil.goodwillvoice.viewModel;

import android.app.Activity;
import android.view.View;

public class CallLogViewModel extends BaseViewModel{

    public void backBtnClick(View view) { ((Activity) view.getContext()).finish(); }

}
