package com.example.myapplication.ui.activity;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.DeviceStateAdapter;
import com.example.myapplication.entity.DeviceStateEntity;

import java.util.ArrayList;
import java.util.List;

public class DeviceStateActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private String titlevla;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        titlevla = intent.getStringExtra("title");
        setContentView(R.layout.activity_device_state);
        mToolbar = findViewById(R.id.common_toolbar);
        mToolbar.setTitle(titlevla);
        mToolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white);
        mToolbar.setNavigationOnClickListener(v -> finish());

        DeviceStateAdapter mAdapter = new DeviceStateAdapter(deviceList());
        RecyclerView mRecyclerView = findViewById(R.id.rv_dev_sta);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

    private List<DeviceStateEntity> deviceList() {
        return new ArrayList<DeviceStateEntity>() {{
            switch (titlevla) {
                case "设备-水库水位": {
                    add(new DeviceStateEntity("WM-1(大坝上游#1板块)", 0));
                    add(new DeviceStateEntity("WM-1(大坝上游#2板块)", 0));
                    add(new DeviceStateEntity("WM-1(大坝上游#3板块)", 0));
                    add(new DeviceStateEntity("WM-1(大坝上游#4板块)", 2));
                    add(new DeviceStateEntity("WM-1(大坝上游#5板块)", 1));
                    add(new DeviceStateEntity("WM-1(大坝上游#6板块)", 0));
                    add(new DeviceStateEntity("WM-1(大坝上游#7板块)", 2));
                    add(new DeviceStateEntity("WM-1(大坝上游#8板块)", 2));
                    add(new DeviceStateEntity("WM-1(大坝上游#9板块)", 0));
                    add(new DeviceStateEntity("WM-1(大坝上游#10板块)", 1));
                    add(new DeviceStateEntity("WM-1(大坝上游#11板块)", 0));
                    add(new DeviceStateEntity("WM-1(大坝上游#12板块)", 2));
                    add(new DeviceStateEntity("WM-1(大坝上游#13板块)", 0));
                    add(new DeviceStateEntity("WM-1(大坝上游#14板块)", 0));
                    add(new DeviceStateEntity("WM-1(大坝上游#15板块)", 1));
                    add(new DeviceStateEntity("WM-1(大坝上游#16板块)", 0));
                    add(new DeviceStateEntity("WM-1(大坝上游#17板块)", 2));
                    add(new DeviceStateEntity("WM-1(大坝上游#18板块)", 0));
                    add(new DeviceStateEntity("WM-1(大坝上游#19板块)", 2));
                    add(new DeviceStateEntity("WM-1(大坝上游#20板块)", 1));
                    break;
                }

                case "设备-大坝变形":{
                    add(new DeviceStateEntity("LD-1(桩号0+17.760,坝下0+0.668)", 0));
                    add(new DeviceStateEntity("LD-2(桩号0+47.640,坝下0+0.666)", 2));
                    add(new DeviceStateEntity("LD-3(桩号0+76.185,坝下0+0.663)", 0));
                    add(new DeviceStateEntity("LD-4(桩号0+10.575,坝下0+0.661)", 0));
                    add(new DeviceStateEntity("LD-5(桩号0+13.575,坝下0+0.661)", 1));
                    add(new DeviceStateEntity("LD-6(桩号0+17.760,坝下0+0.668)", 0));
                    add(new DeviceStateEntity("LD-7(桩号0+47.640,坝下0+0.666)", 0));
                    add(new DeviceStateEntity("LD-8(桩号0+76.185,坝下0+0.663)", 1));
                    add(new DeviceStateEntity("LD-9(桩号0+10.575,坝下0+0.661)", 0));
                    add(new DeviceStateEntity("LD-10(桩号0+18.57,坝下0+0.661)", 0));
                    add(new DeviceStateEntity("LD-11(桩号0+17.70,坝下0+0.668)", 2));
                    add(new DeviceStateEntity("LD-12(桩号0+47.60,坝下0+0.666)", 0));
                    add(new DeviceStateEntity("LD-13(桩号0+76.15,坝下0+0.663)", 0));
                    add(new DeviceStateEntity("LD-14(桩号0+10.55,坝下0+0.661)", 1));
                    add(new DeviceStateEntity("LD-15(桩号0+18.75,坝下0+0.661)", 0));
                    add(new DeviceStateEntity("LD-16(桩号0+17.70,坝下0+0.668)", 0));
                    add(new DeviceStateEntity("LD-17(桩号0+47.40,坝下0+0.666)", 0));
                    add(new DeviceStateEntity("LD-18(桩号0+76.85,坝下0+0.663)", 1));
                    add(new DeviceStateEntity("LD-19(桩号0+17.55,坝下0+0.661)", 0));
                    add(new DeviceStateEntity("LD-20(桩号0+18.55,坝下0+0.661)", 1));
                    add(new DeviceStateEntity("TZ-1(坝轴10，高程417.38)",0));
                    add(new DeviceStateEntity("TZ-2(坝轴10，高程417.38)",0));
                    add(new DeviceStateEntity("TZ-3(坝轴10，高程417.38)",0));
                    add(new DeviceStateEntity("TZ-4(坝轴10，高程417.38)",0));
                    add(new DeviceStateEntity("TZ-5(坝轴10，高程417.38)",0));
                    add(new DeviceStateEntity("TZ-6(坝轴10，高程417.38)",2));
                    add(new DeviceStateEntity("TZ-7(坝轴10，高程417.38)",0));
                    add(new DeviceStateEntity("TZ-8(坝轴10，高程417.38)",0));
                    add(new DeviceStateEntity("TZ-9(坝轴10，高程417.38)",0));
                    add(new DeviceStateEntity("TZ-10(坝轴10，高程417.38)",0));
                    add(new DeviceStateEntity("TZ-11(坝轴10，高程417.38)",0));
                    add(new DeviceStateEntity("TZ-12(坝轴10，高程417.38)",1));
                    add(new DeviceStateEntity("TZ-13(坝轴10，高程417.38)",0));
                    add(new DeviceStateEntity("TZ-14(坝轴10，高程417.38)",0));
                    add(new DeviceStateEntity("TZ-15(坝轴10，高程417.38)",0));
                    add(new DeviceStateEntity("TZ-16(坝轴10，高程417.38)",0));
                    break;
                }

                case "设备-大坝应力":{
                    add(new DeviceStateEntity("7T-1(桩号0+11.0,高程174)", 0));
                    add(new DeviceStateEntity("7T-2(桩号0+11.0,高程177)", 0));
                    add(new DeviceStateEntity("7T-3(桩号0+11.0,高程181)", 2));
                    add(new DeviceStateEntity("7T-4(桩号0+11.0,高程124)", 2));
                    add(new DeviceStateEntity("7T-5(桩号0+11.0,高程184)", 0));
                    add(new DeviceStateEntity("7T-6(桩号0+11.0,高程172)", 0));
                    add(new DeviceStateEntity("7T-7(桩号0+11.0,高程166)", 1));
                    add(new DeviceStateEntity("7T-8(桩号0+11.0,高程182)", 0));
                    add(new DeviceStateEntity("7T-9(桩号0+11.0,高程124)", 0));
                    add(new DeviceStateEntity("7T-10(桩号0+11.0,高程135)", 1));
                    add(new DeviceStateEntity("7T-11(桩号0+11.0,高程156)", 0));
                    add(new DeviceStateEntity("7T-12(桩号0+11.0,高程158)", 0));
                    add(new DeviceStateEntity("7T-13(桩号0+11.0,高程146)", 0));
                    add(new DeviceStateEntity("7T-14(桩号0+11.0,高程159)", 2));
                    add(new DeviceStateEntity("7T-15(桩号0+11.0,高程148)", 0));
                    add(new DeviceStateEntity("7T-16(桩号0+11.0,高程135)", 0));
                    add(new DeviceStateEntity("7T-17(桩号0+11.0,高程127)", 0));
                    add(new DeviceStateEntity("7T-18(桩号0+11.0,高程126)", 1));
                    add(new DeviceStateEntity("7T-19(桩号0+11.0,高程116)", 0));
                    add(new DeviceStateEntity("7T-20(桩号0+11.0,高程171)", 1));
                    break;
                }

                case "设备-大坝渗流":{
                    add(new DeviceStateEntity("G-1(大坝桩号0+101)", 0));
                    add(new DeviceStateEntity("G-2(大坝桩号0+102)", 1));
                    add(new DeviceStateEntity("G-3(大坝桩号0+103)", 0));
                    add(new DeviceStateEntity("G-4(大坝桩号0+104)", 0));
                    add(new DeviceStateEntity("G-5(大坝桩号0+105)", 0));
                    add(new DeviceStateEntity("G-6(大坝桩号0+106)", 0));
                    add(new DeviceStateEntity("G-7(大坝桩号0+107)", 0));
                    add(new DeviceStateEntity("G-8(大坝桩号0+108)", 2));
                    add(new DeviceStateEntity("G-9(大坝桩号0+109)", 0));
                    add(new DeviceStateEntity("G-10(大坝桩号0+200)", 1));
                    add(new DeviceStateEntity("G-11(大坝桩号0+201)", 0));
                    add(new DeviceStateEntity("G-12(大坝桩号0+202)", 0));
                    add(new DeviceStateEntity("G-13(大坝桩号0+203)", 2));
                    add(new DeviceStateEntity("G-14(大坝桩号0+204)", 2));
                    add(new DeviceStateEntity("G-15(大坝桩号0+205)", 2));
                    add(new DeviceStateEntity("G-16(大坝桩号0+206)", 0));
                    add(new DeviceStateEntity("G-17(大坝桩号0+207)", 2));
                    add(new DeviceStateEntity("G-18(大坝桩号0+208)", 1));
                    add(new DeviceStateEntity("G-19(大坝桩号0+209)", 0));
                    add(new DeviceStateEntity("G-20(大坝桩号0+301)", 1));
                    add(new DeviceStateEntity("G-21(大坝桩号0+302)", 1));
                    add(new DeviceStateEntity("G-22(大坝桩号0+303)", 2));
                    break;
                }

                case "设备-引水系统":{
                    add(new DeviceStateEntity("WZ-1(进水口进水段#1)", 0));
                    add(new DeviceStateEntity("WZ-2(进水口进水段#2)", 0));
                    add(new DeviceStateEntity("WZ-3(进水口进水段#3)", 0));
                    add(new DeviceStateEntity("WZ-4(进水口闸门段#1)", 1));
                    add(new DeviceStateEntity("WZ-5(进水口闸门段#2)", 0));
                    add(new DeviceStateEntity("WZ-6(进水口闸门段#3)", 0));
                    add(new DeviceStateEntity("WZ-7(进水口渐变段#1)", 0));
                    add(new DeviceStateEntity("WZ-8(进水口渐变段#2)", 0));
                    add(new DeviceStateEntity("WZ-9(进水口渐变段#3)", 0));
                    add(new DeviceStateEntity("WZ-10(进水口渐变段#4)", 2));
                    add(new DeviceStateEntity("WZ-11(引水明管#1)", 0));
                    add(new DeviceStateEntity("WZ-12(引水明管#2)", 0));
                    add(new DeviceStateEntity("WZ-13(引水明管#3)", 0));
                    add(new DeviceStateEntity("WZ-14(隧洞#1)", 0));
                    add(new DeviceStateEntity("WZ-15(隧洞#2)", 1));
                    add(new DeviceStateEntity("WZ-16(压力钢管#1)", 0));
                    add(new DeviceStateEntity("WZ-17(压力钢管#2)", 0));
                    add(new DeviceStateEntity("WZ-18(压力钢管#3)", 0));
                    add(new DeviceStateEntity("WZ-19(厂房01-01)", 0));
                    add(new DeviceStateEntity("WZ-20(厂房01-01)", 2));
                    add(new DeviceStateEntity("WZ-21(厂房01-01)", 0));
                    add(new DeviceStateEntity("WZ-22(厂房01-01)", 0));
                    add(new DeviceStateEntity("WZ-23(厂房01-01)", 0));
                    add(new DeviceStateEntity("WZ-24(厂房01-01)", 0));
                    add(new DeviceStateEntity("WZ-25(厂房01-01)", 2));
                    add(new DeviceStateEntity("WZ-26(蜗壳#2)", 0));
                    add(new DeviceStateEntity("WZ-27(蜗壳#3)", 1));
                    add(new DeviceStateEntity("WZ-28(蜗壳#1)", 0));
                    add(new DeviceStateEntity("WZ-29(尾水隧洞#2)", 0));
                    add(new DeviceStateEntity("WZ-30(尾水隧洞#3)", 0));
                    add(new DeviceStateEntity("WZ-31(尾水隧洞#2)", 0));
                    break;
                }

                case "设备-视频监控":{
                    add(new DeviceStateEntity("AE-1(输水隧洞#A11)", 0));
                    add(new DeviceStateEntity("AE-2(水利发电机房1-01)", 0));
                    add(new DeviceStateEntity("AE-3(水利发电机房1-02)", 0));
                    add(new DeviceStateEntity("AE-4(水利发电机房1-03)", 2));
                    add(new DeviceStateEntity("AE-5(水利发电机房1-04)", 0));
                    add(new DeviceStateEntity("AE-6(水利发电机房1-05)", 0));
                    add(new DeviceStateEntity("AE-7(水利发电机房1-06)", 0));
                    add(new DeviceStateEntity("AE-8(水利发电机房1-07)", 1));
                    add(new DeviceStateEntity("AE-9(输水隧洞#A12)", 0));
                    add(new DeviceStateEntity("AE-10(输水隧洞#A13)", 0));
                    add(new DeviceStateEntity("AE-11(输水隧洞#A15)", 2));
                    add(new DeviceStateEntity("AE-12(输水隧洞#A6)", 0));
                    add(new DeviceStateEntity("AE-13(输水隧洞#A48)", 0));
                    add(new DeviceStateEntity("AE-14(水库上游#12)", 2));
                    add(new DeviceStateEntity("AE-15(水库上游#15)", 1));
                    add(new DeviceStateEntity("AE-16(水库上游#18)", 2));
                    add(new DeviceStateEntity("AE-17(水库上游#14)", 0));
                    add(new DeviceStateEntity("AE-18(水库上游#11)", 0));
                    break;
                }

                case "设备-水库水质":{
                    add(new DeviceStateEntity("CBW-1(大坝上游#11)", 0));
                    add(new DeviceStateEntity("CBW-2(大坝上游#21)", 0));
                    add(new DeviceStateEntity("CBW-3(大坝上游#31)", 0));
                    add(new DeviceStateEntity("CBW-4(大坝上游#41)", 1));
                    add(new DeviceStateEntity("CBW-5(大坝上游#51)", 0));
                    add(new DeviceStateEntity("CBW-6(大坝上游#61)", 0));
                    add(new DeviceStateEntity("CBW-7(大坝上游#71)", 1));
                    add(new DeviceStateEntity("CBW-8(大坝上游#81)", 0));
                    add(new DeviceStateEntity("CBW-9(大坝上游#91)", 1));
                    add(new DeviceStateEntity("CBW-10(大坝上游101)", 0));
                    add(new DeviceStateEntity("CBW-11(大坝上游#111)", 2));
                    add(new DeviceStateEntity("CBW-12(大坝上游#121)", 0));
                    add(new DeviceStateEntity("CBW-13(大坝上游#131)", 0));
                    add(new DeviceStateEntity("CBW-14(大坝上游#141)", 0));
                    add(new DeviceStateEntity("CBW-15(大坝上游#151)", 2));
                    add(new DeviceStateEntity("CBW-16(大坝上游#161)", 0));
                    add(new DeviceStateEntity("CBW-17(大坝上游#171)", 0));
                    add(new DeviceStateEntity("CBW-18(大坝上游#181)", 0));
                    add(new DeviceStateEntity("CBW-19(大坝上游#191)", 0));
                    add(new DeviceStateEntity("CBW-20(大坝上游#201)", 2));
                    break;
                }
            }
        }};
    }
}
