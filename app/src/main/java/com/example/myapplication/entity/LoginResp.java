package com.example.myapplication.entity;

public class LoginResp {

    private boolean succeed;
    private int loginStateType;

    public boolean isSucceed() {
        return succeed;
    }

    public void setSucceed(boolean succeed) {
        this.succeed = succeed;
    }

    public int getLoginStateType() {
        return loginStateType;
    }

    public void setLoginStateType(int loginStateType) {
        this.loginStateType = loginStateType;
    }
}
