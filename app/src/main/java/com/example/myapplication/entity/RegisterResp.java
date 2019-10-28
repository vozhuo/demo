package com.example.myapplication.entity;

public class RegisterResp {

    private boolean succeed;
    private int registerStateType;

    public boolean isSucceed() {
        return succeed;
    }

    public void setSucceed(boolean succeed) {
        this.succeed = succeed;
    }

    public int getRegisterStateType() {
        return registerStateType;
    }

    public void setRegisterStateType(int registerStateType) {
        this.registerStateType = registerStateType;
    }
}
