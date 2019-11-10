package com.example.myapplication.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.myapplication.R;
import com.example.myapplication.entity.SingleHourReservoirLevelEntity;
import com.example.myapplication.util.WeatherUtils;

import androidx.annotation.NonNull;

public class ReservoirLevelAdapter extends BaseQuickAdapter<SingleHourReservoirLevelEntity,BaseViewHolder> {

    public ReservoirLevelAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, SingleHourReservoirLevelEntity item) {
        helper.setText(R.id.item_hour_reservoir_level_time_tv, WeatherUtils.formatCompleteDate(item.getDate()));
        helper.setText(R.id.item_hour_reservoir_level_data_tv,String.format("%.2fm",item.getReservoirLevel()));
    }
}
