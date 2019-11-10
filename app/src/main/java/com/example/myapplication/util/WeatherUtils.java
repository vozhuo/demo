package com.example.myapplication.util;

import android.text.format.DateUtils;

import com.example.myapplication.R;
import com.example.myapplication.entity.WaterWeatherEntity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class WeatherUtils {

    private static SimpleDateFormat sDayOfWeekFormatter = new SimpleDateFormat("E·");
    private static SimpleDateFormat sHourOfDayFormatter = new SimpleDateFormat("HH:00");

    public static String formatTemperature(float temperature){
        return ((int)temperature) + "°";
    }

    public static String formatTemperatureRange(float minTemp,float maxTemp){
        return ((int)minTemp) + "°/" + ((int)maxTemp) + "°";
    }

    public static String formatHour(Date date){
        return sHourOfDayFormatter.format(date);
    }

    public static String formatDayOfWeek(Date date){
        if (DateUtils.isToday(date.getTime())){
            return "今天·";
        }else{
            return sDayOfWeekFormatter.format(date);
        }
    }

    public static int parseWeatherTypeToResId(int weatherType){
        switch (weatherType){
            case WaterWeatherEntity.WeatherType.TYPE_CLOUDY:{
                return R.mipmap.icon_weather_cloudy_128px;
            }

            case WaterWeatherEntity.WeatherType.TYPE_LIGHT_RAIN:{
                return R.mipmap.icon_weather_light_rain_128px;
            }

            case WaterWeatherEntity.WeatherType.TYPE_MODERATE_RAIN:{
                return R.mipmap.icon_weather_moderate_rain_128px;
            }

            case WaterWeatherEntity.WeatherType.TYPE_HEAVY_RAIN:{
                return R.mipmap.icon_weather_heavy_rain_128px;
            }

            default:
            case WaterWeatherEntity.WeatherType.TYPE_SUNSINE:{
                return R.mipmap.icon_weather_sunshine_128px;
            }
        }
    }

    public static String parseWeatherTypeToString(int weatherType){
        switch (weatherType){
            case WaterWeatherEntity.WeatherType.TYPE_CLOUDY:{
                return "多云";
            }

            case WaterWeatherEntity.WeatherType.TYPE_LIGHT_RAIN:{
                return "小雨";
            }

            case WaterWeatherEntity.WeatherType.TYPE_MODERATE_RAIN:{
                return "中雨";
            }

            case WaterWeatherEntity.WeatherType.TYPE_HEAVY_RAIN:{
                return "大雨";
            }

            default:
            case WaterWeatherEntity.WeatherType.TYPE_SUNSINE:{
                return "晴";
            }
        }
    }

}
