package com.example.myapplication.ui.activity;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.InflateException;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.myapplication.R;
import com.githang.statusbar.StatusBarCompat;

import androidx.annotation.ColorRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

/**
 *  Activity抽象基类，对Activity常见的操作抽取出公共部分。
 *  可以直接继承该类来实现自己的Activity。
 *
 *  实现了以下功能：
 *  (1)Toolbar标题栏的封装
 *  (2)设置状态栏颜色的封装
 *  (3)屏幕横竖屏切换的封装
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
        if (useSupportedToolbar()) {
            setupView();    //使用BaseActivity提供的toolbar
        }else {
            setupViewWithoutToolbar();  //不使用toolbar，布局相关操作仅仅交由子Activity完成
        }
        initView(savedInstanceState);
        initData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (getMenuResourceId() == 0)
            return false;
        else {
            getMenuInflater().inflate(getMenuResourceId(),menu);
            return true;
        }
    }

    protected abstract int getContentViewId();

    protected abstract boolean useSupportedToolbar();

    protected abstract void initView(@Nullable Bundle savedInstanceState);

    protected abstract void initData();

    private void setupView(){
        mRootView = new LinearLayout(this);
        mRootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mRootView.setOrientation(LinearLayout.VERTICAL);

        View view = getLayoutInflater().inflate(R.layout.common_toolbar,mRootView);
        mToolbar = view.findViewById(R.id.common_toolbar);
        setToolbarTitle(getTitle().toString());
        setSupportActionBar(mToolbar);
        mToolbar.setOverflowIcon(getResources().getDrawable(R.mipmap.ic_overflow_button_32px));
        mToolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white);
        mToolbar.setNavigationOnClickListener(this::onNavigationBackClick);
        mToolbar.setOnMenuItemClickListener(this::onMenuItemClick);
        mContentView = getLayoutInflater().inflate(getContentViewId(),mRootView);

        setContentView(mRootView);
        setScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setStatusbarColor(R.color.colorPrimary);
    }

    private void setupViewWithoutToolbar(){
        setContentView(getContentViewId());
        setScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setStatusbarColor(R.color.colorPrimary);
    }

    @Nullable
    protected final Toolbar getToolbar(){
        return mToolbar;
    }

    protected void onNavigationBackClick(View view){
        finish();
    }

    protected boolean onMenuItemClick(MenuItem menuItem){
        return false;
    }

    protected int getMenuResourceId(){
        return 0;
    }

    protected final void setToolbarTitle(String title){
        if (mToolbar != null){
            mToolbar.setTitle(title);
        }
    }

    protected final void setToolbarTitle(int titleResId){
        if (mToolbar != null){
            mToolbar.setTitle(titleResId);
        }
    }

    protected final void setStatusbarColor(String color){
        StatusBarCompat.setStatusBarColor(this,Color.parseColor(color));
    }

    protected final void setStatusbarColor(@ColorRes int colorResId){
        StatusBarCompat.setStatusBarColor(this,getResources().getColor(colorResId));
    }

    protected final void setScreenOrientation(int orientation){
        setRequestedOrientation(orientation);
    }
}
