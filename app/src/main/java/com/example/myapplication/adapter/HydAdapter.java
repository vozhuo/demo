package com.example.myapplication.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.myapplication.R;
import com.example.myapplication.entity.HydEntity;

import java.util.List;

public class HydAdapter extends BaseQuickAdapter<HydEntity, BaseViewHolder> {

    public HydAdapter(@Nullable List<HydEntity> data) {
        super(R.layout.item_hyd, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, HydEntity item) {
        helper.setText(R.id.tv_hyd_item, item.getItem())
                .setText(R.id.tv_hyd_val, item.getValue());
    }
}
