package com.pro.salon.cattocdi.models;

import com.google.android.gms.maps.model.LatLng;

import java.sql.Timestamp;
import java.util.List;

public class Salon {
    private int salonId;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String password;
    private float ratingNumber;
    private boolean full;
    private int discount;
    private String imageUrl;
    private int reviewsAmount;
    private Timestamp startTime;
    private double latitude;
    private double longtitude;
    private List<Category> categories;
    private List<DayWorkingHour> workingHours;


    public Salon() {
    }

    public Salon(String name,String phone, String email, String password) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public Salon(String name,
                 float ratingNumber,
                 boolean full,
                 int discount,
                 List<Category> categories,
                 double latitude,
                 double longtitude,
                 String address,
                 int reviewsAmount,
                 String phone,
                 String email) {
        this.name = name;
        this.ratingNumber = ratingNumber;
        this.full = full;
        this.discount = discount;
        this.categories = categories;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.address = address;
        this.reviewsAmount = reviewsAmount;
        this.phone = phone;
        this.email = email;
    }

    public void setSalonId(int salonId) {
        this.salonId = salonId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSalonId() {
        return salonId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<DayWorkingHour> getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(List<DayWorkingHour> workingHours) {
        this.workingHours = workingHours;
    }

    public LatLng getLatLng() {
        return new LatLng(latitude, longtitude);
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public class DayWorkingHour {
        private int dayInWeek;
        private float startHour;
        private float endHour;
        private boolean open;

        public DayWorkingHour(int dayInWeek, float startHour, float endHour, boolean open) {
            this.dayInWeek = dayInWeek;
            this.startHour = startHour;
            this.endHour = endHour;
            this.open = open;
        }
    }
}
