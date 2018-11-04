package com.pro.salon.cattocdi.models;

import com.google.gson.annotations.SerializedName;

public class WorkingHour {
    @SerializedName("DayOfWeek")
    private int date;
    @SerializedName("FromHour")
    private String startTime;
    @SerializedName("ToHour")
    private String endTime;

    public WorkingHour() {
    }

    public WorkingHour(int date, String startTime, String endTime) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
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
