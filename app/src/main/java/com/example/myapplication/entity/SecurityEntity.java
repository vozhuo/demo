package com.example.myapplication.entity;

import java.util.List;

public class SecurityEntity {
    private String name;
    private int state;
    private List<WarningEntity> warningList;

    public List<WarningEntity> getWarningList() {
        return warningList;
    }

    public void setWarningList(List<WarningEntity> warningList) {
        this.warningList = warningList;
    }

    public SecurityEntity(String name, int state) {
        this.name = name;
        this.state = state;
    }

    public SecurityEntity(String name, List<WarningEntity> warningList) {
        this.name = name;
        this.warningList = warningList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

}
