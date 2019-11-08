package com.example.myapplication.ui.activity;

import android.os.Bundle;
import android.util.Log;

import com.example.myapplication.R;

public class TestActivity extends BaseActivity {

    @Override
    protected int getContentViewId() {
        return R.layout.activity_test_main;
    }

    @Override
    protected boolean useSupportedToolbar() {
        return true;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        Log.d(TAG,"Test init view");
    }

    @Override
    protected void initData() {

    }
}
