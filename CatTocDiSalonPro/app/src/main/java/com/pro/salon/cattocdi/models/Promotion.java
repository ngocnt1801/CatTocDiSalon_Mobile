package com.pro.salon.cattocdi.models;

import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class Promotion {
    @SerializedName("SalonId")
    private int salonId;
    @SerializedName("PromotionId")
    private int id;
    @SerializedName("StartTime")
    private Timestamp startPeriod;
    @SerializedName("EndTime")
    private Timestamp endPeriod;
    @SerializedName("Description")
    private String description;
    @SerializedName("DiscountPercent")
    private int discount;

    public Promotion() {
    }

    public Promotion(int id, Timestamp startPeriod, Timestamp endPeriod, String description, int discount) {
        this.id = id;
        this.startPeriod = startPeriod;
        this.endPeriod = endPeriod;
        this.description = description;
        this.discount = discount;
    }

    public int getSalonId() {
        return salonId;
    }

    public void setSalonId(int salonId) {
        this.salonId = salonId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getStartPeriod() {
        return startPeriod;
    }

    public void setStartPeriod(Timestamp startPeriod) {
        this.startPeriod = startPeriod;
    }

    public Timestamp getEndPeriod() {
        return endPeriod;
    }

    public void setEndPeriod(Timestamp endPeriod) {
        this.endPeriod = endPeriod;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
