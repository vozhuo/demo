package com.example.myapplication.entity;

public class SecurityEntity {
    private String name;
    private int state;

    public WarningEntity getWarningEntity() {
        return warningEntity;
    }

    public void setWarningEntity(WarningEntity warningEntity) {
        this.warningEntity = warningEntity;
    }

    private WarningEntity warningEntity;

    public SecurityEntity(String name, int state) {
        this.name = name;
        this.state = state;
    }

    public SecurityEntity(String name, WarningEntity warningEntity) {
        this.name = name;
        this.warningEntity = warningEntity;
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
