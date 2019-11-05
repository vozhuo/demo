package com.example.myapplication.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.myapplication.R;
import com.example.myapplication.ui.activity.DeviceStateActivity;
import com.google.android.material.card.MaterialCardView;

public class DeviceFragment extends Fragment implements View.OnClickListener {
    private BaseQuickAdapter baseQuickAdapter;
    private MaterialCardView mWaterLevel;           //水库水位
    private MaterialCardView mDamDeformation;       //大坝变形
    private MaterialCardView mDamStress;            //大坝应力
    private MaterialCardView mDamSeepage;           //大坝渗流
    private MaterialCardView mWaterDiversionSystem; //引水系统
    private MaterialCardView mVideoSurveillance;    //视频监控
    private MaterialCardView mWaterQuality;         //水库水质

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_device, container, false);
        initViews(root);
        return root;
    }

    private void initViews(View rootView){
        mWaterLevel = rootView.findViewById(R.id.fragment_device_water_level);
        mDamDeformation = rootView.findViewById(R.id.fragment_device_dam_deformation);
        mDamStress = rootView.findViewById(R.id.fragment_device_dam_stress);
        mDamSeepage = rootView.findViewById(R.id.fragment_device_dam_seepage);
        mWaterDiversionSystem = rootView.findViewById(R.id.fragment_device_water_diversion_system);
        mVideoSurveillance = rootView.findViewById(R.id.fragment_device_video_surveillance);
        mWaterQuality = rootView.findViewById(R.id.fragment_device_water_quality);

        mWaterLevel.setOnClickListener(this);
        mDamDeformation.setOnClickListener(this);
        mDamStress.setOnClickListener(this);
        mDamSeepage.setOnClickListener(this);
        mWaterDiversionSystem.setOnClickListener(this);
        mVideoSurveillance.setOnClickListener(this);
        mWaterQuality.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fragment_device_water_level:{
                Intent intent = new Intent();
                intent.setClass(getContext(), DeviceStateActivity.class);
                intent.putExtra("title", "设备-水库水位");
                startActivity(intent);
                break;
            }

            case R.id.fragment_device_dam_deformation:{
                Intent intent = new Intent(getContext(), DeviceStateActivity.class);
                intent.putExtra("title", "设备-大坝变形");
                startActivity(intent);
                break;
            }

            case R.id.fragment_device_dam_stress:{
                Intent intent = new Intent(getContext(), DeviceStateActivity.class);
                intent.putExtra("title", "设备-大坝应力");
                startActivity(intent);
                break;
            }

            case R.id.fragment_device_dam_seepage:{
                Intent intent = new Intent(getContext(), DeviceStateActivity.class);
                intent.putExtra("title", "设备-大坝渗流");
                startActivity(intent);
                break;
            }

            case  R.id.fragment_device_water_diversion_system:{
                Intent intent = new Intent(getContext(), DeviceStateActivity.class);
                intent.putExtra("title", "设备-引水系统");
                startActivity(intent);
                break;
            }

            case R.id.fragment_device_video_surveillance:{
                Intent intent = new Intent(getContext(), DeviceStateActivity.class);
                intent.putExtra("title", "设备-视频监控");
                startActivity(intent);
                break;
            }

            case R.id.fragment_device_water_quality:{
                Intent intent = new Intent(getContext(), DeviceStateActivity.class);
                intent.putExtra("title", "设备-水库水质");
                startActivity(intent);
                break;
            }
        }
    }
}
