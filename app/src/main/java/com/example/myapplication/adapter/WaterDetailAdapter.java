package com.example.myapplication.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.myapplication.R;
import com.example.myapplication.entity.BasicEntity;

import java.util.List;

public class WaterDetailAdapter extends BaseQuickAdapter<BasicEntity, BaseViewHolder> {
    public WaterDetailAdapter(@Nullable List<BasicEntity> data) {
        super(R.layout.item_water_diversion_detail, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, BasicEntity item) {

        helper.setText(R.id.tv_water_number, item.getNumber())
                .setText(R.id.tv_water_value, item.getValue() + "MPa");
    }
}
