package com.example.myapplication.ui.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.example.myapplication.R;
import com.example.myapplication.adapter.UAVPatrolAdapter;
import com.example.myapplication.entity.UAVVideoItemEntity;
import com.example.myapplication.viewmodel.UAVPatrolViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class UAVPatrolActivity extends BaseActivity {

    private static final String TAG = "UAVPatrolActivity";
    private RecyclerView mRecyclerView;
    private ImageView mSearchIconIv;
    private TextView mDateTv;
    private Calendar mCalendar;

    private UAVPatrolViewModel mViewModel;
    private UAVPatrolAdapter mAdapter;

    private SimpleDateFormat mFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private String testUri = "http://vt1.doubanio.com/201904161504/37df49462f42733060e06dea8d38c3fe/view/movie/M/302440458.mp4";

    @Override
    protected int getContentViewId() {
        return R.layout.activity_uav_patrol_main;
    }

    @Override
    protected boolean useSupportedToolbar() {
        return true;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        setToolbarTitle(R.string.activity_uav_patrol_title_text);
        mCalendar = Calendar.getInstance();

        mRecyclerView = findViewById(R.id.activity_uav_patrol_recyclerview);
        mSearchIconIv = findViewById(R.id.iv_date_picker);
        mDateTv = findViewById(R.id.tv_date);

        mDateTv.setText(SimpleDateFormat.getDateInstance().format(mCalendar.getTime()));
        mSearchIconIv.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
                mCalendar.set(Calendar.YEAR,year);
                mCalendar.set(Calendar.MONTH,month);
                mCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                mDateTv.setText(SimpleDateFormat.getDateInstance().format(mCalendar.getTime()));
                mViewModel.queryVideoByDate(mCalendar.getTime());
            },mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
        });

        mAdapter = new UAVPatrolAdapter(null);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                Log.d(UAVPatrolActivity.TAG,"Item Click.Position is " + position);
                Intent intent = new Intent(UAVPatrolActivity.this,VideoPlayerActivity.class);
                UAVVideoItemEntity entity = (UAVVideoItemEntity) adapter.getData().get(position);
                String uri = "android.resource://" + getPackageName() + "/" + entity.getVideoUrl();
                String title = mFormatter.format(entity.getUploadDate()) + "    " + entity.getLocation();

                intent.putExtra("VideoUri",testUri);
                intent.putExtra("VideoTitle",title);
                startActivity(intent);
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        mViewModel = ViewModelProviders.of(this).get(UAVPatrolViewModel.class);
        mViewModel.getObservableUAVVideoItemEntity().observe(this, uavVideoItemEntities -> {
            mAdapter.replaceData(uavVideoItemEntities);
        });

        mViewModel.queryVideoByDate(new Date());
    }
}
