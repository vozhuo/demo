package com.example.myapplication.ui.activity;

import android.os.Bundle;
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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class RetrievePasswordActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar mToolbar;
    private EditText mUsernameEt;
    private EditText mPhoneOrEmailEt;
    private EditText mCaptchaEt;
    private EditText mPasswordEt;
    private EditText mConfirmPswEt;
    private Button mGetCaptchaBtn;
    private Button mConfirmBtn;
    private RetrievePasswordViewModel mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_password_main);
        initViews();
        subscribeUI();
    }

    private void initViews(){
        mToolbar = findViewById(R.id.activity_retrieve_password_main_toolbar);
        mUsernameEt = findViewById(R.id.activity_retrieve_password_main_username_et);
        mPhoneOrEmailEt = findViewById(R.id.activity_retrieve_password_main_phonenumber_et);
        mCaptchaEt = findViewById(R.id.activity_retrieve_password_main_captcha_et);
        mPasswordEt = findViewById(R.id.activity_retrieve_password_main_password_et);
        mConfirmPswEt = findViewById(R.id.activity_retrieve_password_main_confirm_password_et);
        mGetCaptchaBtn = findViewById(R.id.activity_retrieve_password_main_get_captcha_button);
        mConfirmBtn = findViewById(R.id.activity_retrieve_password_main_confirm_button);

        mGetCaptchaBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
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
}
