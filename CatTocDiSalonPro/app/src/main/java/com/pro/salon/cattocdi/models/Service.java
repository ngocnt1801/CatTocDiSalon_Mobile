package com.pro.salon.cattocdi.models;

public class Service {
    private int id;
    private String name;
    private float price;
    private int minutes;

    public Service(int id, String name, float price, int minutes) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.minutes = minutes;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }
}
