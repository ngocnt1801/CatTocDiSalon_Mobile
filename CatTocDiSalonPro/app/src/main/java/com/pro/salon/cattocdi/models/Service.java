package com.pro.salon.cattocdi.models;

public class Service {
    private String id;
    private String name;
    private float price;
    private int avgTime;

    public Service() {
    }

    public Service(String id, String name, float price, int avgTime) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.avgTime = avgTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getAvgTime() {
        return avgTime;
    }

    public void setAvgTime(int avgTime) {
        this.avgTime = avgTime;
    }
}
