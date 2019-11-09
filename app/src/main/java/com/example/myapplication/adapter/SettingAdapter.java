package com.example.myapplication.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.myapplication.R;

import java.util.List;

public class SettingAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public SettingAdapter(@Nullable List<String> data) {
        super(R.layout.item_account_setting, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_setting, item);
    }
}
