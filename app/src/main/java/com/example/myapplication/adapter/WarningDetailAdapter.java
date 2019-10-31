package com.example.myapplication.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import com.example.myapplication.R;
import com.example.myapplication.entity.WarningEntity;

import java.util.List;

public class WarningDetailAdapter extends BaseQuickAdapter<WarningEntity, BaseViewHolder> {
    public WarningDetailAdapter(@Nullable List<WarningEntity> data) {
        super(R.layout.item_warning_detail, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, WarningEntity item) {
        helper.setText(R.id.tv_warning_time, item.getDate())
                .setText(R.id.tv_warning_pos, item.getPostiton())
                .setText(R.id.tv_warning_info, item.getValue());

    }
}
