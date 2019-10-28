package com.example.myapplication.event;

import com.example.myapplication.entity.RegisterResp;

public class RegisterEvent {
    private RegisterResp registerResp;

    public RegisterEvent(RegisterResp registerResp) {
        this.registerResp = registerResp;
    }

    public RegisterResp getRegisterResp() {
        return registerResp;
    }
}
