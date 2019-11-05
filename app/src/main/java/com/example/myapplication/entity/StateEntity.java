package com.example.myapplication.entity;

public class StateEntity {
    private String device;
    private String position;
    private String value;


    public StateEntity(String device, String position, String value) {
        this.device = device;
        this.position = position;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public StateEntity(String device, String value) {
        this.device = device;
        this.value = value;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getPostiton() {
        return position;
    }

    public void setPostiton(String position) {
        this.position = position;
    }

}
