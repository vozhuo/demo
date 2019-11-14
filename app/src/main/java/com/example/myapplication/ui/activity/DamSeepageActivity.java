package com.example.myapplication.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.myapplication.R;
import com.example.myapplication.app.AppConst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DamSeepageActivity extends BaseActivity {

    @Override
    protected int getContentViewId() {
        return R.layout.activity_dam_seepage;
    }

    @Override
    protected boolean useSupportedToolbar() {
        return true;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        getToolbar().setTitle(R.string.activity_dam_seepage_title);
        TextView tv_sub_header_center = findViewById(R.id.tv_sub_header_center);
        tv_sub_header_center.setText("坝体渗流监测布置图");

        Spinner spinner = findViewById(R.id.spinner);

        List<String> plants = new ArrayList<>();

        for (int i = 1; i < 22; i++) {
            plants.add("G-" + i);
        }
        for (int i = 301; i < 304; i++) {
            plants.add("SPL" + i);
        }
        plants.add("监测点");

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, plants
        ) {
            @Override
            public int getCount() {
                return super.getCount() - 1;
            }
        };

        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);
        spinner.setSelection(plants.size() - 1);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != plants.size() - 1) {
                    Intent intent = new Intent(DamSeepageActivity.this, CommonGraphActivity.class);

                    Bundle bundle = new Bundle();
                    bundle.putString("activity_title", getString(R.string.activity_dam_seepage_title));
                    ArrayList<String> list = new ArrayList<>(Arrays.asList(
                            parent.getSelectedItem().toString() + "渗压计",
                            parent.getSelectedItem().toString() + "渗流量"));
                    bundle.putStringArrayList("list", list);
                    intent.putExtra("GraphType", AppConst.GraphDataType.GRAPH_TYPE_DAM_SEEPAGE);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    protected void initData() {

    }
}
