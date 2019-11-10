package com.example.myapplication.entity;

import java.util.Date;
import java.util.List;

/**
 *  未来1小时的天气数据
 */
public class FutureHourWeatherEntity {
    private int hourTemperature;     //1小时气温数据
    private int hourWeatherType;     //1小时天气类型
    private Date date;         //时间

    public FutureHourWeatherEntity(int hourTemperature, int hourWeatherType, Date date) {
        this.hourTemperature = hourTemperature;
        this.hourWeatherType = hourWeatherType;
        this.date = date;
    }

    public int getHourTemperature() {
        return hourTemperature;
    }

    public void setHourTemperature(int hourTemperature) {
        this.hourTemperature = hourTemperature;
    }

    public int getHourWeatherType() {
        return hourWeatherType;
    }

    public void setHourWeatherType(int hourWeatherType) {
        this.hourWeatherType = hourWeatherType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
