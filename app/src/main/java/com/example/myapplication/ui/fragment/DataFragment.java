package com.example.myapplication.ui.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.myapplication.R;
import com.example.myapplication.ui.activity.DamDeformationActivity;
import com.example.myapplication.ui.activity.DamSeepageActivity;
import com.example.myapplication.ui.activity.DamStressActivity;
import com.example.myapplication.ui.activity.ReservoirLevelActivity;
import com.example.myapplication.ui.activity.UAVPatrolActivity;
import com.example.myapplication.ui.activity.VideoSurveillanceActivity;
import com.example.myapplication.ui.activity.WaterDiversionActivity;
import com.example.myapplication.ui.activity.WaterQualityActivity;
import com.example.myapplication.ui.activity.WaterWeatherActivity;
import com.google.android.material.card.MaterialCardView;


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

    private AlertDialog mAlertDialog;

    public DataFragment() {

    }

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

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View contentView = getLayoutInflater().inflate(R.layout.item_dam_deformation_chooser,null,false);
        View surfaceBtn = contentView.findViewById(R.id.item_dam_deformation_chooser_surface);
        View internelBtn = contentView.findViewById(R.id.item_dam_deformation_chooser_internel);

        surfaceBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), DamDeformationActivity.class);
            intent.putExtra("DeformationType",DamDeformationActivity.TYPE_SURFACE_DEFORMATION);
            startActivity(intent);
            mAlertDialog.dismiss();
        });

        internelBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), DamDeformationActivity.class);
            intent.putExtra("DeformationType",DamDeformationActivity.TYPE_INTERNEL_DEFORMATION);
            startActivity(intent);
            mAlertDialog.dismiss();
        });

        builder.setView(contentView);
        mAlertDialog = builder.create();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fragment_data_reservoir_level_cardview:{
                Intent intent = new Intent(getContext(), ReservoirLevelActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.fragment_data_dam_deformation_cardview:{
                mAlertDialog.show();
                break;
            }

            case R.id.fragment_data_hydro_meteorology_cardview:{
                Intent intent = new Intent(getContext(), WaterWeatherActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.fragment_data_dam_stress_cardview:{
                Intent intent = new Intent(getContext(), DamStressActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.fragment_data_dam_seepage_cardview:{
                Intent intent = new Intent(getContext(), DamSeepageActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.fragment_data_water_diversion_system_cardview:{
                Intent intent = new Intent(getContext(), WaterDiversionActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.fragment_data_video_surveillance_cardview:{
                Intent videoSurveillanceIntent = new Intent(getContext(), VideoSurveillanceActivity.class);
                startActivity(videoSurveillanceIntent);
                break;
            }

            case R.id.fragment_data_uav_cardview:{
                Intent uavSurveillanceIntent = new Intent(getContext(), UAVPatrolActivity.class);
                startActivity(uavSurveillanceIntent);
                break;
            }

            case R.id.fragment_data_water_quality_cardview:{
                Intent intent = new Intent(getContext(), WaterQualityActivity.class);
                startActivity(intent);
                break;
            }
        }
    }

}