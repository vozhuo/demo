package com.example.myapplication.app;

/**
 *  APP常量定义类
 */
public class AppConst {

    public final static class LoginStateType{
        public static final int LOGIN_FAILURE_PSW_ERROR = 1;
        public static final int LOGIN_FAILURE_NO_RESPONSE = 2;
    }

    public final static class RegisterStateType{
        public static final int REGISTER_FAILURE_USERNAME_EXISTED = 1;
        public static final int REGISTER_FAILURE_CAPTCHA_UNMATCHED = 2;
        public static final int REGISTER_FAILURE_NO_RESPONSE = 3;
    }
}
