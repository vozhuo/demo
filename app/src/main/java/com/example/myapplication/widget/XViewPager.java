package com.example.myapplication.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import com.example.myapplication.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class XViewPager extends ViewPager {

    private boolean mScrollableFlag;

    public XViewPager(@NonNull Context context) {
        this(context,null);
    }

    public XViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.XViewPager);
        mScrollableFlag = typedArray.getBoolean(R.styleable.XViewPager_canscroll,true);
        typedArray.recycle();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (mScrollableFlag){
            return super.onInterceptTouchEvent(ev);
        }else{
            return false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mScrollableFlag){
            return super.onTouchEvent(ev);
        }else{
            return false;
        }
    }
}
