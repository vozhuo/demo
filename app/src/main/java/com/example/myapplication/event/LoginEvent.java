package com.example.myapplication.event;

import com.example.myapplication.entity.LoginResp;

public class LoginEvent {

    private LoginResp loginResp;

    public LoginEvent(LoginResp loginResp){
        this.loginResp = loginResp;
    }

    public LoginResp getLoginResp() {
        return loginResp;
    }
}
