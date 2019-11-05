package com.example.myapplication.entity;

import java.io.Serializable;

public class BasicEntity implements Serializable {
    private String number;
    private String value;

    public BasicEntity(String number, String value) {
        this.number = number;
        this.value = value;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
