package com.example.myapplication.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.app.AppConst;
import com.example.myapplication.entity.RetrievePasswordReq;
import com.example.myapplication.entity.RetrievePasswordResp;
import com.example.myapplication.viewmodel.RetrievePasswordViewModel;

import java.lang.ref.WeakReference;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class RetrievePasswordActivity extends BaseActivity implements View.OnClickListener {

    private EditText mUsernameEt;
    private EditText mPhoneOrEmailEt;
    private EditText mCaptchaEt;
    private EditText mPasswordEt;
    private EditText mConfirmPswEt;
    private Button mGetCaptchaBtn;
    private Button mConfirmBtn;
    private RetrievePasswordViewModel mViewModel;
    private int mCountDown = 60;    //发送验证码的倒计时 60s
    private RetrievePasswordActivityHandler mHandler;
    private static final int COUNT_DOWN_MESSAGE = 0x123;
    private static final int ONE_SECOND = 1000;
    @Override
    protected int getContentViewId() {
        return R.layout.activity_retrieve_password_main;
    }

    @Override
    protected boolean useSupportedToolbar() {
        return true;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        initViews();
    }

    @Override
    protected void initData() {
        subscribeUI();
    }

    private void initViews(){
        mHandler = new RetrievePasswordActivityHandler(this);
        mUsernameEt = findViewById(R.id.activity_retrieve_password_main_username_et);
        mPhoneOrEmailEt = findViewById(R.id.activity_retrieve_password_main_phonenumber_et);
        mCaptchaEt = findViewById(R.id.activity_retrieve_password_main_captcha_et);
        mPasswordEt = findViewById(R.id.activity_retrieve_password_main_password_et);
        mConfirmPswEt = findViewById(R.id.activity_retrieve_password_main_confirm_password_et);
        mGetCaptchaBtn = findViewById(R.id.activity_retrieve_password_main_get_captcha_button);
        mConfirmBtn = findViewById(R.id.activity_retrieve_password_main_confirm_button);

        mGetCaptchaBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);
        getToolbar().setTitle("找回密码");
    }

    private void subscribeUI(){
        mViewModel = ViewModelProviders.of(this).get(RetrievePasswordViewModel.class);
        mViewModel.getObservableRetrievePasswordResp().observe(this, new Observer<RetrievePasswordResp>() {
            @Override
            public void onChanged(RetrievePasswordResp retrievePasswordResp) {
                if (retrievePasswordResp.isSucceed()){
                    Toast.makeText(RetrievePasswordActivity.this,R.string.retrieve_password_activity_retrieve_successfully,Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    if (retrievePasswordResp.getRegisterStateType() == AppConst.RegisterStateType.REGISTER_FAILURE_USERNAME_EXISTED){
                        Toast.makeText(RetrievePasswordActivity.this,R.string.retrieve_password_activity_retrieve_failure_username_not_exist,Toast.LENGTH_SHORT).show();
                    }
                    mConfirmBtn.setEnabled(true);
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.activity_retrieve_password_main_get_captcha_button:{
                startCountDown();
                break;
            }

            case R.id.activity_retrieve_password_main_confirm_button:{
                confirm();
                break;
            }
        }
    }

    private void confirm(){
        boolean allItemsValid = true;
        if (TextUtils.isEmpty(mUsernameEt.getText())){
            mUsernameEt.setError(getResources().getText(R.string.register_activity_no_username));
            allItemsValid = false;
        }

        if (TextUtils.isEmpty(mPhoneOrEmailEt.getText())){
            mPhoneOrEmailEt.setError(getResources().getText(R.string.register_activity_no_phone));
            allItemsValid = false;
        }

        if (TextUtils.isEmpty(mCaptchaEt.getText())){
            mCaptchaEt.setError(getResources().getText(R.string.register_activity_no_captcha));
            allItemsValid = false;
        }

        if (TextUtils.isEmpty(mPasswordEt.getText())){
            mPasswordEt.setError(getResources().getText(R.string.register_activity_no_password));
            allItemsValid = false;
        }

        if (TextUtils.isEmpty(mConfirmPswEt.getText())){
            mConfirmPswEt.setError(getResources().getText(R.string.register_activity_no_confirm_password));
            allItemsValid = false;
        }

        if (!mPasswordEt.getText().toString().equals(mConfirmPswEt.getText().toString())){
            mConfirmPswEt.setError(getResources().getText(R.string.register_activity_password_unmatched));
            allItemsValid = false;
        }

        if (!allItemsValid) return;

        mConfirmBtn.setEnabled(false);
        RetrievePasswordReq req = new RetrievePasswordReq(mUsernameEt.getText().toString(),mPhoneOrEmailEt.getText().toString(),
                mCaptchaEt.getText().toString(),mPasswordEt.getText().toString(),mConfirmPswEt.getText().toString());
        mViewModel.retrievePassword(req);
    }

    private void startCountDown(){
        Toast.makeText(this,R.string.register_activity_sent_captcha,Toast.LENGTH_SHORT).show();

        mGetCaptchaBtn.setEnabled(false);
        mGetCaptchaBtn.setTextColor(getResources().getColor(R.color.activity_register_main_captcha_button_disable_color));
        mGetCaptchaBtn.setText(mCountDown + "秒");
        mHandler.sendEmptyMessageDelayed(COUNT_DOWN_MESSAGE,ONE_SECOND);
    }

    private void resetCountDown(){
        mCountDown = 60;
        mGetCaptchaBtn.setEnabled(true);
        mGetCaptchaBtn.setTextColor(getResources().getColor(R.color.activity_register_main_captcha_button_normal_color));
        mGetCaptchaBtn.setText(getResources().getText(R.string.activity_register_main_get_captcha_text));
    }

    static class RetrievePasswordActivityHandler extends Handler {

        WeakReference<RetrievePasswordActivity> mActivity;

        RetrievePasswordActivityHandler(RetrievePasswordActivity activity){
            mActivity = new WeakReference<>(activity);
        }

        public RetrievePasswordActivity getActivity(){
            return mActivity.get();
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == COUNT_DOWN_MESSAGE){
                if (getActivity() != null){
                    if (getActivity().mCountDown > 0) {
                        getActivity().mCountDown--;
                        getActivity().mGetCaptchaBtn.setText(getActivity().mCountDown + "秒");
                        sendEmptyMessageDelayed(COUNT_DOWN_MESSAGE, ONE_SECOND);
                    }else {
                        getActivity().resetCountDown();
                    }
                }
            }
        }
    }
}
