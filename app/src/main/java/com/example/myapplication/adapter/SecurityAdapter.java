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
        switch (item.getState()) {
            case 0:
                helper.setImageDrawable(R.id.iv_security_state,
                        mContext.getResources().getDrawable(R.drawable.icon_shield_0));
                break;
            case 1:
                helper.setImageDrawable(R.id.iv_security_state,
                        mContext.getResources().getDrawable(R.drawable.icon_shield_1));
                break;
            case 2:
                helper.setImageDrawable(R.id.iv_security_state,
                        mContext.getResources().getDrawable(R.drawable.icon_shield_2));
                break;
            case 3:
                helper.setImageDrawable(R.id.iv_security_state,
                        mContext.getResources().getDrawable(R.drawable.icon_shield_3));
                break;
            default:
                break;
        }
    }
}
