package com.example.myapplication.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.myapplication.R;
import com.example.myapplication.entity.DeviceStateEntity;

import java.util.List;

public class DeviceStateAdapter extends BaseQuickAdapter<DeviceStateEntity, BaseViewHolder> {

    public DeviceStateAdapter(@Nullable List<DeviceStateEntity> device){
        super(R.layout.item_device_state,device);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, DeviceStateEntity item) {
        helper.setText(R.id.tv_dev_sta_txt, item.getDevice());
        switch (item.getState()) {
            case 0:
                helper.setImageDrawable(R.id.iv_dev_sta_img,
                        mContext.getResources().getDrawable(R.drawable.ic_device_state_green));
                break;
            case 1:
                helper.setImageDrawable(R.id.iv_dev_sta_img,
                        mContext.getResources().getDrawable(R.drawable.ic_device_state_black));
                break;
            case 2:
                helper.setImageDrawable(R.id.iv_dev_sta_img,
                        mContext.getResources().getDrawable(R.drawable.ic_device_state_red));
                break;
            default:
                break;
        }
    }
}
