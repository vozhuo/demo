package com.example.myapplication.model;

import com.example.myapplication.app.AppConst;
import com.example.myapplication.entity.LoginInfo;
import com.example.myapplication.entity.RegisterRequest;
import com.example.myapplication.entity.RegisterResp;
import com.example.myapplication.event.LoginEvent;
import com.example.myapplication.event.RegisterEvent;

import org.greenrobot.eventbus.EventBus;

/**
 *  用户模型
 */
public class UserModel {

    private UserModel(){}

    public static UserModel getInstance(){
        return UserModelHolder.sInstance;
    }

    private static class UserModelHolder{
        private static final UserModel sInstance = new UserModel();
    }

    /**
     * Login into the server by using username and password.
     *
     * Note:this method may send a http request to server,please don't call it
     * in main thread.
     * @param username
     * @param password
     */
    public void login(String username,String password){
        //------start of test code---------
        LoginInfo loginInfo = new LoginInfo();
        if (username.equals("admin") && password.equals("admin")){
            loginInfo.setSucceed(true);
        }else{
            loginInfo.setSucceed(false);
            loginInfo.setLoginStateType(AppConst.LoginStateType.LOGIN_FAILURE_PSW_ERROR);
        }
        EventBus.getDefault().post(new LoginEvent(loginInfo));
        //------end of test code-----------
    }

    /**
     * send a registerRequest to the server.
     * @param registerRequest
     */
    public void register(RegisterRequest registerRequest){
        //------start of test code---------
        RegisterResp registerResp = new RegisterResp();
        if (registerRequest.getUsername().equals("admin")){
            registerResp.setSucceed(false);
            registerResp.setRegisterStateType(AppConst.RegisterStateType.REGISTER_FAILURE_USERNAME_EXISTED);
        }else{
            registerResp.setSucceed(true);
        }

        EventBus.getDefault().post(new RegisterEvent(registerResp));
        //------end of test code-----------
    }

}
