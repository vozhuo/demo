package com.example.myapplication.entity;

import java.util.Date;

public class UAVVideoItemEntity {

    private Date mUploadDate;
    private String mLocation;
    private int mVideoShortcut;
    private String mVideoUrl;

    public UAVVideoItemEntity(Date uploadDate, String location, int videoShortcut, String videoUrl) {
        this.mUploadDate = uploadDate;
        this.mLocation = location;
        this.mVideoShortcut = videoShortcut;
        this.mVideoUrl = videoUrl;
    }

    public Date getUploadDate() {
        return mUploadDate;
    }

    public void setmUploadDate(Date uploadDate) {
        this.mUploadDate = uploadDate;
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

    public String getVideoUrl() {
        return mVideoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.mVideoUrl = videoUrl;
    }
}
