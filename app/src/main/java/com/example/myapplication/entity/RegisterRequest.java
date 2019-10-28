package com.example.myapplication.entity;

public class RegisterRequest {
    private String username;
    private String phone;
    private String captcha;
    private String email;
    private String password;
    private String confirmPassword;

    public RegisterRequest(String username, String phone, String captcha, String email, String password, String confirmPassword) {
        this.username = username;
        this.phone = phone;
        this.captcha = captcha;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getUsername() {
        return username;
    }

    public String getPhone() {
        return phone;
    }

    public String getCaptcha() {
        return captcha;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }
}
