package com.example.myapplication.ui.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.WaterDiversionAdapter;
import com.example.myapplication.entity.BasicEntity;
import com.example.myapplication.entity.WaterDiversionEntity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class WaterDiversionActivity extends AppCompatActivity {
    private Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_diversion);
        mToolbar = findViewById(R.id.common_toolbar);
        mToolbar.setTitle(R.string.activity_water_diversion_title);
        mToolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white);
        mToolbar.setNavigationOnClickListener(v -> finish());

        WaterDiversionAdapter mAdapter = new WaterDiversionAdapter(dataList());

        RecyclerView mRecyclerView = findViewById(R.id.rv_water_diversion);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            WaterDiversionEntity item = mAdapter.getItem(position);
            if (view.getId() == R.id.btn_water_graph) {
                Intent intent = new Intent(this, WaterDiversionGraphActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("activity_title", getString(R.string.activity_water_diversion_title));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        Button date_picker = findViewById(R.id.btn_calendar);

        String time = SimpleDateFormat.getDateInstance().format(new Date());
        date_picker.setText(time);

        final Calendar calendar = Calendar.getInstance();

        date_picker.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(WaterDiversionActivity.this,
                    (view, year, month, dayOfMonth) -> {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        date_picker.setText(SimpleDateFormat.getDateInstance().format(calendar.getTime()));

                    }, calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
        });
    }

    private List<WaterDiversionEntity> dataList() {
        return new ArrayList<WaterDiversionEntity>() {{
            add(new WaterDiversionEntity("进水口进口段", subList1()));
            add(new WaterDiversionEntity("进水口闸门段", subList2()));
            add(new WaterDiversionEntity("进水口渐变段", subList3()));
            add(new WaterDiversionEntity("引水明管", subList4()));
            add(new WaterDiversionEntity("引水隧洞", subList5()));
            add(new WaterDiversionEntity("压力管道", subList6()));
            add(new WaterDiversionEntity("厂房蝴蝶阀", subList7()));
            add(new WaterDiversionEntity("厂房蜗壳", subList8()));
            add(new WaterDiversionEntity("尾水隧洞", subList9()));
        }};
    }

    private List<BasicEntity> subList1() {
        String common = "进水口#";
        return new ArrayList<BasicEntity>() {{
            add(new BasicEntity(common + "1", "2.81MPa"));
            add(new BasicEntity(common + "2", "2.79MPa"));
            add(new BasicEntity(common + "3", "2.80MPa"));
            add(new BasicEntity(common + "4", "2.80MPa"));
        }};
    }

    private List<BasicEntity> subList2() {
        String common = "闸门#";
        return new ArrayList<BasicEntity>() {{
            add(new BasicEntity(common + "1", "3.01MPa"));
            add(new BasicEntity(common + "2", "3.02MPa"));
            add(new BasicEntity(common + "3", "3.00MPa"));
            add(new BasicEntity(common + "4", "2.98MPa"));
        }};
    }

    private List<BasicEntity> subList3() {
        String common = "输水管道#";
        return new ArrayList<BasicEntity>() {{
            add(new BasicEntity(common + "1", "3.21MPa"));
            add(new BasicEntity(common + "2", "3.20MPa"));
            add(new BasicEntity(common + "3", "3.23MPa"));
            add(new BasicEntity(common + "4", "3.22MPa"));
        }};
    }

    private List<BasicEntity> subList4() {
        String common = "引水管道#";
        return new ArrayList<BasicEntity>() {{
            add(new BasicEntity(common + "1", "3.19MPa"));
            add(new BasicEntity(common + "2", "3.18MPa"));
            add(new BasicEntity(common + "3", "3.20MPa"));
            add(new BasicEntity(common + "4", "3.22MPa"));
        }};
    }

    private List<BasicEntity> subList5() {
        String common = "隧洞#";
        return new ArrayList<BasicEntity>() {{
            add(new BasicEntity(common + "1", "3.30MPa"));
            add(new BasicEntity(common + "2", "3.31MPa"));
        }};
    }

    private List<BasicEntity> subList6() {
        String common = "压力钢管#";
        return new ArrayList<BasicEntity>() {{
            add(new BasicEntity(common + "1", "4.12MPa"));
            add(new BasicEntity(common + "2", "4.11MPa"));
            add(new BasicEntity(common + "3", "4.10MPa"));
            add(new BasicEntity(common + "4", "4.09MPa"));
        }};
    }

    private List<BasicEntity> subList7() {
        String common = "厂房";
        return new ArrayList<BasicEntity>() {{
            add(new BasicEntity(common + "01-01", "2.30MPa"));
            add(new BasicEntity(common + "01-02", "2.04MPa"));
            add(new BasicEntity(common + "01-03", "2.34MPa"));
            add(new BasicEntity(common + "02-01", "2.13MPa"));
            add(new BasicEntity(common + "02-02", "2.48MPa"));
            add(new BasicEntity(common + "02-03", "2.11MPa"));
            add(new BasicEntity(common + "03-01", "2.31MPa"));
            add(new BasicEntity(common + "03-02", "2.56MPa"));
            add(new BasicEntity(common + "03-03", "2.45MPa"));
            add(new BasicEntity(common + "04-01", "2.34MPa"));
            add(new BasicEntity(common + "04-02", "2.09MPa"));
            add(new BasicEntity(common + "04-03", "2.31MPa"));
        }};
    }

    private List<BasicEntity> subList8() {
        String common = "蜗壳#";
        return new ArrayList<BasicEntity>() {{
            add(new BasicEntity(common + "1", "0.33MPa"));
            add(new BasicEntity(common + "2", "0.30MPa"));
            add(new BasicEntity(common + "3", "0.30MPa"));
            add(new BasicEntity(common + "4", "0.29MPa"));
        }};
    }

    private List<BasicEntity> subList9() {
        String common = "尾水隧洞#";
        return new ArrayList<BasicEntity>() {{
            add(new BasicEntity(common + "1", "0.33MPa"));
            add(new BasicEntity(common + "2", "0.45MPa"));
            add(new BasicEntity(common + "3", "0.38MPa"));
            add(new BasicEntity(common + "4", "0.67MPa"));
            add(new BasicEntity(common + "5", "0.48MPa"));
            add(new BasicEntity(common + "6", "0.59MPa"));
            add(new BasicEntity(common + "7", "0.41MPa"));
            add(new BasicEntity(common + "8", "0.78MPa"));
        }};
    }
}
