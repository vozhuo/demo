package com.example.myapplication.ui.activity;

import android.util.Log;

import com.example.myapplication.R;

public class TestActivity extends BaseActivity {

    @Override
    protected int getContentViewId() {
        return R.layout.activity_test_main;
    }

    @Override
    protected void initView() {
        Log.d(TAG,"Test init view");
    }

    @Override
    protected void initData() {

    }
}
