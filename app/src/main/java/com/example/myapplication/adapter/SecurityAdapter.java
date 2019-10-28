package com.example.myapplication.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.myapplication.R;
import com.example.myapplication.entity.SecurityEntity;

import java.util.List;

public class SecurityAdapter extends BaseQuickAdapter<SecurityEntity, BaseViewHolder> {

    public SecurityAdapter(@Nullable List<SecurityEntity> data) {
        super(R.layout.item_security, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, SecurityEntity item) {
        helper.setText(R.id.bt_security_name, item.getName());
//        if(item.getState() == 0)
//
    }
}
