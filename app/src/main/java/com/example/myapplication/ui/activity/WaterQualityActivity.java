package com.example.myapplication.ui.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.WaterQualityAdapter;
import com.example.myapplication.app.AppConst;
import com.example.myapplication.entity.BasicEntity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class WaterQualityActivity extends BaseActivity {
    private ImageView mCalendarBtn;
    private TextView mCalendarTv;

    private Calendar mCalendar;
    @Override
    protected int getContentViewId() {
        return R.layout.activity_water_quality;
    }

    @Override
    protected boolean useSupportedToolbar() {
        return true;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        getToolbar().setTitle(R.string.activity_water_quality_title);
        TextView tv_name = findViewById(R.id.tv_sub_header_left);
        tv_name.setText("水库水质");
        TextView tv_left = findViewById(R.id.tv_para_left);
        tv_left.setText("参数");

        mCalendarBtn = findViewById(R.id.iv_date_picker);
        mCalendarTv = findViewById(R.id.tv_date);
        mCalendarTv.setText(SimpleDateFormat.getDateInstance().format(new Date()));
        mCalendar = Calendar.getInstance();
        mCalendarBtn.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(WaterQualityActivity.this,
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

        WaterQualityAdapter mAdapter = new WaterQualityAdapter(dataList());
        RecyclerView mRecyclerView = findViewById(R.id.rv_water_quality);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

        Button button = findViewById(R.id.btn_graph);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(this, CommonGraphActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("activity_title", getString(R.string.activity_water_quality_title));

            ArrayList<String> list = new ArrayList<>();
            for (BasicEntity d : dataList()) {
                list.add(d.getNumber());
            }
            bundle.putStringArrayList("list", list);
            intent.putExtra("GraphType", AppConst.GraphDataType.GRAPH_TYPE_WATER_QUALITY);
            intent.putExtras(bundle);
            startActivity(intent);
        });
    }

    @Override
    protected void initData() {

    }

    private List<BasicEntity> dataList() {
        return new ArrayList<BasicEntity>() {{
            add(new BasicEntity("总氮", "0.79mg/L"));
            add(new BasicEntity("总磷", "0.19mg/L"));
            add(new BasicEntity("pH", "7.8"));
            add(new BasicEntity("电导率", "1902us/cm"));
            add(new BasicEntity("溶解氧", "4.7mg/L"));
            add(new BasicEntity("浑浊度", "0.91NTU"));
            add(new BasicEntity("高锰酸盐指数", "5.7mg/L"));
        }};
    }
}
