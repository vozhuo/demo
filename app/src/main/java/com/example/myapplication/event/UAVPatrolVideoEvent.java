package com.example.myapplication.event;

import com.example.myapplication.entity.UAVVideoItemEntity;

import java.util.List;

public class UAVPatrolVideoEvent {
    private List<UAVVideoItemEntity> mData;

    public UAVPatrolVideoEvent(List<UAVVideoItemEntity> data) {
        this.mData = data;
    }

    public List<UAVVideoItemEntity> getData() {
        return mData;
    }
}
