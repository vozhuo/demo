package com.example.myapplication.entity;

import java.util.Date;

public class UAVVideoItemEntity {

    private Date mUploadDate;
    private String mLocation;
    private int mVideoShortcut;
    private int mVideoUrl;

    public UAVVideoItemEntity(Date uploadDate, String location, int videoShortcut, int videoUrl) {
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

    public int getVideoUrl() {
        return mVideoUrl;
    }

    public void setVideoUrl(int videoUrl) {
        this.mVideoUrl = videoUrl;
    }
}
