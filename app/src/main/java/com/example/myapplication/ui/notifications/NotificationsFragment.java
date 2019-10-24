package com.example.myapplication.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class NotificationsFragment extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

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