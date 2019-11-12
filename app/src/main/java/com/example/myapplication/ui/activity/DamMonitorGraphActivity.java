package com.example.myapplication.ui.activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import androidx.annotation.Nullable;

public class DamMonitorGraphActivity extends BaseActivity {

    private ImageView mCalendarBtn;
    private TextView mCalendarTv;
    private TextView mSubTitleTv;

    private Calendar mCalendar;
    private int mType;
    private String mMonitorName;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_dam_monitor_graph_main;
    }

    @Override
    protected boolean useSupportedToolbar() {
        return true;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        mType = getIntent().getIntExtra("DeformationType",DamDeformationActivity.TYPE_SURFACE_DEFORMATION);
        mMonitorName = getIntent().getStringExtra("MonitorName");

        mSubTitleTv = findViewById(R.id.tv_sub_header_center);
        mCalendarBtn = findViewById(R.id.iv_date_picker);
        mCalendarTv = findViewById(R.id.tv_date);

        getToolbar().setTitle(mType == DamDeformationActivity.TYPE_SURFACE_DEFORMATION ?
                R.string.activity_dam_deformation_surface_title_text : R.string.activity_dam_deformation_internel_title_text);
        mSubTitleTv.setText(mMonitorName + "监测点-曲线图");

        mCalendarTv.setText(SimpleDateFormat.getDateInstance().format(new Date()));
        mCalendar = Calendar.getInstance();
        mCalendarBtn.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(DamMonitorGraphActivity.this,
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
    }

    @Override
    protected void initData() {

    }
}
