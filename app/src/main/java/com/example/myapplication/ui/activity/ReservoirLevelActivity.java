package com.example.myapplication.ui.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.ReservoirLevelAdapter;
import com.example.myapplication.util.ScreenUtils;
import com.example.myapplication.util.XValueFormatter;
import com.example.myapplication.viewmodel.ReservoirLevelViewModel;
import com.example.myapplication.widget.SpaceItemDecoration;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ReservoirLevelActivity extends BaseActivity{

    private RecyclerView mRecyclerView;
    private ImageView mCalendarBtn;
    private TextView mCalendarTv;
    private LineChart mWeekLineChart;
    private LineChart mMonthLineChart;
    private LineChart mYearLineChart;

    private Calendar mCalendar;
    private ReservoirLevelAdapter mAdapter;
    private ReservoirLevelViewModel mViewModel;
    private ValueFormatter mWeekValueFormatter;
    private ValueFormatter mMonthValueFormatter;
    private ValueFormatter mYearValueFormatter;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_reservoir_level_main;
    }

    @Override
    protected boolean useSupportedToolbar() {
        return true;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        setToolbarTitle(R.string.activity_reservoir_level_title);

        mCalendarBtn = findViewById(R.id.iv_date_picker);
        mCalendarTv = findViewById(R.id.tv_date);
        mCalendarTv.setText(SimpleDateFormat.getDateInstance().format(new Date()));

        mCalendar = Calendar.getInstance();
        mCalendarBtn.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(ReservoirLevelActivity.this,
                    (view, year, month, dayOfMonth) -> {
                        mCalendar.set(Calendar.YEAR, year);
                        mCalendar.set(Calendar.MONTH, month);
                        mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        mCalendarTv.setText(SimpleDateFormat.getDateInstance().format(mCalendar.getTime()));

                    }, mCalendar.get(Calendar.YEAR),
                    mCalendar.get(Calendar.MONTH),
                    mCalendar.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
        });

        mRecyclerView = findViewById(R.id.activity_reservoir_level_main_rv);
        mAdapter = new ReservoirLevelAdapter(R.layout.item_hour_reservoir_level);

        SpaceItemDecoration itemDecoration = new SpaceItemDecoration(0,0, ScreenUtils.dp2px(this,10),
                ScreenUtils.dp2px(this,10));
        mRecyclerView.addItemDecoration(itemDecoration);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

        initLineChart();
    }

    @Override
    protected void initData() {
        mViewModel = ViewModelProviders.of(this).get(ReservoirLevelViewModel.class);
        mViewModel.getObservaleReservoirLevelData().observe(this, singleDayReservoirLevelList -> {
            mAdapter.setNewData(singleDayReservoirLevelList.get(0).getHourReservoirLevelList());

            int lastIndex = singleDayReservoirLevelList.size() - 1;
            List<Entry> weekEntries = new ArrayList<>();
            for (int i = lastIndex,j = 0;i >= lastIndex - 7;i--,j++){
                weekEntries.add(new Entry(j,singleDayReservoirLevelList.get(i).getAverageReservoirLevel()));
            }
            LineDataSet weekLineDataSet = new LineDataSet(weekEntries,"一周水位变化");
            weekLineDataSet.setColor(getResources().getColor(R.color.activity_reservoir_level_graph_line_color));
            weekLineDataSet.setDrawValues(false);
            weekLineDataSet.setDrawCircles(false);
            LineData weekLineData = new LineData(weekLineDataSet);
            mWeekLineChart.setData(weekLineData);

            List<Entry> monthEntries = new ArrayList<>();
            for (int i = lastIndex,j = 0;i >= lastIndex - 30;i--,j++){
                monthEntries.add(new Entry(j,singleDayReservoirLevelList.get(i).getAverageReservoirLevel()));
            }
            LineDataSet monthLineDataSet = new LineDataSet(monthEntries,"30天水位变化");
            monthLineDataSet.setColor(getResources().getColor(R.color.activity_reservoir_level_graph_line_color));
            monthLineDataSet.setDrawValues(false);
            monthLineDataSet.setDrawCircles(false);
            LineData monthLineData = new LineData(monthLineDataSet);
            mMonthLineChart.setData(monthLineData);

            List<Entry> yearEntries = new ArrayList<>();
            for (int i = lastIndex,j = 0;i >= 0;i--,j++){
                yearEntries.add(new Entry(j,singleDayReservoirLevelList.get(i).getAverageReservoirLevel()));
            }
            LineDataSet yearLineDataSet = new LineDataSet(yearEntries,"30天水位变化");
            yearLineDataSet.setColor(getResources().getColor(R.color.activity_reservoir_level_graph_line_color));
            yearLineDataSet.setDrawValues(false);
            yearLineDataSet.setDrawCircles(false);
            LineData yearLineData = new LineData(yearLineDataSet);
            mYearLineChart.setData(yearLineData);
        });
        mViewModel.queryReservoirLevelData();
    }

    private void initLineChart(){
        mWeekValueFormatter = new XValueFormatter(XValueFormatter.DATE_TYPE_WEEK,new Date());
        mWeekLineChart = findViewById(R.id.activity_reservoir_level_main_week_linechart);
        mWeekLineChart.setTouchEnabled(false);                              //禁止用户对图表进行操作
        mWeekLineChart.getAxisRight().setEnabled(false);                    //禁用右侧Y轴
        mWeekLineChart.getAxisLeft().setAxisMinimum(400f);                    //从400m开始
        mWeekLineChart.getAxisLeft().setAxisMaximum(600f);                   //最大刻度是600m
        mWeekLineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);  //设置X轴显示位置
        mWeekLineChart.getXAxis().setDrawAxisLine(false);
        mWeekLineChart.getXAxis().setDrawGridLines(false);                  //不绘制网格
        mWeekLineChart.getLegend().setEnabled(false);                       //禁用图例
        mWeekLineChart.getDescription().setEnabled(false);                  //禁用X轴描述
