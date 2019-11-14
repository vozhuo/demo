package com.example.myapplication.entity;

import com.github.mikephil.charting.data.LineData;

import java.util.List;

public class GraphDisplayItem{

    private String itemTitleText;
    private List<String> graphTitleTextList;
    private List<LineData> graphLineDataList;

    public GraphDisplayItem(String itemTitleText, List<String> graphTitleTextList, List<LineData> graphLineDataList) {
        this.itemTitleText = itemTitleText;
        this.graphTitleTextList = graphTitleTextList;
        this.graphLineDataList = graphLineDataList;
    }

    public String getItemTitleText() {
        return itemTitleText;
    }

    public void setItemTitleText(String itemTitleText) {
        this.itemTitleText = itemTitleText;
    }

    public List<String> getGraphTitleTextList() {
        return graphTitleTextList;
    }

    public void setGraphTitleTextList(List<String> graphTitleTextList) {
        this.graphTitleTextList = graphTitleTextList;
    }

    public List<LineData> getGraphLineDataList() {
        return graphLineDataList;
    }

    public void setGraphLineDataList(List<LineData> graphLineDataList) {
        this.graphLineDataList = graphLineDataList;
    }
}
