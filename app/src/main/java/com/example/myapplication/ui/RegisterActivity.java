package com.example.myapplication.ui;

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
import com.example.myapplication.entity.RegisterRequest;
import com.example.myapplication.entity.RegisterResp;
import com.example.myapplication.viewmodel.RegisterViewModel;

import java.lang.ref.WeakReference;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private RegisterViewModel mViewModel;

    private Toolbar mToolbar;
    private EditText mUsernameEt;
    private EditText mPhoneEt;
    private EditText mCaptchaEt;
    private EditText mEmailEt;
    private EditText mPasswordEt;
    private EditText mConfirmPswEt;
    private Button mGetCaptchaBtn;
    private Button mRegisterBtn;

    private int mCountDown = 60;    //发送验证码的倒计时 60s
    private RegisterActivityHandler mHandler;
    private static final int COUNT_DOWN_MESSAGE = 0x123;
    private static final int ONE_SECOND = 1000;

    private static final String TAG = "RegisterActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_main);
        initViews();
        subscribeUI();
    }

    private void initViews(){
        mHandler = new RegisterActivityHandler(this);

        mToolbar = findViewById(R.id.activity_register_main_toolbar);
        mUsernameEt = findViewById(R.id.activity_register_main_username_et);
        mPhoneEt = findViewById(R.id.activity_register_main_phonenumber_et);
        mCaptchaEt = findViewById(R.id.activity_register_main_captcha_et);
        mEmailEt = findViewById(R.id.activity_register_main_email_et);
        mPasswordEt = findViewById(R.id.activity_register_main_password_et);
        mConfirmPswEt = findViewById(R.id.activity_register_main_confirm_password_et);
        mGetCaptchaBtn = findViewById(R.id.activity_register_main_get_captcha_button);
        mRegisterBtn = findViewById(R.id.activity_register_main_register_button);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mGetCaptchaBtn.setOnClickListener(this);
        mRegisterBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.activity_register_main_get_captcha_button:{
                startCountDown();
                break;
            }

            case R.id.activity_register_main_register_button:{
                register();
                break;
            }
        }
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

    private void register(){
        boolean allItemsValid = true;
        if (TextUtils.isEmpty(mUsernameEt.getText())){
            mUsernameEt.setError(getResources().getText(R.string.register_activity_no_username));
            allItemsValid = false;
        }

        if (TextUtils.isEmpty(mPhoneEt.getText())){
            mPhoneEt.setError(getResources().getText(R.string.register_activity_no_phone));
            allItemsValid = false;
        }

        if (TextUtils.isEmpty(mCaptchaEt.getText())){
            mCaptchaEt.setError(getResources().getText(R.string.register_activity_no_captcha));
            allItemsValid = false;
        }

        if (TextUtils.isEmpty(mEmailEt.getText())){
            mEmailEt.setError(getResources().getText(R.string.register_activity_no_email));
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

        mRegisterBtn.setEnabled(false);
        RegisterRequest registerRequest = new RegisterRequest(mUsernameEt.getText().toString(),mPhoneEt.getText().toString(),
                mCaptchaEt.getText().toString(),mEmailEt.getText().toString(),mPasswordEt.getText().toString(),mConfirmPswEt.getText().toString());
        mViewModel.register(registerRequest);
    }

    private void subscribeUI(){
        mViewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);
        mViewModel.getObservableRegisterResp().observe(this, new Observer<RegisterResp>() {
            @Override
            public void onChanged(RegisterResp registerResp) {
                if (registerResp.isSucceed()){
                    Toast.makeText(RegisterActivity.this,R.string.register_activity_register_successfully,Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    if (registerResp.getRegisterStateType() == AppConst.RegisterStateType.REGISTER_FAILURE_USERNAME_EXISTED){
                        Toast.makeText(RegisterActivity.this,R.string.register_activity_register_failure_username_exist,Toast.LENGTH_SHORT).show();
                    }
                    mRegisterBtn.setEnabled(true);
                }
            }
        });
    }

    static class RegisterActivityHandler extends Handler{

        WeakReference<RegisterActivity> mActivity;

        RegisterActivityHandler(RegisterActivity activity){
            mActivity = new WeakReference<>(activity);
        }

        public RegisterActivity getActivity(){
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
