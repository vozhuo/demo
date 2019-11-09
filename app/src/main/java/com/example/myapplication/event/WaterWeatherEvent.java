package com.example.myapplication.event;

import com.example.myapplication.entity.WaterWeatherEntity;

public class WaterWeatherEvent {

    private WaterWeatherEntity mData;

    public WaterWeatherEvent(WaterWeatherEntity data) {
        this.mData = data;
    }

    public WaterWeatherEntity getData() {
        return mData;
    }
}
