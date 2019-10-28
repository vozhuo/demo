package com.example.myapplication.viewmodel;

import com.example.myapplication.entity.RegisterRequest;
import com.example.myapplication.entity.RegisterResp;
import com.example.myapplication.event.RegisterEvent;
import com.example.myapplication.model.UserModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RegisterViewModel extends ViewModel {

    private MutableLiveData<RegisterResp> mObservableRegisterResp;

    public RegisterViewModel(){
        mObservableRegisterResp = new MutableLiveData<>();
        EventBus.getDefault().register(this);
    }

    public LiveData<RegisterResp> getObservableRegisterResp() {
        return mObservableRegisterResp;
    }

    public void register(RegisterRequest registerRequest){
        UserModel.getInstance().register(registerRequest);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRegisterRespEvent(RegisterEvent event){
        mObservableRegisterResp.setValue(event.getRegisterResp());
    }
}
