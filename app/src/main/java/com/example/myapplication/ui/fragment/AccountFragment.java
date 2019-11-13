package com.example.myapplication.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.SettingAdapter;
import com.example.myapplication.ui.activity.LoginActivity;

import java.util.Arrays;

public class AccountFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_account, container, false);

        SettingAdapter mAdapter = new SettingAdapter(Arrays.asList("账号与安全", "通用", "帮助", "关于西大智慧"));

        RecyclerView mRecyclerView = root.findViewById(R.id.rv_setting);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);

        TextView tv_user_name = root.findViewById(R.id.tv_user_name);
        // 此处需获取用户名
        tv_user_name.append("");

        Button btn_logout = root.findViewById(R.id.btn_logout);
        // 退出登录
        btn_logout.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
            getActivity().finish();
        });

        return root;
    }

}
