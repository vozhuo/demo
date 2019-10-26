package com.example.myapplication.viewmodel;

import com.example.myapplication.entity.LoginInfo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {

    private final MutableLiveData<LoginInfo> mObservableLoginInfo;

    public LoginViewModel(){
        mObservableLoginInfo = new MutableLiveData<>();
    }

    public LiveData<LoginInfo> getLoginInfo(){
        return mObservableLoginInfo;
    }

    public void login(String username,String password){
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setSucceed(true);
        mObservableLoginInfo.postValue(loginInfo);
    }
}
