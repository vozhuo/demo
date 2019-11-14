package com.example.myapplication.model;

import android.annotation.SuppressLint;
import android.graphics.Color;

import com.example.myapplication.R;
import com.example.myapplication.app.AppConst;
import com.example.myapplication.entity.FutureHourWeatherEntity;
import com.example.myapplication.entity.GraphDisplayItem;
import com.example.myapplication.entity.SingleDayHydrologyData;
import com.example.myapplication.entity.SingleDayReservoirLevelEntity;
import com.example.myapplication.entity.SingleDayWeatherData;
import com.example.myapplication.entity.SingleHourReservoirLevelEntity;
import com.example.myapplication.entity.UAVVideoItemEntity;
import com.example.myapplication.entity.WaterWeatherEntity;
import com.example.myapplication.event.ReservoirLevelEvent;
import com.example.myapplication.event.UAVPatrolVideoEvent;
import com.example.myapplication.event.WaterWeatherEvent;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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

    public List<GraphDisplayItem> fetchGraphData(int graphType,List<String> descriptions){
        switch (graphType){
            case AppConst.GraphDataType.GRAPH_TYPE_DAM_SEEPAGE:{
                return generateDamSeepageGraphData(descriptions);
            }

            case AppConst.GraphDataType.GRAPH_TYPE_WATER_DIVERSION:{
                return generateWaterDiversionGraphData(descriptions);
            }

            default:
            case AppConst.GraphDataType.GRAPH_TYPE_WATER_QUALITY:{
                return generateWaterQualityGraphData(descriptions);
            }
        }
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

        int maxLevel = 500;
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

    private List<GraphDisplayItem> generateDamSeepageGraphData(List<String> descriptions){
        List<GraphDisplayItem> data = new ArrayList<>();
        List<String> subtitle1 = new ArrayList<>();
        subtitle1.add("7天渗透压力变化(KPa)");
        subtitle1.add("30天渗透压力变化(KPa)");
        subtitle1.add("12个月渗透压力变化");

        List<String> subtitle2 = new ArrayList<>();
        subtitle2.add("7天渗流量变化(L/s)");
        subtitle2.add("30天渗流量变化(L/s)");
        subtitle2.add("12个月渗流量变化(L/s)");


        GraphDisplayItem item1 = new GraphDisplayItem(descriptions.get(0),subtitle1,generateLineData(60,90, Color.parseColor("#AB313B")));
        GraphDisplayItem item2 = new GraphDisplayItem(descriptions.get(1),subtitle2,generateLineData(2,4,Color.parseColor("#0A2B68")));

        data.add(item1);
        data.add(item2);
        return data;
    }

    private List<GraphDisplayItem> generateWaterDiversionGraphData(List<String> descriptions){
        List<GraphDisplayItem> data = new ArrayList<>();
        List<String> subtitle = new ArrayList<>();
        subtitle.add("7天压力计变化(MPa)");
        subtitle.add("30天压力计变化(MPa)");
        subtitle.add("12个月压力计变化(MPa)");

        for (int i = 0;i < descriptions.size();i++){
            GraphDisplayItem item = new GraphDisplayItem(descriptions.get(i),subtitle,generateLineData(0,4,Color.parseColor("#0A2B68")));
            data.add(item);
        }
        return data;
    }

    private List<GraphDisplayItem> generateWaterQualityGraphData(List<String> descriptions){
        List<GraphDisplayItem> data = new ArrayList<>();

        List<String> subtitle1 = new ArrayList<>();
        subtitle1.add("7天总氮变化(mg/L)");
        subtitle1.add("30天总氮变化(mg/L)");
        subtitle1.add("12个月总氮变化(mg/L)");
        GraphDisplayItem item1 = new GraphDisplayItem(descriptions.get(0),subtitle1,generateLineData(0,2,Color.parseColor("#E4BD32")));
        data.add(item1);

        List<String> subtitle2 = Arrays.asList("7天总磷变化(mg/L)","30天总磷变化(mg/L)","12个月总磷变化(mg/L)");
        GraphDisplayItem item2 = new GraphDisplayItem(descriptions.get(1),subtitle2,generateLineData(0,2,Color.parseColor("#C26526")));
        data.add(item2);

        List<String> subtitle3 = Arrays.asList("7天ph变化","30天ph变化","12个月ph变化");
        GraphDisplayItem item3 = new GraphDisplayItem(descriptions.get(2),subtitle3,generateLineData(0,14,Color.parseColor("#598641")));
        data.add(item3);

        List<String> subtitle4 = Arrays.asList("7天电导率变化(us/cm)","30天电导率变化(us/cm)","12个月电导率变化(us/cm)");
        GraphDisplayItem item4 = new GraphDisplayItem(descriptions.get(3),subtitle4,generateLineData(0,2400,Color.parseColor("#0D6BC1")));
        data.add(item4);

        List<String> subtitle5 = Arrays.asList("7天溶解氧变化(mg/L)","30天溶解氧变化(mg/L)","12个月溶解氧变化(mg/L)");
        GraphDisplayItem item5 = new GraphDisplayItem(descriptions.get(4),subtitle5,generateLineData(0,6,Color.parseColor("#03B3F2")));
        data.add(item5);

        List<String> subtitle6 = Arrays.asList("7天浑浊度变化(NTU)","30天浑浊度变化(NTU)","12个月浑浊度变化(NTU)");
        GraphDisplayItem item6 = new GraphDisplayItem(descriptions.get(5),subtitle6,generateLineData(0,2,Color.parseColor("#6D675E")));
        data.add(item6);

        List<String> subtitle7 = Arrays.asList("7天高锰酸盐指数变化(mg/L)","30天高锰酸盐指数变化(mg/L)","12个月高锰酸盐指数变化(mg/L)");
        GraphDisplayItem item7 = new GraphDisplayItem(descriptions.get(6),subtitle7,generateLineData(0,6,Color.parseColor("#7445A9")));
        data.add(item7);
        return data;
    }

    private List<LineData> generateLineData(float min,float max,int color){
        Random random = new Random();
        List<Entry> weekEntries = new ArrayList<>();
        for (int i = 0; i < 7;i++){
            weekEntries.add(new Entry(i,random.nextInt((int) (max - min)) + random.nextFloat() + min));
        }
        LineDataSet weekLineDataSet = new LineDataSet(weekEntries,"week");
        weekLineDataSet.setDrawCircles(false);
        weekLineDataSet.setDrawValues(false);
        weekLineDataSet.setColor(color);
        LineData weekLineData = new LineData(weekLineDataSet);

        List<Entry> monthEntries = new ArrayList<>(weekEntries);
        for (int i = monthEntries.size();i < 30;i++){
            monthEntries.add(new Entry(i,random.nextInt((int)(max - min)) + random.nextFloat() + min));
        }
        LineDataSet monthLineDataSet = new LineDataSet(monthEntries,"month");
        monthLineDataSet.setDrawCircles(false);
        monthLineDataSet.setDrawValues(false);
        monthLineDataSet.setColor(color);
        LineData monthLineData = new LineData(monthLineDataSet);

        List<Entry> yearEntries = new ArrayList<>(monthEntries);
        for (int i = yearEntries.size();i < 365;i++){
            yearEntries.add(new Entry(i,random.nextInt((int)(max - min)) + random.nextFloat() + min));
        }
        LineDataSet yearLineDataSet = new LineDataSet(yearEntries,"year");
        yearLineDataSet.setDrawCircles(false);
        yearLineDataSet.setDrawValues(false);
        yearLineDataSet.setColor(color);
        LineData yearLineData = new LineData(yearLineDataSet);

        List<LineData> list = new ArrayList<>();
        list.add(weekLineData);
        list.add(monthLineData);
        list.add(yearLineData);

        return list;
    }
}
