package com.pro.salon.cattocdi.models;

import java.util.List;

public class Customer {
    private String id;
    private String name;
    private String phone;
    private String emai;
    private List<Appointment> appointments;

    public Customer() {

    }

    public Customer(String id, String name, String phone, String emai) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.emai = emai;
    }
    public Customer(String id, String name, String phone, String emai, List<Appointment> appointments) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.emai = emai;
        this.appointments = appointments;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmai() {
        return emai;
    }

    public void setEmai(String emai) {
        this.emai = emai;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }
}
