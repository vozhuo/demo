package com.example.myapplication.ui.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.DamStressAdapter;
import com.example.myapplication.entity.DamStressEntity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DamStressActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dam_stress);
        mToolbar = findViewById(R.id.common_toolbar);
        mToolbar.setTitle(R.string.activity_dam_stress_title);
        mToolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white);
        mToolbar.setNavigationOnClickListener(v -> finish());

        DamStressAdapter mAdapter = new DamStressAdapter(dataList());

        RecyclerView mRecyclerView = findViewById(R.id.rv_stress_nephogram);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);


        Button date_picker = findViewById(R.id.btn_calendar);

        String time = SimpleDateFormat.getDateInstance().format(new Date());
        date_picker.setText(time);

        final Calendar calendar = Calendar.getInstance();
        
        ImageButton ib_date_picker = findViewById(R.id.ib_date_picker);

        date_picker.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(DamStressActivity.this,
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

    private List<DamStressEntity> dataList() {
        return new ArrayList<DamStressEntity>() {{
            add(new DamStressEntity("大坝A-A剖面应力云图", Arrays.asList(-0.29, -3.21, 0.43)));
            add(new DamStressEntity("大坝B-B剖面应力云图", Arrays.asList(-0.13, -1.37, 0.10)));
            add(new DamStressEntity("大坝C-C剖面应力云图", Arrays.asList(-0.43, -5.98, 0.33)));
        }};
    }

}
