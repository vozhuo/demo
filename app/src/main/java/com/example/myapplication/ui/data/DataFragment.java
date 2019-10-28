package com.example.myapplication.ui.data;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.myapplication.R;
import com.example.myapplication.adapter.HydAdapter;
import com.example.myapplication.entity.HydEntity;

import java.util.ArrayList;
import java.util.List;

public class DataFragment extends Fragment {

    private BaseQuickAdapter baseQuickAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_data, container, false);
//        final TextView textView = root.findViewById(R.id.text_dashboard);
//        dashboardViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

//        String info = DataProcess.getRow();
//        TextView tv = root.findViewById(R.id.textView);
//        tv.setText(info);

        HydAdapter mAdapter = new HydAdapter(dataList());

        RecyclerView mRecyclerView = root.findViewById(R.id.rv_hyd);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
        return root;
    }

    private List<HydEntity> dataList() {
        return new ArrayList<HydEntity>() {{
            add(new HydEntity("项目", "预报值"));
            add(new HydEntity("最高气温", "20"));
            add(new HydEntity("最低气温", "10"));
            add(new HydEntity("平均气温", "25"));
        }};
    }
}