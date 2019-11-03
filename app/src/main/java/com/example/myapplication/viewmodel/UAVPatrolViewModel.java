package com.example.myapplication.viewmodel;

import com.example.myapplication.entity.UAVVideoItemEntity;
import com.example.myapplication.event.UAVPatrolVideoEvent;
import com.example.myapplication.model.MonitorDataModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Date;
import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UAVPatrolViewModel extends ViewModel {

    private MutableLiveData<List<UAVVideoItemEntity>> mObservableUAVVideoItemEntity;

    public UAVPatrolViewModel(){
        mObservableUAVVideoItemEntity = new MutableLiveData<>();
        EventBus.getDefault().register(this);
    }

    public MutableLiveData<List<UAVVideoItemEntity>> getObservableUAVVideoItemEntity() {
        return mObservableUAVVideoItemEntity;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUAVPatrolVideoEvent(UAVPatrolVideoEvent event){
        mObservableUAVVideoItemEntity.setValue(event.getData());
    }

    public void queryVideoByDate(Date date){
        MonitorDataModel.getInstance().queryUAVVideobyDate(date);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        EventBus.getDefault().unregister(this);
    }
}
