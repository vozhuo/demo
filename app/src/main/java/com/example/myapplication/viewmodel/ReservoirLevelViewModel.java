package com.example.myapplication.viewmodel;

import android.util.Log;

import com.example.myapplication.entity.SingleDayReservoirLevelEntity;
import com.example.myapplication.event.ReservoirLevelEvent;
import com.example.myapplication.model.MonitorDataModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ReservoirLevelViewModel extends ViewModel {

    private MutableLiveData<List<SingleDayReservoirLevelEntity>> mObservaleReservoirLevelData;

    public ReservoirLevelViewModel() {
        mObservaleReservoirLevelData = new MutableLiveData<>();
        EventBus.getDefault().register(this);
    }

    public MutableLiveData<List<SingleDayReservoirLevelEntity>> getObservaleReservoirLevelData() {
        return mObservaleReservoirLevelData;
    }

    public void queryReservoirLevelData(){
        MonitorDataModel.getInstance().queryReservoirLevelData();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReservoirLevelEvent(ReservoirLevelEvent event){
        Log.d("ccc","Data size:" + event.getData().get(0).getAverageReservoirLevel());
        mObservaleReservoirLevelData.setValue(event.getData());
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        EventBus.getDefault().unregister(this);
    }
}
