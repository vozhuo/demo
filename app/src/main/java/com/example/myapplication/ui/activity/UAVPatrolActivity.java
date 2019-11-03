package com.example.myapplication.ui.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.ImageView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.UAVPatrolAdapter;
import com.example.myapplication.entity.UAVVideoItemEntity;
import com.example.myapplication.viewmodel.UAVPatrolViewModel;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class UAVPatrolActivity extends AppCompatActivity {

    private static final String TAG = "UAVPatrolActivity";
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private ImageView mSearchIconIv;
    private Calendar mCalendar;

    private UAVPatrolViewModel mViewModel;
    private UAVPatrolAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uav_patrol_main);
        initViews();
        initSettings();
    }

    private void initViews(){
        mCalendar = Calendar.getInstance();

        mToolbar = findViewById(R.id.common_toolbar);
        mRecyclerView = findViewById(R.id.activity_uav_patrol_recyclerview);
        mSearchIconIv = findViewById(R.id.activity_uav_patrol_search_iv);

        mToolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white);
        mToolbar.setNavigationOnClickListener(v -> finish());
        mToolbar.setTitle(R.string.activity_uav_patrol_title_text);

        mSearchIconIv.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                mViewModel.queryVideoByDate(calendar.getTime());
            },mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
        });

        mAdapter = new UAVPatrolAdapter(null);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initSettings(){
        mViewModel = ViewModelProviders.of(this).get(UAVPatrolViewModel.class);
        mViewModel.getObservableUAVVideoItemEntity().observe(this, uavVideoItemEntities -> {
            mAdapter.replaceData(uavVideoItemEntities);
        });

        mViewModel.queryVideoByDate(new Date());
    }
}
