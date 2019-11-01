package com.example.myapplication.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.myapplication.R;
import com.example.myapplication.entity.VideoSurveillanceEntity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class VideoSurveillanceAdapter extends BaseQuickAdapter<VideoSurveillanceEntity, BaseViewHolder> {

    public VideoSurveillanceAdapter(@Nullable List<VideoSurveillanceEntity> data) {
        super(R.layout.activity_video_surveillance_item, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, VideoSurveillanceEntity item) {
        helper.setText(R.id.activity_video_surveillance_number_tv,item.getSerialNumber())
                .setText(R.id.activity_video_surveillance_location_tv,item.getLocation())
                .setImageResource(R.id.activity_video_surveillance_iv,item.getVideoShortcut())
                .addOnClickListener(R.id.activity_video_surveillance_cardview);
    }

    @Override
    protected void bindViewClickListener(BaseViewHolder baseViewHolder) {
        super.bindViewClickListener(baseViewHolder);
    }
}
