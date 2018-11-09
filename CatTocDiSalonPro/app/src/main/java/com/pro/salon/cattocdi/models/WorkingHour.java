package com.pro.salon.cattocdi.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class WorkingHour implements Serializable{
    @SerializedName("DayOfWeek")
    private int date;
    @SerializedName("FromHour")
    private String startTime;
    @SerializedName("ToHour")
    private String endTime;
    @SerializedName("IsClosed")
    private boolean isClose;

    public WorkingHour() {
    }

    public WorkingHour(int date, String startTime, String endTime, boolean isClose) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isClose = isClose;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public boolean isClose() {
        return isClose;
    }

    public void setClose(boolean close) {
        isClose = close;
    }

    public int getdate() {
        return date;
    }

    public void setdate(int date) {
        this.date = date;
    }

    public String getStartTime() {
        String[] item = startTime.split(":");
        return item[0] + ":" + item[1] ;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        String[] item = endTime.split(":");
        return item[0] + ":" + item[1] ;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
