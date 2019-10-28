package com.example.myapplication.entity;

public class RetrievePasswordResp {

    private boolean succeed;
    private int retrievePasswordStateType;

    public boolean isSucceed() {
        return succeed;
    }

    public void setSucceed(boolean succeed) {
        this.succeed = succeed;
    }

    public int getRegisterStateType() {
        return retrievePasswordStateType;
    }

    public void setRegisterStateType(int registerStateType) {
        this.retrievePasswordStateType = registerStateType;
    }
}
