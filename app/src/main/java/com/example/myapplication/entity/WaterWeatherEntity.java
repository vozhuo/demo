package com.example.myapplication.entity;

import java.util.List;

public class WaterWeatherEntity{

    private List<FutureHourWeatherEntity> futureHourWeatherData;
    private List<SingleDayWeatherData> monthWeatherData;
    private List<SingleDayHydrologyData> monthHydrologyData;

    public List<SingleDayWeatherData> getMonthWeatherData() {
        return monthWeatherData;
    }

    public void setMonthWeatherData(List<SingleDayWeatherData> monthWeatherData) {
        this.monthWeatherData = monthWeatherData;
    }

    public List<SingleDayHydrologyData> getMonthHydrologyData() {
        return monthHydrologyData;
    }

    public void setMonthHydrologyData(List<SingleDayHydrologyData> monthHydrologyData) {
        this.monthHydrologyData = monthHydrologyData;
    }

    public List<FutureHourWeatherEntity> getFutureHourWeatherData() {
        return futureHourWeatherData;
    }

    public void setFutureHourWeatherData(List<FutureHourWeatherEntity> futureHourWeatherData) {
        this.futureHourWeatherData = futureHourWeatherData;
    }

    public static class WeatherType{
        public static final int TYPE_SUNSINE = 0;   //晴天
        public static final int TYPE_CLOUDY = 1;    //多云
        public static final int TYPE_LIGHT_RAIN = 2;//小雨
        public static final int TYPE_MODERATE_RAIN = 3;//中雨
        public static final int TYPE_HEAVY_RAIN = 4;   //大雨
    }
}
