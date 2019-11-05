package com.example.myapplication.ui.activity;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.myapplication.R;
import com.githang.statusbar.StatusBarCompat;

import androidx.annotation.ColorRes;
import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

/**
 *  Activity抽象基类，对Activity常见的操作抽取出公共部分。
 *  可以直接继承该类来实现自己的Activity。
 *
 *  实现了以下功能：
 *  (1)Toolbar标题栏的封装
 *  (2)状态栏颜色的封装
 *
 * @author ChenYu
 * @since 2019.11.05
 * @version test-0.0.1
 * @apiNote This class is now developing.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private LinearLayout mRootView;
    private View mContentView;

    protected String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null){
            initParam(savedInstanceState);
        }else if (getIntent() != null && getIntent().getExtras() != null){
            initParam(getIntent().getExtras());
        }
        setupView();
        initView();
        initData();
    }

    protected abstract int getContentViewId();

    protected abstract void initView();

    protected abstract void initData();

    private void setupView(){
        mRootView = new LinearLayout(this);
        mRootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mRootView.setOrientation(LinearLayout.VERTICAL);

        View view = getLayoutInflater().inflate(R.layout.common_toolbar,mRootView);
        mToolbar = view.findViewById(R.id.common_toolbar);
        mToolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white);
        mToolbar.setNavigationOnClickListener(v -> {
            onNavigationBackClick();
            finish();
        });
        mContentView = getLayoutInflater().inflate(getContentViewId(),mRootView);

        setContentView(mRootView);
        setToolbarTitle(getTitle().toString());
        setScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setStatusbarColor(R.color.colorPrimary);
    }

    protected Toolbar getToolbar(){
        return mToolbar;
    }

    protected void onNavigationBackClick(){

    }

    protected void initParam(Bundle bundle){

    }

    protected void setToolbarTitle(String title){
        if (mToolbar != null){
            mToolbar.setTitle(title);
        }
    }

    protected void setToolbarTitle(int titleResId){
        if (mToolbar != null){
            mToolbar.setTitle(titleResId);
        }
    }

    protected void setStatusbarColor(String color){
        StatusBarCompat.setStatusBarColor(this,Color.parseColor(color));
    }

    protected void setStatusbarColor(@ColorRes int colorResId){
        StatusBarCompat.setStatusBarColor(this,getResources().getColor(colorResId));
    }

    protected void setScreenOrientation(int orientation){
        setRequestedOrientation(orientation);
    }


}
