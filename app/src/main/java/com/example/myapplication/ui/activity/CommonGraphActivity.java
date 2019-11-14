package com.example.myapplication.ui.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.CommonGrpahItemAdapter;
import com.example.myapplication.adapter.WaterGraphAdapter;
import com.example.myapplication.app.AppConst;
import com.example.myapplication.entity.GraphDisplayItem;
import com.example.myapplication.model.MonitorDataModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CommonGraphActivity extends BaseActivity {

    private ImageView mCalendarBtn;
    private TextView mCalendarTv;
    private RecyclerView mRecyclerView;

    private Calendar mCalendar;
    private CommonGrpahItemAdapter mAdapter;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_water_diversion_graph;
    }

    @Override
    protected boolean useSupportedToolbar() {
        return true;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        getToolbar().setTitle(bundle.getString("activity_title"));


        ArrayList<String> s = bundle.getStringArrayList("list");

        mCalendarBtn = findViewById(R.id.iv_date_picker);
        mCalendarTv = findViewById(R.id.tv_date);
        mCalendarTv.setText(SimpleDateFormat.getDateInstance().format(new Date()));
        mCalendar = Calendar.getInstance();
        mCalendarBtn.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(CommonGraphActivity.this,
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


        mRecyclerView = findViewById(R.id.common_graph_activity_rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void initData() {
        Bundle bundle = getIntent().getExtras();
        ArrayList<String> titles = bundle.getStringArrayList("list");
        int graphType = getIntent().getIntExtra("GraphType", AppConst.GraphDataType.GRAPH_TYPE_DAM_SEEPAGE);
        List<GraphDisplayItem> items = MonitorDataModel.getInstance().fetchGraphData(graphType,titles);
        mAdapter = new CommonGrpahItemAdapter(items);
        mRecyclerView.setAdapter(mAdapter);
    }
}
