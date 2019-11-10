package com.example.myapplication.entity;

import java.util.Date;
import java.util.List;

public class SingleDayReservoirLevelEntity {

    private List<SingleHourReservoirLevelEntity> hourReservoirLevelList;
    private float averageReservoirLevel;
    private Date date;

    public List<SingleHourReservoirLevelEntity> getHourReservoirLevelList() {
        return hourReservoirLevelList;
    }

    public void setHourReservoirLevelList(List<SingleHourReservoirLevelEntity> hourReservoirLevelList) {
        this.hourReservoirLevelList = hourReservoirLevelList;
    }

    public float getAverageReservoirLevel() {
        return averageReservoirLevel;
    }

    public void setAverageReservoirLevel(float averageReservoirLevel) {
        this.averageReservoirLevel = averageReservoirLevel;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
