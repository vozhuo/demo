package com.example.myapplication.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

public class AdjustableVideoView extends VideoView {
    public AdjustableVideoView(Context context) {
        super(context);
    }

    public AdjustableVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AdjustableVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(widthMeasureSpec,heightMeasureSpec);
    }
}
