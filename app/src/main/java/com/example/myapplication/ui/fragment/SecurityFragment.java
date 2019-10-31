package com.example.myapplication.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.SecurityAdapter;
import com.example.myapplication.entity.SecurityEntity;

import java.util.ArrayList;
import java.util.List;

public class SecurityFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_security, container, false);

        SecurityAdapter mAdapter = new SecurityAdapter(dataList());

        RecyclerView mRecyclerView = root.findViewById(R.id.rv_security);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);

        TextView tv1 = root.findViewById(R.id.tv_top1);
        TextView tv2 = root.findViewById(R.id.tv_top2);
        tv1.setText("功能区");
        tv2.setText("状态");

        return root;
    }

    private List<SecurityEntity> dataList() {
        return new ArrayList<SecurityEntity>() {{
            add(new SecurityEntity("挡水坝", 0));
            add(new SecurityEntity("溢流坝", 0));
            add(new SecurityEntity("引水系统", 0));
            add(new SecurityEntity("水库水质", 2));
            add(new SecurityEntity("库区安全", 1));
        }};
    }


}
