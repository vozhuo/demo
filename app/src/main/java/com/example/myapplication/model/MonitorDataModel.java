package com.example.myapplication.model;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.myapplication.R;
import com.example.myapplication.entity.FutureHourWeatherEntity;
import com.example.myapplication.entity.SingleDayHydrologyData;
import com.example.myapplication.entity.SingleDayReservoirLevelEntity;
import com.example.myapplication.entity.SingleDayWeatherData;
import com.example.myapplication.entity.SingleHourReservoirLevelEntity;
import com.example.myapplication.entity.UAVVideoItemEntity;
import com.example.myapplication.entity.WaterWeatherEntity;
import com.example.myapplication.event.ReservoirLevelEvent;
import com.example.myapplication.event.UAVPatrolVideoEvent;
import com.example.myapplication.event.WaterWeatherEvent;
import com.example.myapplication.util.WeatherUtils;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

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

    public void queryWaterWeatherData(){
        EventBus.getDefault().post(new WaterWeatherEvent(generateWaterWeatherData()));
    }

    public void queryReservoirLevelData(){
        EventBus.getDefault().post(new ReservoirLevelEvent(generateReservoirLevelData()));
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
        UAVVideoItemEntity entity1 = new UAVVideoItemEntity(calendar.getTime(),"K12", R.mipmap.ic_uav_patrol1,-1);

        //two hour ago
        calendar.add(Calendar.HOUR,-1);
        UAVVideoItemEntity entity2 = new UAVVideoItemEntity(calendar.getTime(),"PB32",R.mipmap.ic_uav_patrol2,-1);

        List<UAVVideoItemEntity> list = new ArrayList<>();
        list.add(entity1);
        list.add(entity2);

        return list;
    }

    private List<UAVVideoItemEntity> generateYesterdayData(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,-1);
        UAVVideoItemEntity entity1 = new UAVVideoItemEntity(calendar.getTime(),"K12", R.mipmap.ic_uav_patrol1,-1);
        List<UAVVideoItemEntity> list = new ArrayList<>();
        list.add(entity1);
        return list;
    }

    private List<SingleDayReservoirLevelEntity> generateReservoirLevelData(){
        List<SingleDayReservoirLevelEntity> data = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);       //把时间设置为今天的00:00:00:000

        int duration = 365;                           //过去<duration>天的数据
        for (int i = 0;i < duration;i++){
            data.add(simulateOneDayReservoirLevel(calendar.getTime()));
            calendar.add(Calendar.DAY_OF_MONTH,-1);
        }

        return data;
    }

    private WaterWeatherEntity generateWaterWeatherData(){
        Random random = new Random();
        int minTem = 15;
        int maxTem = 30;
        int step = 5;
        WaterWeatherEntity entity = new WaterWeatherEntity();
        Calendar calendar = Calendar.getInstance();

        List<FutureHourWeatherEntity> hourWeatherList = new ArrayList<>();
        for (int i = 0;i < 24;i++){
            hourWeatherList.add(simulateAnHourWeather(minTem,maxTem,calendar.getTime()));
            calendar.add(Calendar.HOUR_OF_DAY,1);
        }

        calendar = Calendar.getInstance();
        List<SingleDayWeatherData> dayWeatherDataList = new ArrayList<>();
        for (int i = 0;i < 30;i++){
            dayWeatherDataList.add(simulateSingleDayWeather(random.nextInt(step) + minTem,random.nextInt(step) + maxTem,calendar.getTime()));
            calendar.add(Calendar.DAY_OF_MONTH,1);
        }

        calendar = Calendar.getInstance();
        List<SingleDayHydrologyData> dayHydrologyData = new ArrayList<>();
        for (int i = 0;i < 30;i++){
            dayHydrologyData.add(simulateSingleDayHydrology(random.nextInt(5) + 2,random.nextInt(5) + 10,calendar.getTime()));
            calendar.add(Calendar.DAY_OF_MONTH,1);
        }

        entity.setFutureHourWeatherData(hourWeatherList);
        entity.setMonthWeatherData(dayWeatherDataList);
        entity.setMonthHydrologyData(dayHydrologyData);
        return entity;
    }

    private SingleDayWeatherData simulateSingleDayWeather(int minValue,int maxValue,Date date){
        Random random = new Random();
        SingleDayWeatherData dayWeatherData = new SingleDayWeatherData();
        dayWeatherData.setDate(date);
        dayWeatherData.setMaxTemperature(maxValue);
        dayWeatherData.setMinTemperature(minValue);
        dayWeatherData.setAvgTemperature((maxValue + minValue) / 2);
        dayWeatherData.setWeatherType(random.nextInt(5));

        List<Float> hourTemperatures = new ArrayList<>();
        for (int i = 0;i < 24;i++){
            hourTemperatures.add((float) (random.nextInt(maxValue - minValue) + minValue));
        }

        List<Float> sensibleTemperatures = new ArrayList<>();
        for (int i = 0;i < 24;i++){
            sensibleTemperatures.add((float) (random.nextInt(maxValue - minValue - 2) + minValue));
        }

        List<Integer> weatherTypes = new ArrayList<>();
        for (int i = 0;i < 24;i++){
            weatherTypes.add(random.nextInt(5));
        }

        dayWeatherData.setHoursTemperatureList(hourTemperatures);
        dayWeatherData.setHoursSensibleTemperatureList(sensibleTemperatures);
        dayWeatherData.setWeatherTypeList(weatherTypes);
        return dayWeatherData;
    }

    private SingleDayHydrologyData simulateSingleDayHydrology(int minValue,int maxValue,Date date){
        Random random = new Random();
        SingleDayHydrologyData dayHydrologyData = new SingleDayHydrologyData();
        dayHydrologyData.setDate(date);
        dayHydrologyData.setRainfallCapacity(random.nextInt(maxValue - minValue) + minValue);
        dayHydrologyData.setEvaporationCapacity(random.nextInt(maxValue - minValue) + minValue);
        dayHydrologyData.setInflowCapacity(random.nextInt(20000) + 50000);
        dayHydrologyData.setUsedWaterCapacity(random.nextInt(20000) + 50000);
        dayHydrologyData.setAmountOfWaterCapacity(random.nextInt(20000) + 10000);
        return dayHydrologyData;
    }

    private FutureHourWeatherEntity simulateAnHourWeather(int minTemp,int maxTemp,Date date){
        Random random = new Random();
        int temperature = random.nextInt(maxTemp - minTemp) + minTemp;
        int weatherType = random.nextInt(5);
        return new FutureHourWeatherEntity(temperature,weatherType,date);
    }

    private SingleDayReservoirLevelEntity simulateOneDayReservoirLevel(Date date){
        Random random = new Random();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        SingleDayReservoirLevelEntity entity = new SingleDayReservoirLevelEntity();
        List<SingleHourReservoirLevelEntity> hourReservoirLevelList = new ArrayList<>();

        int maxLevel = 600;
        int minLevel = 400;
        int totalLevel = 0;

        //每个4小时测量一次水位，因此一天有6个水位的数据
        for (int i = 0;i < 6;i++){
            SingleHourReservoirLevelEntity hourEntity = new SingleHourReservoirLevelEntity();
            hourEntity.setDate(calendar.getTime());
            hourEntity.setReservoirLevel(random.nextInt(maxLevel - minLevel) + minLevel + random.nextFloat());
            hourReservoirLevelList.add(hourEntity);
            calendar.add(Calendar.HOUR_OF_DAY,-4);
            totalLevel += hourEntity.getReservoirLevel();
        }

        entity.setHourReservoirLevelList(hourReservoirLevelList);
        entity.setAverageReservoirLevel(totalLevel / 6);
        entity.setDate(date);
        return entity;
    }
}
