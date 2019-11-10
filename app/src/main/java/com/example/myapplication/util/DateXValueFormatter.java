package com.example.myapplication.util;

import android.annotation.SuppressLint;

import com.github.mikephil.charting.formatter.ValueFormatter;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateXValueFormatter extends ValueFormatter {

    private SimpleDateFormat mFormatter;

    @SuppressLint("SimpleDateFormat")
    public DateXValueFormatter() {
        mFormatter = new SimpleDateFormat("MM-dd");
    }

    @Override
    public String getFormattedValue(float value) {
        int index = (int) value;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH,index);
        return mFormatter.format(calendar.getTime());
    }

}
