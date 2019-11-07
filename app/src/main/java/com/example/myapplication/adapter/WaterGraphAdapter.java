package com.example.myapplication.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.myapplication.R;

import java.util.List;

public class WaterGraphAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public WaterGraphAdapter(@Nullable List<String> data) {
        super(R.layout.item_water_diversion_graph, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_subheader_name, item + "-曲线图")
                .setVisible(R.id.btn_graph, false);
    }
}
