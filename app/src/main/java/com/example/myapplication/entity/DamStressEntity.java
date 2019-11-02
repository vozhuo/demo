package com.example.myapplication.entity;

import java.util.List;

public class DamStressEntity {
    private String name;
    private List<Double> value;

    public DamStressEntity(String name, List<Double> value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Double> getValue() {
        return value;
    }

    public void setValue(List<Double> value) {
        this.value = value;
    }
}
