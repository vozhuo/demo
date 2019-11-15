package com.example.myapplication.adapter;

import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.myapplication.R;
import com.example.myapplication.entity.GraphDisplayItem;
import com.example.myapplication.util.DateXValueFormatter;
import com.example.myapplication.util.ReverseDateXValueFormatter;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;

import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CommonGrpahItemAdapter extends BaseQuickAdapter<GraphDisplayItem, BaseViewHolder> {


    public CommonGrpahItemAdapter(@Nullable List<GraphDisplayItem> data) {
        super(R.layout.common_graph_item,data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, GraphDisplayItem item) {
        helper.setText(R.id.tv_sub_header_center,item.getItemTitleText());
        helper.setText(R.id.common_graph_item_week_title,item.getGraphTitleTextList().get(0));
        helper.setText(R.id.common_graph_item_month_title,item.getGraphTitleTextList().get(1));
        helper.setText(R.id.common_graph_item_year_title,item.getGraphTitleTextList().get(2));

        LineChart weekChart = helper.getView(R.id.common_graph_item_week_linechart);
        weekChart.getXAxis().setValueFormatter(new ReverseDateXValueFormatter(ReverseDateXValueFormatter.DATE_TYPE_WEEK,new Date()));
        weekChart.setTouchEnabled(false);                              //禁止用户对图表进行操作
        weekChart.getAxisRight().setEnabled(false);                    //禁用右侧Y轴
        weekChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);  //设置X轴显示位置
        weekChart.getXAxis().setDrawAxisLine(false);
        weekChart.getXAxis().setDrawGridLines(false);                  //不绘制网格
        weekChart.getLegend().setEnabled(false);                       //禁用图例
        weekChart.getDescription().setEnabled(false);                  //禁用X轴描述
        weekChart.setData(item.getGraphLineDataList().get(0));

        LineChart monthChart = helper.getView(R.id.common_graph_item_month_linechart);
        monthChart.getXAxis().setValueFormatter(new ReverseDateXValueFormatter(ReverseDateXValueFormatter.DATE_TYPE_MONTH,new Date()));
        monthChart.setTouchEnabled(false);                              //禁止用户对图表进行操作
        monthChart.getAxisRight().setEnabled(false);                    //禁用右侧Y轴
        monthChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);  //设置X轴显示位置
        monthChart.getXAxis().setDrawAxisLine(false);
        monthChart.getXAxis().setDrawGridLines(false);                  //不绘制网格
        monthChart.getLegend().setEnabled(false);                       //禁用图例
        monthChart.getDescription().setEnabled(false);                  //禁用X轴描述
        monthChart.setData(item.getGraphLineDataList().get(1));

        LineChart yearChart = helper.getView(R.id.common_graph_item_year_linechart);
        yearChart.getXAxis().setValueFormatter(new ReverseDateXValueFormatter(ReverseDateXValueFormatter.DATE_TYPE_YEAR,new Date()));
        yearChart.setTouchEnabled(false);                              //禁止用户对图表进行操作
        yearChart.getAxisRight().setEnabled(false);                    //禁用右侧Y轴
        yearChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);  //设置X轴显示位置
        yearChart.getXAxis().setDrawAxisLine(false);
        yearChart.getXAxis().setDrawGridLines(false);                  //不绘制网格
        yearChart.getLegend().setEnabled(false);                       //禁用图例
        yearChart.getDescription().setEnabled(false);                  //禁用X轴描述
        yearChart.setData(item.getGraphLineDataList().get(2));
    }


}
