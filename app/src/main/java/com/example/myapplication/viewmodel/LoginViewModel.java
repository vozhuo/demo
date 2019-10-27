package com.example.myapplication.viewmodel;

import com.example.myapplication.entity.LoginInfo;
import com.example.myapplication.event.LoginEvent;
import com.example.myapplication.model.UserModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {

    private final MutableLiveData<LoginInfo> mObservableLoginInfo;

    public LoginViewModel(){
        mObservableLoginInfo = new MutableLiveData<>();
        EventBus.getDefault().register(this);
    }

    public LiveData<LoginInfo> getLoginInfo(){
        return mObservableLoginInfo;
    }

    public void login(String username,String password){
        UserModel.getInstance().login(username,password);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginEvent(LoginEvent event){
        mObservableLoginInfo.setValue(event.getLoginInfo());
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        EventBus.getDefault().unregister(this);
    }
}
