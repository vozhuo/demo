package com.example.myapplication.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.myapplication.R;
import com.example.myapplication.adapter.VideoSurveillanceAdapter;
import com.example.myapplication.entity.VideoSurveillanceEntity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class VideoSurveillanceActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private List<VideoSurveillanceEntity> mDataList;

    private VideoSurveillanceAdapter mAdapter;
    private static final String TAG = "VideoActivity";

    private SimpleDateFormat mFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Override
    protected int getContentViewId() {
        return R.layout.activity_video_surveillance_main;
    }

    @Override
    protected boolean useSupportedToolbar() {
        return true;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        generateData();
        initViews();
    }

    @Override
    protected void initData() {

    }

    @SuppressLint("LongLogTag")
    private void initViews(){
        getToolbar().setTitle(R.string.activity_video_surveillance_title_text);

        mRecyclerView = findViewById(R.id.activity_video_surveillance_recyclerview);
        mAdapter = new VideoSurveillanceAdapter(mDataList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Log.d(VideoSurveillanceActivity.TAG,"Item Click.Position is " + position);
                VideoSurveillanceEntity entity = (VideoSurveillanceEntity) adapter.getData().get(position);
                String uri = "android.resource://" + getPackageName() + "/" + entity.getVideoFilePath();
                String title = entity.getLocation();


                Intent intent = new Intent(VideoSurveillanceActivity.this,VideoPlayerActivity.class);
                intent.putExtra("VideoUri",uri);
                intent.putExtra("VideoTitle",title);
                startActivity(intent);

            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    private void generateData(){
        mDataList = new ArrayList<>();

        VideoSurveillanceEntity entity1 = new VideoSurveillanceEntity("AE01","输水隧洞A11#",R.mipmap.video_shortcut1,R.raw.video_uav_1);
        mDataList.add(entity1);

        VideoSurveillanceEntity entity2 = new VideoSurveillanceEntity("AE02","输水隧洞A12#",R.mipmap.video_shortcut2,R.raw.video_uav_2);
        mDataList.add(entity2);

        VideoSurveillanceEntity entity3 = new VideoSurveillanceEntity("AE03","水力发电机房1-01",R.mipmap.video_shortcut3,R.raw.video_uav_1);
        mDataList.add(entity3);

        VideoSurveillanceEntity entity4 = new VideoSurveillanceEntity("AE04","水力发电机房1-02",R.mipmap.video_shortcut4,R.raw.video_uav_2);
        mDataList.add(entity4);

        VideoSurveillanceEntity entity5 = new VideoSurveillanceEntity("AE05","水力发电机房2-01",R.mipmap.video_shortcut5,R.raw.video_uav_1);
        mDataList.add(entity5);

        VideoSurveillanceEntity entity6 = new VideoSurveillanceEntity("AE06","水力发电机房2-02",R.mipmap.video_shortcut6,R.raw.video_uav_2);
        mDataList.add(entity6);

        VideoSurveillanceEntity entity7 = new VideoSurveillanceEntity("AE07","水力发电机房3-01",R.mipmap.video_shortcut7,R.raw.video_uav_1);
        mDataList.add(entity7);

        VideoSurveillanceEntity entity8 = new VideoSurveillanceEntity("AE08","水力发电机房3-02",R.mipmap.video_shortcut8,R.raw.video_uav_2);
        mDataList.add(entity8);

    }
}
