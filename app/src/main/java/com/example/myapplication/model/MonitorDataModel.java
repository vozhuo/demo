package com.example.myapplication.model;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.myapplication.R;
import com.example.myapplication.entity.UAVVideoItemEntity;
import com.example.myapplication.event.UAVPatrolVideoEvent;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MonitorDataModel{

    private SimpleDateFormat mSimpleDateFormat;

    @SuppressLint("SimpleDateFormat")
    private MonitorDataModel(){
        mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    public static MonitorDataModel getInstance(){
        return MonitorDataModelHolder.sInstance;
    }

    private static class MonitorDataModelHolder{
        private static final MonitorDataModel sInstance = new MonitorDataModel();
    }

    public void queryUAVVideobyDate(Date queryDate){
        //-------------start of test code------------------
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,-1);
        Date yesterdayDate = calendar.getTime();

        String todayStr = mSimpleDateFormat.format(date);
        String yesterdayStr = mSimpleDateFormat.format(yesterdayDate);
        String queryDayStr = mSimpleDateFormat.format(queryDate);

        //Log.d("ccc","Today(" + todayStr + ")\n" + "Yesterday(" + yesterdayStr + ")\n" + "QueryDay(" + queryDayStr + ")");

        //今天的数据
        if (todayStr.equals(queryDayStr)){
            EventBus.getDefault().post(new UAVPatrolVideoEvent(generateTodayData()));
        }else if (yesterdayStr.equals(queryDayStr)){
            //昨天的数据
            EventBus.getDefault().post(new UAVPatrolVideoEvent(generateYesterdayData()));
        }else{
            //其余时间段的数据均为空
            EventBus.getDefault().post(new UAVPatrolVideoEvent(new ArrayList<>()));
        }

        //-------------end of test code--------------------
    }

    private List<UAVVideoItemEntity> generateTodayData(){
        Calendar calendar = Calendar.getInstance();

        //an hour ago
        calendar.add(Calendar.HOUR,-1);
        UAVVideoItemEntity entity1 = new UAVVideoItemEntity(calendar.getTime(),"K12", R.mipmap.video_shortcut1,null);

        //two hour ago
        calendar.add(Calendar.HOUR,-1);
        UAVVideoItemEntity entity2 = new UAVVideoItemEntity(calendar.getTime(),"PB32",R.mipmap.video_shortcut2,null);

        List<UAVVideoItemEntity> list = new ArrayList<>();
        list.add(entity1);
        list.add(entity2);

        return list;
    }

    private List<UAVVideoItemEntity> generateYesterdayData(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,-1);
        UAVVideoItemEntity entity1 = new UAVVideoItemEntity(calendar.getTime(),"K12", R.mipmap.video_shortcut1,null);
        List<UAVVideoItemEntity> list = new ArrayList<>();
        list.add(entity1);
        return list;
    }

}
