package com.example.myapplication.ui.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AnalysisFragment extends Fragment {

    private Button date_picker;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_analysis, container, false);

        LineChart mLineChart = root.findViewById(R.id.lineChart);
        List<Entry> entryList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            entryList.add(new Entry(i, (float) (Math.random()) * 25));
        }
        //一条线
        LineDataSet lineDataSet = new LineDataSet(entryList, "气温");
        mLineChart.setData(new LineData(lineDataSet));

        return root;
    }
}