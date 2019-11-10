package com.example.myapplication.util;

import android.annotation.SuppressLint;

import com.github.mikephil.charting.formatter.ValueFormatter;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateXValueFormatter extends ValueFormatter {

    private SimpleDateFormat mFormatter;
    private boolean mIsReverse;

    public DateXValueFormatter(boolean reverse){
        mFormatter = new SimpleDateFormat("MM-dd");
        mIsReverse = reverse;
    }

    @SuppressLint("SimpleDateFormat")
    public DateXValueFormatter() {
        this(false);
    }

    @Override
    public String getFormattedValue(float value) {
        int index = (int) value;
        Calendar calendar = Calendar.getInstance();

        if (!mIsReverse) {
            calendar.add(Calendar.DAY_OF_MONTH, index);
        }else {
            calendar.add(Calendar.DAY_OF_MONTH, -index);
        }
        return mFormatter.format(calendar.getTime());
    }

}
