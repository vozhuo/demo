package com.example.myapplication.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.myapplication.R;
import com.example.myapplication.entity.WaterDiversionEntity;

import java.util.List;

public class WaterDiversionAdapter extends BaseQuickAdapter<WaterDiversionEntity, BaseViewHolder> {
    public WaterDiversionAdapter(@Nullable List<WaterDiversionEntity> data) {
        super(R.layout.item_water_diversion, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, WaterDiversionEntity item) {
        helper.setText(R.id.tv_water_name, item.getName())
                .addOnClickListener(R.id.btn_water_graph);

        RecyclerView mRecyclerView = helper.getView(R.id.rv_water_diversion_detail);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(new WaterDetailAdapter(item.getWaterlist()));
    }
}
