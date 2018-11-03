package com.pro.salon.cattocdi.models;

import com.google.gson.annotations.SerializedName;

public class Service {
    @SerializedName("CategoryId")
    private int id;
    @SerializedName("ServiceName")
    private String name;
    private double price;
    private int durantion;


    public Service(int id, String name, double price, int durantion) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.durantion = durantion;
    }

    public Service(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Service() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getdurantion() {
        return durantion;
    }

    public void setdurantion(int durantion) {
        this.durantion = durantion;
    }
}
