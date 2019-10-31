package com.example.myapplication.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.myapplication.R;
import com.example.myapplication.entity.SecurityEntity;

import java.util.List;

public class WarningAdapter extends BaseQuickAdapter<SecurityEntity, BaseViewHolder> {
    public WarningAdapter(@Nullable List<SecurityEntity> data) {
        super(R.layout.item_warning, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, SecurityEntity item) {
        helper.setText(R.id.tv_left, item.getName());

        RecyclerView mRecyclerView = helper.getView(R.id.rv_warning_detail);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(new WarningDetailAdapter(item.getWarningList()));
    }
}
