package com.example.myapplication.event;

import com.example.myapplication.entity.RetrievePasswordResp;

public class RetrievePasswordEvent {

    private RetrievePasswordResp retrievePasswordResp;

    public RetrievePasswordEvent(RetrievePasswordResp retrievePasswordResp) {
        this.retrievePasswordResp = retrievePasswordResp;
    }

    public RetrievePasswordResp getRetrievePasswordResp() {
        return retrievePasswordResp;
    }
}
