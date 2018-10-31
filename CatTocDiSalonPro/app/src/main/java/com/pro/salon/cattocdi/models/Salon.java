package com.pro.salon.cattocdi.models;

import java.sql.Timestamp;

public class Salon {
    private String salonId;
    private String name;
    private float ratingNumber;
    private boolean full;
    private int discount;
    private String imgUrl;
    private int reviewsAmount;
    private Timestamp startTime;
    private double latitude;
    private double longtitude;


    public Salon() {
    }

    public Salon(String name, float ratingNumber, boolean full, int discount, String imgUrl, int reviewsAmount, Timestamp startTime, double latitude, double longtitude) {
        this.name = name;
        this.ratingNumber = ratingNumber;
        this.full = full;
        this.discount = discount;
        this.imgUrl = imgUrl;
        this.reviewsAmount = reviewsAmount;
        this.startTime = startTime;
        this.latitude = latitude;
        this.longtitude = longtitude;
    }

    public String getSalonId() {
        return salonId;
    }

    public void setSalonId(String salonId) {
        this.salonId = salonId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRatingNumber() {
        return ratingNumber;
    }

    public void setRatingNumber(float ratingNumber) {
        this.ratingNumber = ratingNumber;
    }

    public boolean isFull() {
        return full;
    }

    public void setFull(boolean full) {
        this.full = full;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getReviewsAmount() {
        return reviewsAmount;
    }

    public void setReviewsAmount(int reviewsAmount) {
        this.reviewsAmount = reviewsAmount;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    public class WorkingHour{
        private String day;
        private float startTime;
        private float endTime;

        public WorkingHour() {
        }

        public WorkingHour(String day, float startTime, float endTime) {
            this.day = day;
            this.startTime = startTime;
            this.endTime = endTime;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public float getStartTime() {
            return startTime;
        }

        public void setStartTime(float startTime) {
            this.startTime = startTime;
        }

        public float getEndTime() {
            return endTime;
        }

        public void setEndTime(float endTime) {
            this.endTime = endTime;
        }
    }
}
