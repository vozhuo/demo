package com.example.myapplication.ui.activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.entity.SingleDayDamOffsetEntity;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import androidx.annotation.Nullable;

public class DamMonitorGraphActivity extends BaseActivity {

    private ImageView mCalendarBtn;
    private TextView mCalendarTv;
    private TextView mSubTitleTv;
    private LineChart mHorizontalWeekChart;
    private LineChart mHorizontalMonthChart;
    private LineChart mHorizontalYearChart;
    private LineChart mVerticalWeekChart;
    private LineChart mVerticalMonthChart;
    private LineChart mVerticalYearChart;

    private Calendar mCalendar;
    private int mType;
    private String mMonitorName;
    private int mLineColorResId;

    private List<SingleDayDamOffsetEntity> mHorizontalDamOffsetList;
    private List<SingleDayDamOffsetEntity> mVerticalDamOffsetList;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_dam_monitor_graph_main;
    }

    @Override
    protected boolean useSupportedToolbar() {
        return true;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        mType = getIntent().getIntExtra("DeformationType",DamDeformationActivity.TYPE_SURFACE_DEFORMATION);
        mMonitorName = getIntent().getStringExtra("MonitorName");
        mLineColorResId = (mType == DamDeformationActivity.TYPE_SURFACE_DEFORMATION ? R.color.activity_dam_monitor_graph_surface_line_color
                : R.color.activity_dam_monitor_graph_internel_line_color);

        mSubTitleTv = findViewById(R.id.tv_sub_header_center);
        mCalendarBtn = findViewById(R.id.iv_date_picker);
        mCalendarTv = findViewById(R.id.tv_date);

        getToolbar().setTitle(mType == DamDeformationActivity.TYPE_SURFACE_DEFORMATION ?
                R.string.activity_dam_deformation_surface_title_text : R.string.activity_dam_deformation_internel_title_text);
        mSubTitleTv.setText(mMonitorName + "监测点-曲线图");

        mCalendarTv.setText(SimpleDateFormat.getDateInstance().format(new Date()));
        mCalendar = Calendar.getInstance();
        mCalendarBtn.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(DamMonitorGraphActivity.this,
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

        initLineChart();
    }

    @Override
    protected void initData() {
        mHorizontalDamOffsetList = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);       //把时间设置为今天的00:00:00:000
        int duration = 365;                           //过去<duration>天的数据
        for (int i = 0;i < duration;i++){
            mHorizontalDamOffsetList.add(simulateOneDayDamOffset(calendar.getTime()));
            calendar.add(Calendar.DAY_OF_MONTH,-1);
        }

        mVerticalDamOffsetList = new ArrayList<>();
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.HOUR_OF_DAY,0);
        calendar1.set(Calendar.MINUTE,0);
        calendar1.set(Calendar.SECOND,0);
        calendar1.set(Calendar.MILLISECOND,0);       //把时间设置为今天的00:00:00:000
        for (int i = 0;i < duration;i++){
            mVerticalDamOffsetList.add(simulateOneDayDamOffset(calendar1.getTime()));
            calendar1.add(Calendar.DAY_OF_MONTH,-1);
        }

        //填充数据进图表控件
        int lastIndex = mHorizontalDamOffsetList.size() - 1;
        List<Entry> weekHorizontalEntries = new ArrayList<>();
        for (int i = lastIndex,j = 0;i >= lastIndex - 6;i--,j++){
            weekHorizontalEntries.add(new Entry(j,mHorizontalDamOffsetList.get(i).getDamOffset()));
        }
        LineDataSet weekHorizontalLineDataSet = new LineDataSet(weekHorizontalEntries,"7天水平位移变化");
        weekHorizontalLineDataSet.setColor(getResources().getColor(mLineColorResId));
        weekHorizontalLineDataSet.setDrawValues(false);
        weekHorizontalLineDataSet.setDrawCircles(false);
        LineData weekHorizontalLineData = new LineData(weekHorizontalLineDataSet);
        mHorizontalWeekChart.setData(weekHorizontalLineData);

        List<Entry> monthHorizontalEntries = new ArrayList<>();
        for (int i = lastIndex,j = 0;i >= lastIndex - 29;i--,j++){
            monthHorizontalEntries.add(new Entry(j,mHorizontalDamOffsetList.get(i).getDamOffset()));
        }
        LineDataSet monthHorizontalLineDataSet = new LineDataSet(monthHorizontalEntries,"30天水平位移变化");
        monthHorizontalLineDataSet.setColor(getResources().getColor(mLineColorResId));
        monthHorizontalLineDataSet.setDrawValues(false);
        monthHorizontalLineDataSet.setDrawCircles(false);
        LineData monthHorizontalLineData = new LineData(monthHorizontalLineDataSet);
        mHorizontalMonthChart.setData(monthHorizontalLineData);

        List<Entry> yearHorizontalEntries = new ArrayList<>();
        for (int i = lastIndex,j = 0;i >= 0;i--,j++){
            yearHorizontalEntries.add(new Entry(j,mHorizontalDamOffsetList.get(i).getDamOffset()));
        }
        LineDataSet yearHorizontalLineDataSet = new LineDataSet(yearHorizontalEntries,"12个月水平位移变化");
        yearHorizontalLineDataSet.setColor(getResources().getColor(mLineColorResId));
        yearHorizontalLineDataSet.setDrawValues(false);
        yearHorizontalLineDataSet.setDrawCircles(false);
        LineData yearHorizontalLineData = new LineData(yearHorizontalLineDataSet);
        mHorizontalYearChart.setData(yearHorizontalLineData);



        List<Entry> weekVerticalEntries = new ArrayList<>();
        for (int i = lastIndex,j = 0;i >= lastIndex - 6;i--,j++){
            weekVerticalEntries.add(new Entry(j,mVerticalDamOffsetList.get(i).getDamOffset()));
        }
        LineDataSet weekVerticalLineDataSet = new LineDataSet(weekVerticalEntries,"7天垂直位移变化");
        weekVerticalLineDataSet.setColor(getResources().getColor(mLineColorResId));
        weekVerticalLineDataSet.setDrawValues(false);
        weekVerticalLineDataSet.setDrawCircles(false);
        LineData weekVerticalLineData = new LineData(weekVerticalLineDataSet);
        mVerticalWeekChart.setData(weekVerticalLineData);

        List<Entry> monthVerticalEntries = new ArrayList<>();
        for (int i = lastIndex,j = 0;i >= lastIndex - 29;i--,j++){
            monthVerticalEntries.add(new Entry(j,mVerticalDamOffsetList.get(i).getDamOffset()));
        }
        LineDataSet monthVerticalLineDataSet = new LineDataSet(monthVerticalEntries,"30天垂直位移变化");
        monthVerticalLineDataSet.setColor(getResources().getColor(mLineColorResId));
        monthVerticalLineDataSet.setDrawValues(false);
        monthVerticalLineDataSet.setDrawCircles(false);
        LineData monthVerticalLineData = new LineData(monthVerticalLineDataSet);
        mVerticalMonthChart.setData(monthVerticalLineData);

        List<Entry> yearVerticalEntries = new ArrayList<>();
        for (int i = lastIndex,j = 0;i >= 0;i--,j++){
            yearVerticalEntries.add(new Entry(j,mVerticalDamOffsetList.get(i).getDamOffset()));
        }
        LineDataSet yearVerticalLineDataSet = new LineDataSet(yearVerticalEntries,"12个月垂直位移变化");
        yearVerticalLineDataSet.setColor(getResources().getColor(mLineColorResId));
        yearVerticalLineDataSet.setDrawValues(false);
        yearVerticalLineDataSet.setDrawCircles(false);
        LineData yearVerticalLineData = new LineData(yearVerticalLineDataSet);
        mVerticalYearChart.setData(yearVerticalLineData);
    }

    private SingleDayDamOffsetEntity simulateOneDayDamOffset(Date date){
        Random random = new Random();

        int min = 3;
        int max = 5;

        return new SingleDayDamOffsetEntity(random.nextFloat() + min,date);
    }

    private void initLineChart(){
        mHorizontalWeekChart = findViewById(R.id.activity_dam_monitor_graph_week_horizontal_offset_chart);
        mHorizontalMonthChart = findViewById(R.id.activity_dam_monitor_graph_month_horizontal_offset_chart);
        mHorizontalYearChart = findViewById(R.id.activity_dam_monitor_graph_year_horizontal_offset_chart);
        mVerticalWeekChart = findViewById(R.id.activity_dam_monitor_graph_week_vertical_offset_chart);
        mVerticalMonthChart = findViewById(R.id.activity_dam_monitor_graph_month_vertical_offset_chart);
        mVerticalYearChart = findViewById(R.id.activity_dam_monitor_graph_year_vertical_offset_chart);

        //week horizontal chart
        mHorizontalWeekChart.setTouchEnabled(false);                              //禁止用户对图表进行操作
        mHorizontalWeekChart.getAxisRight().setEnabled(false);                    //禁用右侧Y轴
        mHorizontalWeekChart.getAxisLeft().setAxisMinimum(3f);                    //从0°开始
        mHorizontalWeekChart.getAxisLeft().setAxisMaximum(5f);                   //最大刻度是60°
        mHorizontalWeekChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);  //设置X轴显示位置
        mHorizontalWeekChart.getXAxis().setDrawAxisLine(false);
        mHorizontalWeekChart.getXAxis().setDrawGridLines(false);                  //不绘制网格
        mHorizontalWeekChart.getLegend().setEnabled(false);                       //禁用图例
        mHorizontalWeekChart.getDescription().setEnabled(false);                  //禁用X轴描述
        //mHorizontalWeekChart.getXAxis().setValueFormatter(mDateXValueFormatter);

        //month horizontal chart
        mHorizontalMonthChart.setTouchEnabled(false);                              //禁止用户对图表进行操作
        mHorizontalMonthChart.getAxisRight().setEnabled(false);                    //禁用右侧Y轴
        mHorizontalMonthChart.getAxisLeft().setAxisMinimum(3f);                    //从0°开始
        mHorizontalMonthChart.getAxisLeft().setAxisMaximum(5f);                   //最大刻度是60°
        mHorizontalMonthChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);  //设置X轴显示位置
        mHorizontalMonthChart.getXAxis().setDrawAxisLine(false);
        mHorizontalMonthChart.getXAxis().setDrawGridLines(false);                  //不绘制网格
        mHorizontalMonthChart.getLegend().setEnabled(false);                       //禁用图例
        mHorizontalMonthChart.getDescription().setEnabled(false);                  //禁用X轴描述
        //mHorizontalMonthChart.getXAxis().setValueFormatter(mDateXValueFormatter);

        //year horizontal chart
        mHorizontalYearChart.setTouchEnabled(false);                              //禁止用户对图表进行操作
        mHorizontalYearChart.getAxisRight().setEnabled(false);                    //禁用右侧Y轴
        mHorizontalYearChart.getAxisLeft().setAxisMinimum(3f);                    //从0°开始
        mHorizontalYearChart.getAxisLeft().setAxisMaximum(5f);                   //最大刻度是60°
        mHorizontalYearChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);  //设置X轴显示位置
        mHorizontalYearChart.getXAxis().setDrawAxisLine(false);
        mHorizontalYearChart.getXAxis().setDrawGridLines(false);                  //不绘制网格
        mHorizontalYearChart.getLegend().setEnabled(false);                       //禁用图例
        mHorizontalYearChart.getDescription().setEnabled(false);                  //禁用X轴描述
        //mHorizontalYearChart.getXAxis().setValueFormatter(mDateXValueFormatter);

        //week vertical chart
        mVerticalWeekChart.setTouchEnabled(false);                              //禁止用户对图表进行操作
        mVerticalWeekChart.getAxisRight().setEnabled(false);                    //禁用右侧Y轴
        mVerticalWeekChart.getAxisLeft().setAxisMinimum(3f);                    //从0°开始
        mVerticalWeekChart.getAxisLeft().setAxisMaximum(5f);                   //最大刻度是60°
        mVerticalWeekChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);  //设置X轴显示位置
        mVerticalWeekChart.getXAxis().setDrawAxisLine(false);
        mVerticalWeekChart.getXAxis().setDrawGridLines(false);                  //不绘制网格
        mVerticalWeekChart.getLegend().setEnabled(false);                       //禁用图例
        mVerticalWeekChart.getDescription().setEnabled(false);                  //禁用X轴描述
        //mVerticalWeekChart.getXAxis().setValueFormatter(mDateXValueFormatter);

        //month vertical chart
        mVerticalMonthChart.setTouchEnabled(false);                              //禁止用户对图表进行操作
        mVerticalMonthChart.getAxisRight().setEnabled(false);                    //禁用右侧Y轴
        mVerticalMonthChart.getAxisLeft().setAxisMinimum(3f);                    //从0°开始
        mVerticalMonthChart.getAxisLeft().setAxisMaximum(5f);                   //最大刻度是60°
        mVerticalMonthChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);  //设置X轴显示位置
        mVerticalMonthChart.getXAxis().setDrawAxisLine(false);
        mVerticalMonthChart.getXAxis().setDrawGridLines(false);                  //不绘制网格
        mVerticalMonthChart.getLegend().setEnabled(false);                       //禁用图例
        mVerticalMonthChart.getDescription().setEnabled(false);                  //禁用X轴描述
        //mVerticalMonthChart.getXAxis().setValueFormatter(mDateXValueFormatter);

        //year vertical chart
        mVerticalYearChart.setTouchEnabled(false);                              //禁止用户对图表进行操作
        mVerticalYearChart.getAxisRight().setEnabled(false);                    //禁用右侧Y轴
        mVerticalYearChart.getAxisLeft().setAxisMinimum(3f);                    //从0°开始
        mVerticalYearChart.getAxisLeft().setAxisMaximum(5f);                   //最大刻度是60°
        mVerticalYearChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);  //设置X轴显示位置
        mVerticalYearChart.getXAxis().setDrawAxisLine(false);
        mVerticalYearChart.getXAxis().setDrawGridLines(false);                  //不绘制网格
        mVerticalYearChart.getLegend().setEnabled(false);                       //禁用图例
        mVerticalYearChart.getDescription().setEnabled(false);                  //禁用X轴描述
        //mVerticalYearChart.getXAxis().setValueFormatter(mDateXValueFormatter);
    }
}
