package com.example.myapplication.viewmodel;

import com.example.myapplication.entity.LoginResp;
import com.example.myapplication.event.LoginEvent;
import com.example.myapplication.model.UserModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {

    private final MutableLiveData<LoginResp> mObservableLoginInfo;

    public LoginViewModel(){
        mObservableLoginInfo = new MutableLiveData<>();
        EventBus.getDefault().register(this);
    }

    public LiveData<LoginResp> getLoginInfo(){
        return mObservableLoginInfo;
    }

    public void login(String username,String password){
        UserModel.getInstance().login(username,password);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginEvent(LoginEvent event){
        mObservableLoginInfo.setValue(event.getLoginResp());
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        EventBus.getDefault().unregister(this);
    }
}
