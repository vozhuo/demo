package com.example.myapplication.entity;

import java.util.Date;
import java.util.List;

public class SingleDayWeatherData {

    private float maxTemperature;
    private float minTemperature;
    private float avgTemperature;
    private int weatherType;
    private Date date;
    private List<Float> hoursTemperatureList;
    private List<Float> hoursSensibleTemperatureList;
    private List<Integer> weatherTypeList;

    public float getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(float maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public float getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(float minTemperature) {
        this.minTemperature = minTemperature;
    }

    public float getAvgTemperature() {
        return avgTemperature;
    }

    public void setAvgTemperature(float avgTemperature) {
        this.avgTemperature = avgTemperature;
    }

    public int getWeatherType() {
        return weatherType;
    }

    public void setWeatherType(int weatherType) {
        this.weatherType = weatherType;
    }

    public List<Float> getHoursTemperatureList() {
        return hoursTemperatureList;
    }

    public void setHoursTemperatureList(List<Float> hoursTemperatureList) {
        this.hoursTemperatureList = hoursTemperatureList;
    }

    public List<Float> getHoursSensibleTemperatureList() {
        return hoursSensibleTemperatureList;
    }

    public void setHoursSensibleTemperatureList(List<Float> hoursSensibleTemperatureList) {
        this.hoursSensibleTemperatureList = hoursSensibleTemperatureList;
    }

    public List<Integer> getWeatherTypeList() {
        return weatherTypeList;
    }

    public void setWeatherTypeList(List<Integer> weatherTypeList) {
        this.weatherTypeList = weatherTypeList;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
