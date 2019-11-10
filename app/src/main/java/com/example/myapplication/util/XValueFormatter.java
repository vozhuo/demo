package com.example.myapplication.util;

import android.annotation.SuppressLint;
import android.util.Log;

import com.github.mikephil.charting.formatter.ValueFormatter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class XValueFormatter extends ValueFormatter {

    private Calendar mCalendar;
    private SimpleDateFormat mDateFormatter;
    private Date mAnchorDate;
    private int mType;

    public static final int DATE_TYPE_WEEK = 1;
    public static final int DATE_TYPE_MONTH = 2;
    public static final int DATE_TYPE_YEAR = 3;

    @SuppressLint("SimpleDateFormat")
    public XValueFormatter(int dateType, Date anchor) {
        mType = dateType;
        mAnchorDate = anchor;
        mCalendar = Calendar.getInstance();
        mCalendar.setTime(anchor);
        if (dateType == DATE_TYPE_WEEK) {
            mDateFormatter = new SimpleDateFormat("MM-dd");
            mCalendar.add(Calendar.WEEK_OF_MONTH,-1);
            //Log.d("cylog","Week:" + SimpleDateFormat.getDateInstance().format(mCalendar.getTime()));
        } else if (dateType == DATE_TYPE_MONTH) {
            mDateFormatter = new SimpleDateFormat("MM-dd");
            mCalendar.add(Calendar.MONTH,-1);
//            Log.d("cylog","Month:" + SimpleDateFormat.getDateInstance().format(mCalendar.getTime()));
        }else {
            mDateFormatter = new SimpleDateFormat("yyyy-MM");
            mCalendar.add(Calendar.YEAR,-1);
//            Log.d("cylog","Year:" + SimpleDateFormat.getDateInstance().format(mCalendar.getTime()));
        }
    }

    @Override
    public String getFormattedValue(float value) {
        int index = (int) value;
        Calendar calendar = Calendar.getInstance();
        mCalendar.add(Calendar.DAY_OF_WEEK,1);

        if (mType == DATE_TYPE_MONTH){
            Log.d("cylog","Year:" + SimpleDateFormat.getDateInstance().format(mCalendar.getTime()));
        }

        return mDateFormatter.format(mCalendar.getTime());
    }
}
