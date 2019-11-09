package com.example.myapplication.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.myapplication.R;
import com.example.myapplication.entity.DamStressEntity;

import java.util.List;

public class DamStressAdapter extends BaseQuickAdapter<DamStressEntity, BaseViewHolder> {
    public DamStressAdapter(@Nullable List<DamStressEntity> data) {
        super(R.layout.item_dam_stress, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, DamStressEntity item) {
        helper.setText(R.id.tv_sub_header_left, item.getName())
                .setVisible(R.id.btn_graph, false);

        String text = "";
        text += "最大应力：" + item.getValue().get(0) + "Mpa\n";
        text += "最小应力：" + item.getValue().get(1) + "Mpa\n";
        text += "拉应力：" + item.getValue().get(2) + "Mpa";

        helper.setText(R.id.tv_dam_stress_value, text);

        if (item.getName().contains("A"))
            helper.setImageDrawable(R.id.iv_dam_stress, mContext.getResources().getDrawable(R.drawable.nephogram_a));
        else if (item.getName().contains("B"))
            helper.setImageDrawable(R.id.iv_dam_stress, mContext.getResources().getDrawable(R.drawable.nephogram_b));
        else if (item.getName().contains("C"))
            helper.setImageDrawable(R.id.iv_dam_stress, mContext.getResources().getDrawable(R.drawable.nephogram_c));
    }
}
