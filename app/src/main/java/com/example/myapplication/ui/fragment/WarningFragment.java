package com.example.myapplication.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.WarningAdapter;
import com.example.myapplication.entity.SecurityEntity;
import com.example.myapplication.entity.WarningEntity;

import java.util.ArrayList;
import java.util.List;

public class WarningFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_warning, container, false);

        WarningAdapter mAdapter = new WarningAdapter(dataList());

        RecyclerView mRecyclerView = root.findViewById(R.id.rv_warning);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);

        return root;
    }

    private List<SecurityEntity> dataList() {
        return new ArrayList<SecurityEntity>() {{
            add(new SecurityEntity("挡水坝", subList1()));
            add(new SecurityEntity("溢流坝", subList2()));
            add(new SecurityEntity("引水系统", subList3()));
            add(new SecurityEntity("水库水质", subList4()));
            add(new SecurityEntity("库区安全", subList5()));
        }};
    }

    private List<WarningEntity> subList1() {
        return new ArrayList<WarningEntity>() {{
            add(new WarningEntity("2019-5-29 03:50", "水平位移监测点（Z-20）", "8.77mm(5.33%)"));
            add(new WarningEntity("2019-4-03 09:07", "坝址应力监测点（9T-4）", "4.19MPa(拉应力)"));
        }};
    }

    private List<WarningEntity> subList2() {
        return new ArrayList<WarningEntity>() {{
            add(new WarningEntity());
        }};
    }

    private List<WarningEntity> subList3() {
        return new ArrayList<WarningEntity>() {{
            add(new WarningEntity("2019-8-20 08:20", "进水口进水段#1（WZ-1）", "4.52MPa(↑)"));
            add(new WarningEntity("2019-6-17 12:34", "压力钢管#1（WZ-18）", "5.32MPa (↑)"));
            add(new WarningEntity("2019-4-23 01:31", "隧洞#1（WZ-16）", "6.34MPa (↑)"));
            add(new WarningEntity("2019-4-01 18:56", "厂房04-03（ WZ-30 ）", "故障"));
        }};
    }

    private List<WarningEntity> subList4() {
        return new ArrayList<WarningEntity>() {{
            add(new WarningEntity("2019-8-30 01:25", "总氮（CBW-21）", "1.02mg/L（＜1mg/L）"));
            add(new WarningEntity("2019-5-11 05:10", "总磷（CBW-33） \npH （CBW-25）", "0.21mg/L(＜0.2mg/L) \n5.8(6～9)"));
            add(new WarningEntity("2019-4-30 11:50", "总氮（CBW-21）", "1.02mg/L（＜1mg/L"));
        }};
    }

    private List<WarningEntity> subList5() {
        return new ArrayList<WarningEntity>() {{
            add(new WarningEntity("2019-9-20 12:30", "视频监控（AE-14）", "边坡落石"));
            add(new WarningEntity("2019-9-13 21:33", "视频监控（AE-20）", "有人进入库区"));
            add(new WarningEntity("2019-9-01 20:32", "视频监控（AE-23）", "有人进入库区"));
            add(new WarningEntity("2019-8-11 15:51", "视频监控（AE-24）", "故障"));
            add(new WarningEntity("2019-8-03 12:37", "视频监控（AE-15）", "有人进入库区"));
            add(new WarningEntity("2019-7-29 19:07", "视频监控（AE-29）", "故障"));
            add(new WarningEntity("2019-7-03 08:31", "视频监控（AE-11）", "有人进入库区"));
            add(new WarningEntity("2019-4-30 06:23", "视频监控（AE-23） \n水库库区（WM-3）", "有人向水库投入杂物 \n库区水位上升速度异常"));
            add(new WarningEntity("2019-5-29 03:50", "视频监控（AE-21） \n视频监控（AE-23）", "有人进入库区 \n有人进入库区"));
            add(new WarningEntity("2019-3-01 15:34", "视频监控（AE-20）", "故障"));
        }};
    }
}




