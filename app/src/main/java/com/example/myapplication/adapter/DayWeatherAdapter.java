package com.example.myapplication.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.myapplication.R;
import com.example.myapplication.entity.FutureHourWeatherEntity;
import com.example.myapplication.entity.SingleDayWeatherData;
import com.example.myapplication.util.WeatherUtils;

import androidx.annotation.NonNull;

public class DayWeatherAdapter extends BaseQuickAdapter<SingleDayWeatherData, BaseViewHolder> {

    public DayWeatherAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, SingleDayWeatherData item) {
        helper.setImageResource(R.id.item_future_day_weather_iv,WeatherUtils.parseWeatherTypeToResId(item.getWeatherType()));
        helper.setText(R.id.item_future_day_weather_type_tv,WeatherUtils.parseWeatherTypeToString(item.getWeatherType()));
        helper.setText(R.id.item_future_day_weather_temperature_range_tv,WeatherUtils.formatTemperatureRange(item.getMinTemperature(),item.getMaxTemperature()));
        helper.setText(R.id.item_future_day_weather_date_tv,WeatherUtils.formatDayOfWeek(item.getDate()));
    }
}
