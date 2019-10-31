package com.example.myapplication.entity;

import java.util.Date;

public class WarningEntity {
    private String date;
    private String position;
    private String value;

    public WarningEntity() {
    }

    public WarningEntity(String date, String position, String value) {
        this.date = date;
        this.position = position;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public WarningEntity(String date, String value) {
        this.date = date;
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setData(String date) {
        this.date = date;
    }

    public String getPostiton() {
        return position;
    }

    public void setPostiton(String position) {
        this.position = position;
    }
}
