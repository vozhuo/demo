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

    public final static class RetrievePasswordStateType{
        public static final int RETRIEVE_PASSWORD_FAILURE_USERNAME_DONT_EXISTED = 1;
        public static final int RETRIEVE_PASSWORD_FAILURE_CAPTCHA_UNMATCHED = 2;
    }

    /**
     *  定义图表数据的数据类型，比如大坝渗流的曲线图、水库水质的曲线图
     */
    public final static class GraphDataType{
        public static final int GRAPH_TYPE_DAM_SEEPAGE = 0x1001;
        public static final int GRAPH_TYPE_WATER_DIVERSION = 0x1002;
        public static final int GRAPH_TYPE_WATER_QUALITY = 0x1003;
    }
}
