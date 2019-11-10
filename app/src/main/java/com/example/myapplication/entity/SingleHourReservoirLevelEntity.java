package com.example.myapplication.entity;

import java.util.Date;

public class SingleHourReservoirLevelEntity {

    private float reservoirLevel;
    private Date date;

    public float getReservoirLevel() {
        return reservoirLevel;
    }

    public void setReservoirLevel(float reservoirLevel) {
        this.reservoirLevel = reservoirLevel;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
