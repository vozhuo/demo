package com.example.myapplication.ui.fragment;

import android.os.Bundle;
import android.util.Log;
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
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

public class DataFragment extends Fragment implements View.OnClickListener {

    private BaseQuickAdapter baseQuickAdapter;
    private MaterialCardView mReservoirLevelCard;   //水库水位
    private MaterialCardView mDamDeformationCard;   //大坝变形
    private MaterialCardView mHydroMeteorologyCard; //水文气象
    private MaterialCardView mDamStressCard;        //大坝应力
    private MaterialCardView mDamSeepageCard;       //大坝渗流
    private MaterialCardView mWaterDiversionSystemCard;//引水系统
    private MaterialCardView mVideoSurveillanceCard;   //视频监控
    private MaterialCardView mUAVCard;              //无人机
    private MaterialCardView mWaterQualityCard;     //水库水质

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_data, container, false);
        initViews(root);
        return root;
    }

    private void initViews(View rootView){
        mReservoirLevelCard = rootView.findViewById(R.id.fragment_data_reservoir_level_cardview);
        mDamDeformationCard = rootView.findViewById(R.id.fragment_data_dam_deformation_cardview);
        mHydroMeteorologyCard = rootView.findViewById(R.id.fragment_data_hydro_meteorology_cardview);
        mDamStressCard = rootView.findViewById(R.id.fragment_data_dam_stress_cardview);
        mDamSeepageCard = rootView.findViewById(R.id.fragment_data_dam_seepage_cardview);
        mWaterDiversionSystemCard = rootView.findViewById(R.id.fragment_data_water_diversion_system_cardview);
        mVideoSurveillanceCard = rootView.findViewById(R.id.fragment_data_video_surveillance_cardview);
        mUAVCard = rootView.findViewById(R.id.fragment_data_uav_cardview);
        mWaterQualityCard = rootView.findViewById(R.id.fragment_data_water_quality_cardview);

        mReservoirLevelCard.setOnClickListener(this);
        mDamDeformationCard.setOnClickListener(this);
        mHydroMeteorologyCard.setOnClickListener(this);
        mDamStressCard.setOnClickListener(this);
        mDamSeepageCard.setOnClickListener(this);
        mWaterDiversionSystemCard.setOnClickListener(this);
        mVideoSurveillanceCard.setOnClickListener(this);
        mUAVCard.setOnClickListener(this);
        mWaterQualityCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fragment_data_reservoir_level_cardview:{
                break;
            }

            case R.id.fragment_data_dam_deformation_cardview:{
                break;
            }

            case R.id.fragment_data_hydro_meteorology_cardview:{
                break;
            }

            case R.id.fragment_data_dam_stress_cardview:{
                break;
            }

            case R.id.fragment_data_dam_seepage_cardview:{
                break;
            }

            case R.id.fragment_data_water_diversion_system_cardview:{
                break;
            }

            case R.id.fragment_data_video_surveillance_cardview:{
                break;
            }

            case R.id.fragment_data_uav_cardview:{
                break;
            }

            case R.id.fragment_data_water_quality_cardview:{
                break;
            }
        }
    }
}