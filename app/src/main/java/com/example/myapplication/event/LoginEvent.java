package com.example.myapplication.event;

import com.example.myapplication.entity.LoginInfo;

public class LoginEvent {

    private LoginInfo loginInfo;

    public LoginEvent(LoginInfo loginInfo){
        this.loginInfo = loginInfo;
    }

    public LoginInfo getLoginInfo() {
        return loginInfo;
    }
}
