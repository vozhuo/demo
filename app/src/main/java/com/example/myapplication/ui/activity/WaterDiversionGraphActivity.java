package com.example.myapplication.ui.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.WaterGraphAdapter;

import java.util.ArrayList;

public class WaterDiversionGraphActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_diversion_graph);
        Bundle b = getIntent().getExtras();
        assert b != null;

        Toolbar mToolbar = findViewById(R.id.common_toolbar);
        mToolbar.setTitle(b.getString("activity_title"));
        mToolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white);
        mToolbar.setNavigationOnClickListener(v -> finish());

        ArrayList<String> s = b.getStringArrayList("list");

        RecyclerView mRecyclerView = findViewById(R.id.rv_water_graph);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new WaterGraphAdapter(s));
    }
}
