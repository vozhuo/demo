package com.example.myapplication.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.WaterQualityAdapter;
import com.example.myapplication.entity.BasicEntity;

import java.util.ArrayList;
import java.util.List;

public class WaterQualityActivity extends AppCompatActivity {
    private Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_quality);
        mToolbar = findViewById(R.id.common_toolbar);
        mToolbar.setTitle(R.string.activity_water_quality_title);
        mToolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white);
        mToolbar.setNavigationOnClickListener(v -> finish());

        WaterQualityAdapter mAdapter = new WaterQualityAdapter(dataList());

        RecyclerView mRecyclerView = findViewById(R.id.rv_water_quality);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

        Button button = findViewById(R.id.btn_water_qgraph);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(this, WaterDiversionGraphActivity.class);
//            intent.putExtra("activity_title", R.string.activity_water_quality_title);
            Bundle bundle = new Bundle();
            bundle.putString("activity_title", getString(R.string.activity_water_quality_title));
            intent.putExtras(bundle);
            startActivity(intent);
        });
    }

    private List<BasicEntity> dataList() {
        return new ArrayList<BasicEntity>() {{
            add(new BasicEntity("总氮", "0.79mg/L"));
            add(new BasicEntity("总磷", "0.19mg/L"));
            add(new BasicEntity("pH", "7.8"));
            add(new BasicEntity("电导率", "1902us/cm"));
            add(new BasicEntity("溶解氧", "4.7mg/L"));
            add(new BasicEntity("浑浊度", "0.91NTU"));
            add(new BasicEntity("高锰酸盐指数", "5.7mg/L"));
        }};
    }
}
