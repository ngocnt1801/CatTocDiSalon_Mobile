package com.pro.salon.cattocdi.models;

import com.google.gson.annotations.SerializedName;

public class WorkingHour {
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
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
