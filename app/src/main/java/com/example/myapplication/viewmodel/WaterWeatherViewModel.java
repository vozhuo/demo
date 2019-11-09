package com.example.myapplication.viewmodel;

import com.example.myapplication.entity.WaterWeatherEntity;
import com.example.myapplication.event.WaterWeatherEvent;
import com.example.myapplication.model.MonitorDataModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WaterWeatherViewModel extends ViewModel {

    private MutableLiveData<WaterWeatherEntity> mObservableWaterWeatherData;

    public WaterWeatherViewModel() {
        this.mObservableWaterWeatherData = new MutableLiveData<>();
        EventBus.getDefault().register(this);
    }

    public MutableLiveData<WaterWeatherEntity> getObservableWaterWeatherData() {
        return mObservableWaterWeatherData;
    }

    public void queryWaterWeatherData(){
        MonitorDataModel.getInstance().queryWaterWeatherData();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onWaterWeatherEvent(WaterWeatherEvent event){
        mObservableWaterWeatherData.setValue(event.getData());
    }
}
