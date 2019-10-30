package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.entity.LoginResp;
import com.example.myapplication.viewmodel.LoginViewModel;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "LoginActivity";
    private EditText mUsernameEt;
    private EditText mPasswordEt;
    private Button mLoginButton;
    private Button mFindPswButton;
    private Button mRegisterButton;
    private Button mTestButton;
    private View mRootView;

    private LoginViewModel mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);
        initViews();
        subscribeUI();
    }

    private void initViews(){
        mRootView = findViewById(R.id.activity_login_main_root_container);
        mUsernameEt = findViewById(R.id.activity_login_main_username_et);
        mPasswordEt = findViewById(R.id.activity_login_main_password_et);
        mLoginButton = findViewById(R.id.activity_login_main_login_btn);
        mFindPswButton = findViewById(R.id.activity_login_main_findpsw_btn);
        mRegisterButton = findViewById(R.id.activity_login_main_register_btn);
        mTestButton = findViewById(R.id.activity_login_main_test_btn);

        mLoginButton.setOnClickListener(this);
        mFindPswButton.setOnClickListener(this);
        mRegisterButton.setOnClickListener(this);
        mTestButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.activity_login_main_login_btn:{
                //----------Quick entrance to MainActivity---------
                Intent test = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(test);
                //doLogin();
                //--------------------------------------------------
                break;
            }

            case R.id.activity_login_main_findpsw_btn:{
                Intent intent1 = new Intent(LoginActivity.this,RetrievePasswordActivity.class);
                startActivity(intent1);
                break;
            }

            case R.id.activity_login_main_register_btn:{
                Intent intent2 = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent2);
                break;
            }

            case R.id.activity_login_main_test_btn:{
                Intent intent3 = new Intent(LoginActivity.this,TestActivity.class);
                startActivity(intent3);
                break;
            }
        }
    }

    private void subscribeUI(){
        mViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        mViewModel.getLoginInfo().observe(this, new Observer<LoginResp>() {
            @Override
            public void onChanged(LoginResp loginResp) {
                if (loginResp.isSucceed()){
                    //login successfully,go to the MainActivity
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);

                    //enable login button
                    mLoginButton.setEnabled(true);
                }else{
                    //login failure,show some error messages
                    Toast.makeText(LoginActivity.this,"账号或者密码错误，请重试！",Toast.LENGTH_SHORT).show();
                    //enable login button
                    mLoginButton.setEnabled(true);
                }
            }
        });
    }

    private void doLogin(){
        mLoginButton.setEnabled(false);
        String username = mUsernameEt.getText().toString();
        String password = mPasswordEt.getText().toString();

        if (username.equals("") || password.equals("")){
            Toast.makeText(LoginActivity.this,"账号或者密码不能为空，请重试",Toast.LENGTH_SHORT).show();
            mLoginButton.setEnabled(true);
            return;
        }

        mViewModel.login(username,password);
    }
}
