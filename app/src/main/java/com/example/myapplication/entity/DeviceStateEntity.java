package com.example.myapplication.entity;

import java.util.List;

public class DeviceStateEntity {
    private String device;
    private int state;
    private List<StateEntity> stateList;

    public List<StateEntity> getStateList() { return stateList; }

    public void setStateList(List<StateEntity> stateList) {
        this.stateList = stateList;
    }

    public DeviceStateEntity(String device, int state) {
        this.device = device;
        this.state = state;
    }

    public DeviceStateEntity(String device, List<StateEntity> stateList) {
        this.device = device;
        this.stateList = stateList;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

}
