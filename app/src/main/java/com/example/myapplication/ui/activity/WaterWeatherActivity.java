package com.example.myapplication.ui.activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.entity.SingleDayWeatherData;
import com.example.myapplication.entity.WaterWeatherEntity;
import com.example.myapplication.viewmodel.WaterWeatherViewModel;
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

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

public class WaterWeatherActivity extends BaseActivity {

    private ImageView mCurrentWeatherIv;
    private TextView mCurrentTempTv;
    private TextView mCurrentTempRangeTv;
    private TextView mCurrentSensibleTempTv;

    private TextView mOneHourTv;
    private ImageView mOneHourWeatherIv;
    private TextView mOneHourTempTv;
    private TextView mTwoHourTv;
    private ImageView mTwoHourWeatherIv;
    private TextView mTwoHourTempTv;
    private TextView mThreeHourTv;
    private ImageView mThreeHourWeatherIv;
    private TextView mThreeHourTempTv;
    private TextView mFourHourTv;
    private ImageView mFourHourWeatherIv;
    private TextView mFourHourTempTv;

    private ImageView mTodayWeatherIv;
    private TextView mTodayWeatherDescTv;
    private TextView mTodayTempRangeTv;
    private ImageView mOneDayWeatherIv;
    private TextView mOneDayWeatherDescTv;
    private TextView mOneDayTempRangeTv;
    private ImageView mTwoDayWeatherIv;
    private TextView mTwoDayWeatherDescTv;
    private TextView mTwoDayTempRangeTv;
    private ImageView mThreeDayWeatherIv;
    private TextView mThreeDayWeatherDescTv;
    private TextView mThreeDayTempRangeTv;

    private ImageView mCalendarBtn;
    private TextView mCalendarTv;
    private TextView mRainFallCapacityTv;
    private TextView mEvaporationCapacityTv;
    private TextView mInflowCapacityTv;
    private TextView mUseWaterCapacityTv;
    private TextView mAmountOfWaterCapacityTv;
    private LineChart mTemperatureChart1;
    private LineChart mTemperatureChart2;
    private LineChart mHydrologyChart1;
    private LineChart mHydrologyChart2;

