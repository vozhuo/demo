package com.example.myapplication.entity;

import java.util.Date;

public class SingleDayDamOffsetEntity {

    private float damOffset;
    private Date date;

    public SingleDayDamOffsetEntity(float damOffset, Date date) {
        this.damOffset = damOffset;
        this.date = date;
    }

    public float getDamOffset() {
        return damOffset;
    }

    public Date getDate() {
        return date;
    }
}
