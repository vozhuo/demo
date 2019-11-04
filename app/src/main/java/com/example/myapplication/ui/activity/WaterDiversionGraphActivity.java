package com.example.myapplication.ui.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class WaterDiversionGraphActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_diversion_graph);

        String name = getIntent().getStringExtra("water_info");

        TextView tv = findViewById(R.id.tv_left);
        tv.setText(name);
    }
}
