package com.example.myapplication.ui.activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import androidx.annotation.Nullable;

public class DamOffsetDistributionActivity extends BaseActivity {

    private ImageView mCalendarBtn;
    private TextView mCalendarTv;
    private TextView mSubTitleTv;
    private Calendar mCalendar;
    private TextView mMapTv1;
    private TextView mMapTv2;
    private TextView mMapTv3;
    private ImageView mMapIv1;
    private ImageView mMapIv2;
    private ImageView mMapIv3;

    private int mType;
    private int mOrientation;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_dam_offset_distribution_main;
    }

    @Override
    protected boolean useSupportedToolbar() {
        return true;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        mType = getIntent().getIntExtra("DeformationType",DamDeformationActivity.TYPE_SURFACE_DEFORMATION);
        mOrientation = getIntent().getIntExtra("DeformationOrientation",DamDeformationActivity.ORIENTATION_HORIZONTAL);
        mSubTitleTv = findViewById(R.id.tv_sub_header_center);
        mCalendarBtn = findViewById(R.id.iv_date_picker);
        mCalendarTv = findViewById(R.id.tv_date);
        mMapTv1 = findViewById(R.id.activity_dam_offset_distribution_main_map_tv1);
        mMapTv2 = findViewById(R.id.activity_dam_offset_distribution_main_map_tv2);
        mMapTv3 = findViewById(R.id.activity_dam_offset_distribution_main_map_tv3);
        mMapIv1 = findViewById(R.id.activity_dam_offset_distribution_main_map_iv1);
        mMapIv2 = findViewById(R.id.activity_dam_offset_distribution_main_map_iv2);
        mMapIv3 = findViewById(R.id.activity_dam_offset_distribution_main_map_iv3);

        getToolbar().setTitle(mType == DamDeformationActivity.TYPE_SURFACE_DEFORMATION ?
                R.string.activity_dam_deformation_surface_title_text : R.string.activity_dam_deformation_internel_title_text);
        if (mType == DamDeformationActivity.TYPE_SURFACE_DEFORMATION){
            mSubTitleTv.setText(mOrientation == DamDeformationActivity.ORIENTATION_HORIZONTAL ? R.string.activity_dam_offset_distribution_surface_horizontal_subtitle_text
                    :R.string.activity_dam_offset_distribution_surface_vertical_subtitle_text);
        }else{
            mSubTitleTv.setText(mOrientation == DamDeformationActivity.ORIENTATION_HORIZONTAL ? R.string.activity_dam_offset_distribution_internel_horizontal_subtitle_text
                    :R.string.activity_dam_offset_distribution_internel_vertical_subtitle_text);
        }

        mCalendarTv.setText(SimpleDateFormat.getDateInstance().format(new Date()));
        mCalendar = Calendar.getInstance();
        mCalendarBtn.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(DamOffsetDistributionActivity.this,
                    (view, year, month, dayOfMonth) -> {
                        mCalendar.set(Calendar.YEAR, year);
                        mCalendar.set(Calendar.MONTH, month);
                        mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        mCalendarTv.setText(SimpleDateFormat.getDateInstance().format(mCalendar.getTime()));

                    }, mCalendar.get(Calendar.YEAR),
                    mCalendar.get(Calendar.MONTH),
                    mCalendar.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
        });
        if (mType == DamDeformationActivity.TYPE_SURFACE_DEFORMATION){
            initSurfaceView();
        }else {
            initInternelView();
        }
    }

    @Override
    protected void initData() {

    }

    private void initSurfaceView(){
        mMapTv1.setVisibility(View.GONE);
        mMapTv2.setVisibility(View.GONE);
        mMapTv3.setVisibility(View.GONE);
        mMapIv2.setVisibility(View.GONE);
        mMapIv3.setVisibility(View.GONE);

        if (mOrientation == DamDeformationActivity.ORIENTATION_HORIZONTAL){
            mMapIv1.setImageResource(R.mipmap.ic_dam_surface_horizontal_offset);
        }else {
            mMapIv1.setImageResource(R.mipmap.ic_dam_surface_vertical_offset);
        }
    }

    @SuppressLint("SetTextI18n")
    private void initInternelView(){
        mMapTv1.setText("0+17.760剖面：");
        mMapTv2.setText("0+76.185剖面：");
        mMapTv3.setText("0+138.757剖面：");
        if (mOrientation == DamDeformationActivity.ORIENTATION_HORIZONTAL){
            mMapIv1.setImageResource(R.mipmap.ic_dam_internel_horizontal_offset_pic1);
            mMapIv2.setImageResource(R.mipmap.ic_dam_internel_horizontal_offset_pic2);
            mMapIv3.setImageResource(R.mipmap.ic_dam_internel_horizontal_offset_pic3);
        }else {
            mMapIv1.setImageResource(R.mipmap.ic_dam_internel_vertical_offset_pic1);
            mMapIv2.setImageResource(R.mipmap.ic_dam_internel_vertical_offset_pic2);
            mMapIv3.setImageResource(R.mipmap.ic_dam_internel_vertical_offset_pic3);
        }
    }
}
