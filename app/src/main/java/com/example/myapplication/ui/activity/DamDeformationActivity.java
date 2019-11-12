package com.example.myapplication.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;

public class DamDeformationActivity extends BaseActivity {

    private TextView mSubTitleTv;
    private Button mHorizontalBtn;
    private Button mVertivalBtn;
    private AppCompatSpinner mSpinner;
    private ImageView mImageView;
    private ArrayAdapter<String> mAdapter;
    private List<String> mData;

    private int mType;

    private String[] mSurfaceMonitors = new String[]{"监测点","LD-1","LD-2","LD-3","LD-4","LD-5","LD-6","LD-7","LD-8",
            "LD-9","LD-10","LD-11","LD-12","LD-13","LD-14","LD-15","LD-16","LD-17","LD-18","LD-19","LD-20",};
    private String[] mInternelMonitors = new String[]{"监测点","TZ-1","TZ-2","TZ-3","TZ-4","TZ-5","TZ-6","TZ-7","TZ-8"
            ,"TZ-9","TZ-10","TZ-11","TZ-12","TZ-13","TZ-14","TZ-15","TZ-16","TZ-17","TZ-18"};

    public static final int TYPE_SURFACE_DEFORMATION = 1;
    public static final int TYPE_INTERNEL_DEFORMATION = 2;
    @Override
    protected int getContentViewId() {
        return R.layout.activity_dam_deformation_main;
    }

    @Override
    protected boolean useSupportedToolbar() {
        return true;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        mType = getIntent().getIntExtra("DeformationType",TYPE_SURFACE_DEFORMATION);

        mSubTitleTv = findViewById(R.id.tv_sub_header_center);
        mHorizontalBtn = findViewById(R.id.activity_dam_deformation_main_horizontal_btn);
        mVertivalBtn = findViewById(R.id.activity_dam_deformation_main_vertical_btn);
        mSpinner = findViewById(R.id.activity_dam_deformation_main_spinner);
        mImageView = findViewById(R.id.activity_dam_deformation_main_map_iv);

        if (mType == TYPE_SURFACE_DEFORMATION) {
            mSubTitleTv.setText(R.string.activity_dam_deformation_surface_subtitle_text);
            getToolbar().setTitle(R.string.activity_dam_deformation_surface_title_text);
            mImageView.setImageResource(R.mipmap.dam_surface_monitor_map);
            mData = Arrays.asList(mSurfaceMonitors);
        }else {
            mSubTitleTv.setText(R.string.activity_dam_deformation_internel_subtitle_text);
            getToolbar().setTitle(R.string.activity_dam_deformation_internel_title_text);
            mImageView.setImageResource(R.mipmap.dam_internel_monitor_map);
            mData = Arrays.asList(mInternelMonitors);
        }

        mAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item, mData);
        mSpinner.setAdapter(mAdapter);
        mSpinner.setSelection(0);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0){
                    Intent intent = new Intent(DamDeformationActivity.this,DamMonitorGraphActivity.class);
                    intent.putExtra("DeformationType",mType);
                    intent.putExtra("MonitorName",mData.get(position));
                    startActivity(intent);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    protected void initData() {

    }
}
