package com.example.myapplication.ui.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.WaterDetailAdapter;
import com.example.myapplication.adapter.WaterGraphApater;
import com.example.myapplication.entity.BasicEntity;
import com.example.myapplication.entity.WaterDiversionEntity;

import java.util.List;

public class WaterDiversionGraphActivity extends AppCompatActivity {
    private Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_diversion_graph);
        mToolbar = findViewById(R.id.common_toolbar);
        mToolbar.setTitle(getIntent().getStringExtra("activity_title"));
        mToolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white);
        mToolbar.setNavigationOnClickListener(v -> finish());

//        WaterDiversionEntity item = (WaterDiversionEntity) getIntent().getSerializableExtra("water_info");
//        List<BasicEntity> list = item.getWaterlist();
//
//        RecyclerView mRecyclerView = findViewById(R.id.rv_water_graph);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerView.setAdapter(new WaterGraphApater(list));

    }
}
