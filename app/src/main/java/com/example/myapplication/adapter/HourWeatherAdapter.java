package com.example.myapplication.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.myapplication.R;
import com.example.myapplication.entity.FutureHourWeatherEntity;
import com.example.myapplication.util.WeatherUtils;
import androidx.annotation.NonNull;

public class HourWeatherAdapter extends BaseQuickAdapter<FutureHourWeatherEntity, BaseViewHolder> {

    public HourWeatherAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, FutureHourWeatherEntity item) {
        helper.setText(R.id.item_future_hour_weather_hour_tv,WeatherUtils.formatHour(item.getDate()));
        helper.setText(R.id.item_future_hour_weather_temp_tv, WeatherUtils.formatTemperature(item.getHourTemperature()));
        helper.setImageResource(R.id.item_future_hour_weather_iv,WeatherUtils.parseWeatherTypeToResId(item.getHourWeatherType()));
    }
}
