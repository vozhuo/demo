package com.example.myapplication.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnChangeLisener;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
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

        final Button bt_start = root.findViewById(R.id.bt_start);
        final Button bt_end = root.findViewById(R.id.bt_end);
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, -1);

        bt_start.setText(SimpleDateFormat.getDateTimeInstance().format(c.getTime()));
        bt_end.setText(SimpleDateFormat.getDateTimeInstance().format(date));

        bt_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickDialog dialog = new DatePickDialog(getContext());
                dialog.show();
                //设置上下年分限制
                dialog.setYearLimt(5);
                //设置标题
                dialog.setTitle("选择时间");
                //设置类型
                dialog.setType(DateType.TYPE_ALL);
                //设置消息体的显示格式，日期格式
                dialog.setMessageFormat("yyyy-MM-dd HH:mm");
                //设置点击确定按钮回调
                dialog.setOnSureLisener(new OnSureLisener() {
                    @Override
                    public void onSure(Date date) {
                        Calendar c = Calendar.getInstance();
                        c.setTime(date);
                        c.add(Calendar.DAY_OF_MONTH, 1);
                        Date sDate = c.getTime();


                        String time = SimpleDateFormat.getDateTimeInstance().format(date);
                        bt_start.setText(time);
                        bt_end.setText(SimpleDateFormat.getDateTimeInstance().format(sDate));
                    }
                });

            }
        });





        return root;
    }
}