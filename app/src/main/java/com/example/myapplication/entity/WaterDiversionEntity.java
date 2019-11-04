package com.example.myapplication.entity;

import java.util.List;

public class WaterDiversionEntity {
    private String name;
    private List<BasicEntity> waterlist;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BasicEntity> getWaterlist() {
        return waterlist;
    }

    public void setWaterlist(List<BasicEntity> waterlist) {
        this.waterlist = waterlist;
    }

    public WaterDiversionEntity(String name, List<BasicEntity> waterlist) {
        this.name = name;
        this.waterlist = waterlist;
    }
}
