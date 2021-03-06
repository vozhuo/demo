package com.example.myapplication.entity;

import java.util.Date;

public class SingleDayHydrologyData {

    private float rainfallCapacity;
    private float evaporationCapacity;
    private float inflowCapacity;
    private float usedWaterCapacity;
    private float amountOfWaterCapacity;
    private Date date;

    public float getRainfallCapacity() {
        return rainfallCapacity;
    }

    public void setRainfallCapacity(float rainfallCapacity) {
        this.rainfallCapacity = rainfallCapacity;
    }

    public float getEvaporationCapacity() {
        return evaporationCapacity;
    }

    public void setEvaporationCapacity(float evaporationCapacity) {
        this.evaporationCapacity = evaporationCapacity;
    }

    public float getInflowCapacity() {
        return inflowCapacity;
    }

    public void setInflowCapacity(float inflowCapacity) {
        this.inflowCapacity = inflowCapacity;
    }

    public float getUsedWaterCapacity() {
        return usedWaterCapacity;
    }

    public void setUsedWaterCapacity(float usedWaterCapacity) {
        this.usedWaterCapacity = usedWaterCapacity;
    }

    public float getAmountOfWaterCapacity() {
        return amountOfWaterCapacity;
    }

    public void setAmountOfWaterCapacity(float amountOfWaterCapacity) {
        this.amountOfWaterCapacity = amountOfWaterCapacity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
