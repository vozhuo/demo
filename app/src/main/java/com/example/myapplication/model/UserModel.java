package com.example.myapplication.model;

import com.example.myapplication.app.AppConst;
import com.example.myapplication.entity.LoginResp;
import com.example.myapplication.entity.RegisterRequest;
import com.example.myapplication.entity.RegisterResp;
import com.example.myapplication.entity.RetrievePasswordReq;
import com.example.myapplication.entity.RetrievePasswordResp;
import com.example.myapplication.event.LoginEvent;
import com.example.myapplication.event.RegisterEvent;
import com.example.myapplication.event.RetrievePasswordEvent;

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
        LoginResp loginResp = new LoginResp();
        if (username.equals("admin") && password.equals("admin")){
            loginResp.setSucceed(true);
        }else{
            loginResp.setSucceed(false);
            loginResp.setLoginStateType(AppConst.LoginStateType.LOGIN_FAILURE_PSW_ERROR);
        }
        EventBus.getDefault().post(new LoginEvent(loginResp));
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

    public void retrievePassword(RetrievePasswordReq retrievePasswordReq){
        //------start of test code---------
        RetrievePasswordResp retrievePasswordResp = new RetrievePasswordResp();
        if (!retrievePasswordReq.getUsername().equals("admin")){
            retrievePasswordResp.setSucceed(false);
            retrievePasswordResp.setRegisterStateType(AppConst.RetrievePasswordStateType.RETRIEVE_PASSWORD_FAILURE_USERNAME_DONT_EXISTED);
        }else{
            retrievePasswordResp.setSucceed(true);
        }

        EventBus.getDefault().post(new RetrievePasswordEvent(retrievePasswordResp));
        //------end of test code-----------
    }
}
