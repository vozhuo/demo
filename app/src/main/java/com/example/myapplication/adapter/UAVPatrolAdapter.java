package com.example.myapplication.adapter;

import android.annotation.SuppressLint;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.myapplication.R;
import com.example.myapplication.entity.UAVVideoItemEntity;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class UAVPatrolAdapter extends BaseQuickAdapter<UAVVideoItemEntity, BaseViewHolder> {

    private SimpleDateFormat mFormatter;

    @SuppressLint("SimpleDateFormat")
    public UAVPatrolAdapter(@Nullable List<UAVVideoItemEntity> data) {
        super(R.layout.activity_uav_patrol_item,data);
        mFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, UAVVideoItemEntity item) {
        helper.setText(R.id.activity_uav_patrol_item_date_tv,mFormatter.format(item.getUploadDate()))
                .setText(R.id.activity_uav_patrol_item_location_tv,item.getLocation())
                .setImageResource(R.id.activity_uav_patrol_item_iv,item.getVideoShortcut())
                .addOnClickListener(R.id.activity_uav_patrol_item_cardview);
    }
}