//        mWeekLineChart.getXAxis().setAxisMinimum(0f);
//        mWeekLineChart.getXAxis().setAxisMaximum(6f);
        //mWeekLineChart.getXAxis().setValueFormatter(mWeekValueFormatter);
        mWeekLineChart.getXAxis().setAvoidFirstLastClipping(true);

        mMonthValueFormatter = new XValueFormatter(XValueFormatter.DATE_TYPE_MONTH,new Date());
        mMonthLineChart = findViewById(R.id.activity_reservoir_level_main_month_linechart);
        mMonthLineChart.setTouchEnabled(false);                              //禁止用户对图表进行操作
        mMonthLineChart.getAxisRight().setEnabled(false);                    //禁用右侧Y轴
        mMonthLineChart.getAxisLeft().setAxisMinimum(400f);                    //从400m开始
        mMonthLineChart.getAxisLeft().setAxisMaximum(600f);                   //最大刻度是600m
        mMonthLineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);  //设置X轴显示位置
        mMonthLineChart.getXAxis().setDrawAxisLine(false);
        mMonthLineChart.getXAxis().setDrawGridLines(false);                  //不绘制网格
        mMonthLineChart.getLegend().setEnabled(false);                       //禁用图例
        mMonthLineChart.getDescription().setEnabled(false);                  //禁用X轴描述
//        mMonthLineChart.getXAxis().setAxisMinimum(0f);
//        mMonthLineChart.getXAxis().setAxisMaximum(30f);
        //mMonthLineChart.getXAxis().setValueFormatter(mMonthValueFormatter);
        mMonthLineChart.getXAxis().setAvoidFirstLastClipping(true);

        mYearValueFormatter = new XValueFormatter(XValueFormatter.DATE_TYPE_YEAR,new Date());
        mYearLineChart = findViewById(R.id.activity_reservoir_level_main_year_linechart);
        mYearLineChart.setTouchEnabled(false);                              //禁止用户对图表进行操作
        mYearLineChart.getAxisRight().setEnabled(false);                    //禁用右侧Y轴
        mYearLineChart.getAxisLeft().setAxisMinimum(400f);                    //从400m开始
        mYearLineChart.getAxisLeft().setAxisMaximum(600f);                   //最大刻度是600m
        mYearLineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);  //设置X轴显示位置
        mYearLineChart.getXAxis().setDrawAxisLine(false);
        mYearLineChart.getXAxis().setDrawGridLines(false);                  //不绘制网格
        mYearLineChart.getLegend().setEnabled(false);                       //禁用图例
        mYearLineChart.getDescription().setEnabled(false);                  //禁用X轴描述
        mYearLineChart.getXAxis().setAxisMinimum(0f);
        mYearLineChart.getXAxis().setAxisMaximum(365f);
        //mYearLineChart.getXAxis().setValueFormatter(mYearValueFormatter);
        mYearLineChart.getXAxis().setAvoidFirstLastClipping(true);
    }
}
