package com.example.myapplication.entity;

import com.example.myapplication.R;

import java.util.List;

public class WaterWeatherEntity{

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

    public static String formatTemperature(float temperature){
        return ((int)temperature) + "°";
    }

    public static String formatTemperatureRange(float minTemp,float maxTemp){
        return ((int)minTemp) + "°/" + ((int)maxTemp) + "°";
    }

    public static String formatHour(int index){
        if (index < 10){
            return "0" + index + ":00";
        }else {
            return index + ":00";
        }
    }

    public static int parseWeatherTypeToResId(int weatherType){
        switch (weatherType){
            case WeatherType.TYPE_CLOUDY:{
                return R.mipmap.icon_weather_cloudy_128px;
            }

            case WeatherType.TYPE_LIGHT_RAIN:{
                return R.mipmap.icon_weather_light_rain_128px;
            }

            case WeatherType.TYPE_MODERATE_RAIN:{
                return R.mipmap.icon_weather_moderate_rain_128px;
            }

            case WeatherType.TYPE_HEAVY_RAIN:{
                return R.mipmap.icon_weather_heavy_rain_128px;
            }

            default:
            case WeatherType.TYPE_SUNSINE:{
                return R.mipmap.icon_weather_sunshine_128px;
            }
        }
    }

    public static String parseWeatherTypeToString(int weatherType){
        switch (weatherType){
            case WeatherType.TYPE_CLOUDY:{
                return "多云";
            }

            case WeatherType.TYPE_LIGHT_RAIN:{
                return "小雨";
            }

            case WeatherType.TYPE_MODERATE_RAIN:{
                return "中雨";
            }

            case WeatherType.TYPE_HEAVY_RAIN:{
                return "大雨";
            }

            default:
            case WeatherType.TYPE_SUNSINE:{
                return "晴";
            }
        }
    }

    public static class WeatherType{
        public static final int TYPE_SUNSINE = 0;   //晴天
        public static final int TYPE_CLOUDY = 1;    //多云
        public static final int TYPE_LIGHT_RAIN = 2;//小雨
        public static final int TYPE_MODERATE_RAIN = 3;//中雨
        public static final int TYPE_HEAVY_RAIN = 4;   //大雨
    }
}