    private Calendar mCalendar;
    private WaterWeatherViewModel mViewModel;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_water_weather_main;
    }

    @Override
    protected boolean useSupportedToolbar() {
        return true;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        mRainFallCapacityTv = findViewById(R.id.activity_water_weather_main_rainfall_capacity_tv);
        mEvaporationCapacityTv = findViewById(R.id.activity_water_weather_main_evaporation_capacity_tv);
        mInflowCapacityTv = findViewById(R.id.activity_water_weather_main_inflow_capacity_tv);
        mUseWaterCapacityTv = findViewById(R.id.activity_water_weather_main_use_water_capacity_tv);
        mAmountOfWaterCapacityTv = findViewById(R.id.activity_water_weather_main_amountofwater_capacity_tv);
        mCalendarTv = findViewById(R.id.tv_date);
        mCalendarBtn = findViewById(R.id.iv_date_picker);
        mCalendarTv.setText(SimpleDateFormat.getDateInstance().format(new Date()));

        mCalendar = Calendar.getInstance();
        mCalendarBtn.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(WaterWeatherActivity.this,
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

        initWeatherDescriptionView();
        initChartView();
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void initData() {
        mViewModel = ViewModelProviders.of(this).get(WaterWeatherViewModel.class);
        mViewModel.getObservableWaterWeatherData().observe(this, waterWeatherEntity -> {
            List<Entry> monthMaxTempEntries = new ArrayList<>();
            List<Entry> monthMinTempEntries = new ArrayList<>();
            List<Entry> monthAvgTempEntries = new ArrayList<>();
            for (int i = 0;i < 30;i++){
                monthMaxTempEntries.add(new Entry(i,waterWeatherEntity.getMonthWeatherData().get(i).getMaxTemperature()));
                monthMinTempEntries.add(new Entry(i,waterWeatherEntity.getMonthWeatherData().get(i).getMinTemperature()));
                monthAvgTempEntries.add(new Entry(i,waterWeatherEntity.getMonthWeatherData().get(i).getAvgTemperature()));
            }

            LineDataSet monthMaxTempDataSet = new LineDataSet(monthMaxTempEntries,"最高气温");
            monthMaxTempDataSet.setColor(getResources().getColor(R.color.activity_water_weather_weather_graph_max_temperature_color));
            monthMaxTempDataSet.setDrawValues(false);
            monthMaxTempDataSet.setDrawCircles(false);

            LineDataSet monthMinTempDataSet = new LineDataSet(monthMinTempEntries,"最低气温");
            monthMinTempDataSet.setColor(getResources().getColor(R.color.activity_water_weather_weather_graph_min_temperature_color));
            monthMinTempDataSet.setDrawValues(false);
            monthMinTempDataSet.setDrawCircles(false);

            LineDataSet monthAvgTempDataSet = new LineDataSet(monthAvgTempEntries,"平均气温");
            monthAvgTempDataSet.setColor(getResources().getColor(R.color.activity_water_weather_weather_graph_avg_temperature_color));
            monthAvgTempDataSet.setDrawValues(false);
            monthAvgTempDataSet.setDrawCircles(false);

            LineData weatherLineData = new LineData(monthMaxTempDataSet,monthMinTempDataSet,monthAvgTempDataSet);
            mTemperatureChart1.setData(weatherLineData);
            mTemperatureChart2.setData(weatherLineData);

            List<Entry> rainfallEntries = new ArrayList<>();
            List<Entry> evaporationEntries = new ArrayList<>();
            for (int i = 0;i < 30;i ++){
                rainfallEntries.add(new Entry(i,waterWeatherEntity.getMonthHydrologyData().get(i).getRainfallCapacity()));
                evaporationEntries.add(new Entry(i,waterWeatherEntity.getMonthHydrologyData().get(i).getEvaporationCapacity()));
            }
            LineDataSet rainfallDataSet = new LineDataSet(rainfallEntries,"降雨量");
            rainfallDataSet.setColor(getResources().getColor(R.color.activity_water_weather_hydrology_graph_rainfall_capacity_color));
            rainfallDataSet.setDrawCircles(false);
            rainfallDataSet.setDrawValues(false);

            LineDataSet evaporationDataSet = new LineDataSet(evaporationEntries,"蒸发量");
            evaporationDataSet.setColor(getResources().getColor(R.color.activity_water_weather_hydrology_graph_evaporation_capacity_color));
            evaporationDataSet.setDrawValues(false);
            evaporationDataSet.setDrawCircles(false);

            LineData hydrologyData = new LineData(rainfallDataSet,evaporationDataSet);
            mHydrologyChart1.setData(hydrologyData);
            mHydrologyChart2.setData(hydrologyData);

            mRainFallCapacityTv.setText(waterWeatherEntity.getMonthHydrologyData().get(0).getRainfallCapacity()+"mm");
            mEvaporationCapacityTv.setText(waterWeatherEntity.getMonthHydrologyData().get(0).getEvaporationCapacity() + "mm");
            mInflowCapacityTv.setText(((int)waterWeatherEntity.getMonthHydrologyData().get(0).getInflowCapacity()) + "q");
            mUseWaterCapacityTv.setText(((int)waterWeatherEntity.getMonthHydrologyData().get(0).getUsedWaterCapacity()) + "q");
            mAmountOfWaterCapacityTv.setText(((int)waterWeatherEntity.getMonthHydrologyData().get(0).getAmountOfWaterCapacity()) + "q");

            //天气的数据展示
            //实时数据
            SingleDayWeatherData singleDayWeatherData = waterWeatherEntity.getMonthWeatherData().get(0);
            mCurrentWeatherIv.setImageResource(WaterWeatherEntity.parseWeatherTypeToResId(singleDayWeatherData.getWeatherType()));
            mCurrentTempTv.setText(WaterWeatherEntity.formatTemperature(singleDayWeatherData.getHoursTemperatureList().get(0)));
            mCurrentSensibleTempTv.setText(WaterWeatherEntity.formatTemperature(singleDayWeatherData.getHoursSensibleTemperatureList().get(0)));
            mCurrentTempRangeTv.setText(WaterWeatherEntity.formatTemperatureRange(singleDayWeatherData.getMinTemperature(),singleDayWeatherData.getMaxTemperature()));

            //未来四个小时的数据：
            mOneHourTv.setText(WaterWeatherEntity.formatHour(8));
            mOneHourTempTv.setText(WaterWeatherEntity.formatTemperature(waterWeatherEntity.getMonthWeatherData().get(0).getHoursTemperatureList().get(8)));
            mOneHourWeatherIv.setImageResource(WaterWeatherEntity.parseWeatherTypeToResId(waterWeatherEntity.getMonthWeatherData().get(0).getWeatherTypeList().get(8)));

            mTwoHourTv.setText(WaterWeatherEntity.formatHour(9));
            mTwoHourTempTv.setText(WaterWeatherEntity.formatTemperature(waterWeatherEntity.getMonthWeatherData().get(0).getHoursTemperatureList().get(9)));
            mTwoHourWeatherIv.setImageResource(WaterWeatherEntity.parseWeatherTypeToResId(waterWeatherEntity.getMonthWeatherData().get(0).getWeatherTypeList().get(9)));

            mThreeHourTv.setText(WaterWeatherEntity.formatHour(10));
            mThreeHourTempTv.setText(WaterWeatherEntity.formatTemperature(waterWeatherEntity.getMonthWeatherData().get(0).getHoursTemperatureList().get(10)));
            mThreeHourWeatherIv.setImageResource(WaterWeatherEntity.parseWeatherTypeToResId(waterWeatherEntity.getMonthWeatherData().get(0).getWeatherTypeList().get(10)));

            mFourHourTv.setText(WaterWeatherEntity.formatHour(11));
            mFourHourTempTv.setText(WaterWeatherEntity.formatTemperature(waterWeatherEntity.getMonthWeatherData().get(0).getHoursTemperatureList().get(11)));
            mFourHourWeatherIv.setImageResource(WaterWeatherEntity.parseWeatherTypeToResId(waterWeatherEntity.getMonthWeatherData().get(0).getWeatherTypeList().get(11)));

            //今天以及未来三天的数据：
            mTodayWeatherIv.setImageResource(WaterWeatherEntity.parseWeatherTypeToResId(waterWeatherEntity.getMonthWeatherData().get(1).getWeatherType()));
            mTodayWeatherDescTv.setText(WaterWeatherEntity.parseWeatherTypeToString(waterWeatherEntity.getMonthWeatherData().get(1).getWeatherType()));
            mTodayTempRangeTv.setText(WaterWeatherEntity.formatTemperatureRange(waterWeatherEntity.getMonthWeatherData().get(1).getMinTemperature(),waterWeatherEntity.getMonthWeatherData().get(1).getMaxTemperature()));

            mOneDayWeatherIv.setImageResource(WaterWeatherEntity.parseWeatherTypeToResId(waterWeatherEntity.getMonthWeatherData().get(2).getWeatherType()));
            mOneDayWeatherDescTv.setText(WaterWeatherEntity.parseWeatherTypeToString(waterWeatherEntity.getMonthWeatherData().get(2).getWeatherType()));
            mOneDayTempRangeTv.setText(WaterWeatherEntity.formatTemperatureRange(waterWeatherEntity.getMonthWeatherData().get(2).getMinTemperature(),waterWeatherEntity.getMonthWeatherData().get(2).getMaxTemperature()));

            mTwoDayWeatherIv.setImageResource(WaterWeatherEntity.parseWeatherTypeToResId(waterWeatherEntity.getMonthWeatherData().get(3).getWeatherType()));
            mTwoDayWeatherDescTv.setText(WaterWeatherEntity.parseWeatherTypeToString(waterWeatherEntity.getMonthWeatherData().get(3).getWeatherType()));
            mTwoDayTempRangeTv.setText(WaterWeatherEntity.formatTemperatureRange(waterWeatherEntity.getMonthWeatherData().get(3).getMinTemperature(),waterWeatherEntity.getMonthWeatherData().get(3).getMaxTemperature()));

            mThreeDayWeatherIv.setImageResource(WaterWeatherEntity.parseWeatherTypeToResId(waterWeatherEntity.getMonthWeatherData().get(4).getWeatherType()));
            mThreeDayWeatherDescTv.setText(WaterWeatherEntity.parseWeatherTypeToString(waterWeatherEntity.getMonthWeatherData().get(4).getWeatherType()));
            mThreeDayTempRangeTv.setText(WaterWeatherEntity.formatTemperatureRange(waterWeatherEntity.getMonthWeatherData().get(4).getMinTemperature(),waterWeatherEntity.getMonthWeatherData().get(4).getMaxTemperature()));
        });
        mViewModel.queryWaterWeatherData();
    }

    private void initChartView(){
        mTemperatureChart1 = findViewById(R.id.activity_water_weather_main_temperature_chart1);
        mTemperatureChart2 = findViewById(R.id.activity_water_weather_main_temperature_chart2);
        mHydrologyChart1 = findViewById(R.id.activity_water_weather_main_hydrology_chart1);
        mHydrologyChart2 = findViewById(R.id.activity_water_weather_main_hydrology_chart2);

        //temperature chart1:
        mTemperatureChart1.setTouchEnabled(false);                              //禁止用户对图表进行操作
        mTemperatureChart1.getAxisRight().setEnabled(false);                    //禁用右侧Y轴
        mTemperatureChart1.getAxisLeft().setAxisMinimum(10f);                    //从0°开始
        mTemperatureChart1.getAxisLeft().setAxisMaximum(50f);                   //最大刻度是60°
        mTemperatureChart1.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);  //设置X轴显示位置
        mTemperatureChart1.getXAxis().setDrawAxisLine(false);
        mTemperatureChart1.getXAxis().setDrawGridLines(false);                  //不绘制网格
        mTemperatureChart1.getLegend().setEnabled(false);                       //禁用图例
        mTemperatureChart1.getDescription().setEnabled(false);                  //禁用X轴描述
        mTemperatureChart1.getXAxis().setAxisMinimum(0f);
        mTemperatureChart1.getXAxis().setAxisMaximum(7f);

        //temperature chart2:
        mTemperatureChart2.setTouchEnabled(false);                              //禁止用户对图表进行操作
        mTemperatureChart2.getAxisRight().setEnabled(false);                    //禁用右侧Y轴
        mTemperatureChart2.getAxisLeft().setAxisMinimum(10f);                    //从0°开始
        mTemperatureChart2.getAxisLeft().setAxisMaximum(50f);                   //最大刻度是60°
        mTemperatureChart2.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);  //设置X轴显示位置
        mTemperatureChart2.getXAxis().setDrawAxisLine(false);
        mTemperatureChart2.getXAxis().setDrawGridLines(false);                  //不绘制网格
        mTemperatureChart2.getLegend().setEnabled(false);                       //禁用图例
        mTemperatureChart2.getDescription().setEnabled(false);                  //禁用X轴描述

        //hydrology chart1
        mHydrologyChart1.setTouchEnabled(false);                              //禁止用户对图表进行操作
        mHydrologyChart1.getAxisRight().setEnabled(false);                    //禁用右侧Y轴
        mHydrologyChart1.getAxisLeft().setAxisMinimum(0f);                    //从0°开始
        mHydrologyChart1.getAxisLeft().setAxisMaximum(20f);                   //最大刻度是60°
        mHydrologyChart1.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);  //设置X轴显示位置
        mHydrologyChart1.getXAxis().setDrawAxisLine(false);
        mHydrologyChart1.getXAxis().setDrawGridLines(false);                  //不绘制网格
        mHydrologyChart1.getLegend().setEnabled(false);                       //禁用图例
        mHydrologyChart1.getDescription().setEnabled(false);                  //禁用X轴描述
        mHydrologyChart1.getXAxis().setAxisMinimum(0f);
        mHydrologyChart1.getXAxis().setAxisMaximum(7f);

        //hydrology chart2
        mHydrologyChart2.setTouchEnabled(false);                              //禁止用户对图表进行操作
        mHydrologyChart2.getAxisRight().setEnabled(false);                    //禁用右侧Y轴
        mHydrologyChart2.getAxisLeft().setAxisMinimum(0f);                    //从0°开始
        mHydrologyChart2.getAxisLeft().setAxisMaximum(20f);                   //最大刻度是60°
        mHydrologyChart2.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);  //设置X轴显示位置
        mHydrologyChart2.getXAxis().setDrawAxisLine(false);
        mHydrologyChart2.getXAxis().setDrawGridLines(false);                  //不绘制网格
        mHydrologyChart2.getLegend().setEnabled(false);                       //禁用图例
        mHydrologyChart2.getDescription().setEnabled(false);                  //禁用X轴描述
    }

    private void initWeatherDescriptionView(){
        mCurrentWeatherIv = findViewById(R.id.activity_water_weather_main_current_weather_iv);
        mCurrentTempTv = findViewById(R.id.activity_water_weather_main_current_tempeature_tv);
        mCurrentTempRangeTv = findViewById(R.id.activity_water_weather_main_current_temperature_range_tv);
        mCurrentSensibleTempTv = findViewById(R.id.activity_water_weather_main_current_sensible_temperature_tv);

        mOneHourTv = findViewById(R.id.activity_water_weather_main_one_hour_tv);
        mOneHourWeatherIv = findViewById(R.id.activity_water_weather_main_one_hour_weather_iv);
        mOneHourTempTv = findViewById(R.id.activity_water_weather_main_one_hour_temperature_tv);

        mTwoHourTv = findViewById(R.id.activity_water_weather_main_two_hour_tv);
        mTwoHourWeatherIv = findViewById(R.id.activity_water_weather_main_two_hour_weather_iv);
        mTwoHourTempTv = findViewById(R.id.activity_water_weather_main_two_hour_temperature_tv);

        mThreeHourTv = findViewById(R.id.activity_water_weather_main_three_hour_tv);
        mThreeHourWeatherIv = findViewById(R.id.activity_water_weather_main_three_hour_weather_iv);
        mThreeHourTempTv = findViewById(R.id.activity_water_weather_main_three_hour_temperature_tv);

        mFourHourTv = findViewById(R.id.activity_water_weather_main_four_hour_tv);
        mFourHourWeatherIv = findViewById(R.id.activity_water_weather_main_four_hour_weather_iv);
        mFourHourTempTv = findViewById(R.id.activity_water_weather_main_four_hour_temperature_tv);

        mTodayWeatherIv = findViewById(R.id.activity_water_weather_main_today_weather_iv);
        mTodayWeatherDescTv = findViewById(R.id.activity_water_weather_main_today_weather_description_tv);
        mTodayTempRangeTv = findViewById(R.id.activity_water_weather_main_today_temperature_range_tv);

        mOneDayWeatherIv = findViewById(R.id.activity_water_weather_main_one_day_weather_iv);
        mOneDayWeatherDescTv = findViewById(R.id.activity_water_weather_main_one_day_weather_description_tv);
        mOneDayTempRangeTv = findViewById(R.id.activity_water_weather_main_one_day_temperature_range_tv);

        mTwoDayWeatherIv = findViewById(R.id.activity_water_weather_main_two_day_weather_iv);
        mTwoDayWeatherDescTv = findViewById(R.id.activity_water_weather_main_two_day_weather_description_tv);
        mTwoDayTempRangeTv = findViewById(R.id.activity_water_weather_main_two_day_temperature_range_tv);

        mThreeDayWeatherIv = findViewById(R.id.activity_water_weather_main_three_day_weather_iv);
        mThreeDayWeatherDescTv = findViewById(R.id.activity_water_weather_main_three_day_weather_description_tv);
        mThreeDayTempRangeTv = findViewById(R.id.activity_water_weather_main_three_day_temperature_range_tv);
    }
}
