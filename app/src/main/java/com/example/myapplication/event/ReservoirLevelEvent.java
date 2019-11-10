package com.example.myapplication.event;

import com.example.myapplication.entity.SingleDayReservoirLevelEntity;

import java.util.List;

public class ReservoirLevelEvent {

    private List<SingleDayReservoirLevelEntity> data;

    public ReservoirLevelEvent(List<SingleDayReservoirLevelEntity> data) {
        this.data = data;
    }

    public List<SingleDayReservoirLevelEntity> getData() {
        return data;
    }
}
