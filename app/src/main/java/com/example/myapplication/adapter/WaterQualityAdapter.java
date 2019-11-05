package com.example.myapplication.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.myapplication.R;
import com.example.myapplication.entity.BasicEntity;

import java.util.List;

public class WaterQualityAdapter extends BaseQuickAdapter<BasicEntity, BaseViewHolder> {
    public WaterQualityAdapter(@Nullable List<BasicEntity> data) {
        super(R.layout.item_water_quality, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, BasicEntity item) {
        helper.setText(R.id.tv_water_para, item.getNumber())
                .setText(R.id.tv_water_qvalue, item.getValue() + "mg/L");
    }
}
