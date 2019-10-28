package com.example.myapplication.entity;

import java.util.Date;

public class WarningEntity {
    private Date date;
    private String position;
    private String value;

    public WarningEntity(Date date, String position, String value) {
        this.date = date;
        this.position = position;
        this.value = value;
    }

    public WarningEntity(Date date, String value) {
        this.date = date;
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setData(Date date) {
        this.date = date;
    }

    public String getPostiton() {
        return position;
    }

    public void setPostiton(String position) {
        this.position = position;
    }
}
