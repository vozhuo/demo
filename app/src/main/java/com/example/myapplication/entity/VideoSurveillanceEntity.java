package com.example.myapplication.entity;

public class VideoSurveillanceEntity {

    private String mSerialNumber;
    private String mLocation;
    private int mVideoShortcut;
    private int mVideoFilePath;

    public VideoSurveillanceEntity(String serialNumber, String location, int videoShortcut, int videoFilePath) {
        this.mSerialNumber = serialNumber;
        this.mLocation = location;
        this.mVideoShortcut = videoShortcut;
        this.mVideoFilePath = videoFilePath;
    }

    public String getSerialNumber() {
        return mSerialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.mSerialNumber = serialNumber;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        this.mLocation = location;
    }

    public int getVideoShortcut() {
        return mVideoShortcut;
    }

    public void setVideoShortcut(int videoShortcut) {
        this.mVideoShortcut = videoShortcut;
    }

    public int getVideoFilePath() {
        return mVideoFilePath;
    }

    public void setVideoFilePath(int videoFilePath) {
        this.mVideoFilePath = videoFilePath;
    }
}
