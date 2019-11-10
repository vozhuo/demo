package com.example.myapplication.widget;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 *  RecyclerView item decoration.This class achieves the margins between two items.
 */
public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int mMarginLeft;
    private int mMarginRight;
    private int mMarginTop;
    private int mMarginBottom;

    public SpaceItemDecoration(int marginLeft, int marginRight, int marginTop, int marginBottom) {
        this.mMarginLeft = marginLeft;
        this.mMarginRight = marginRight;
        this.mMarginTop = marginTop;
        this.mMarginBottom = marginBottom;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.left = mMarginLeft;
        outRect.right = mMarginRight;
        outRect.top = mMarginTop;
        outRect.bottom = mMarginBottom;
    }
}
