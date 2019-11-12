package com.example.myapplication.util;

import android.annotation.SuppressLint;
import android.util.Log;

import com.github.mikephil.charting.formatter.ValueFormatter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ReverseDateXValueFormatter extends ValueFormatter {

    private Calendar mCalendar;
    private SimpleDateFormat mDateFormatter;

    public static final int DATE_TYPE_WEEK = 1;
    public static final int DATE_TYPE_MONTH = 2;
    public static final int DATE_TYPE_YEAR = 3;

    @SuppressLint("SimpleDateFormat")
    public ReverseDateXValueFormatter(int dateType, Date anchor) {
        mCalendar = Calendar.getInstance();
        mCalendar.setTime(anchor);
        if (dateType == DATE_TYPE_WEEK) {
            mDateFormatter = new SimpleDateFormat("MM-dd");
            mCalendar.add(Calendar.WEEK_OF_MONTH,-1);
        } else if (dateType == DATE_TYPE_MONTH) {
            mDateFormatter = new SimpleDateFormat("MM-dd");
            mCalendar.add(Calendar.MONTH,-1);
        }else {
            mDateFormatter = new SimpleDateFormat("yyyy-MM");
            mCalendar.add(Calendar.YEAR,-1);
        }
    }

    @Override
    public String getFormattedValue(float value) {
        int index = (int) value;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mCalendar.getTime());
        calendar.add(Calendar.DAY_OF_WEEK, index);
        return mDateFormatter.format(calendar.getTime());
    }
}
