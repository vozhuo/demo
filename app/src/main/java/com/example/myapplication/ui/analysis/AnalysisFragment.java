package com.example.myapplication.ui.analysis;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AnalysisFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    private Button bt_start;

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

        bt_start = root.findViewById(R.id.bt_start);
//        final Button bt_end = root.findViewById(R.id.bt_end);

        Date date = new Date();
        String time = SimpleDateFormat.getDateInstance().format(date);
        bt_start.setText(time);


//        Calendar now = Calendar.getInstance();
//        DatePickerDialog dpd = DatePickerDialog.newInstance(
//                AnalysisFragment.this,
//                now.get(Calendar.YEAR), // Initial year selection
//                now.get(Calendar.MONTH), // Initial month selection
//                now.get(Calendar.DAY_OF_MONTH) // Inital day selection
//        );
//        dpd.show(getFragmentManager(), "Datepickerdialog");

        return root;
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = "You picked the following date: " + dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
        bt_start.setText(date);
    }
}