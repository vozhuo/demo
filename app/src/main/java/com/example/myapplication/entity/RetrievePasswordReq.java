package com.example.myapplication.entity;

public class RetrievePasswordReq {
    private String username;
    private String phoneOrEmail;
    private String captcha;
    private String password;
    private String confirmPassword;

    public RetrievePasswordReq(String username, String phoneOrEmail, String captcha, String password, String confirmPassword) {
        this.username = username;
        this.phoneOrEmail = phoneOrEmail;
        this.captcha = captcha;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getUsername() {
        return username;
    }

    public String getPhoneOrEmail() {
        return phoneOrEmail;
    }

    public String getCaptcha() {
        return captcha;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }
}
