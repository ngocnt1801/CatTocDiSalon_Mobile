package com.pro.salon.cattocdi.models;

import java.util.List;

public class Appointment {
    private String id;
    private String cusName;
    private List<Service> services;
    private int discount;
    private int status;
    private String cusId;
    private String phone;
    private float startTime;
    private float endTime;

    public Appointment() {
    }

    public Appointment(String id, String cusName, List<Service> services, int discount, int status, String cusId, String phone) {
        this.id = id;
        this.cusName = cusName;
        this.services = services;
        this.discount = discount;
        this.status = status;
        this.cusId = cusId;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
