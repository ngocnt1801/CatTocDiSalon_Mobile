package com.pro.salon.cattocdi.models;

import com.pro.salon.cattocdi.models.enums.AppointmentStatus;

import java.sql.Timestamp;
import java.util.List;

public class Appointment {
    private int appointmentId;
    private Salon salon;
    private AppointmentStatus status;
    private Timestamp startTime;
    private Timestamp endTime;
    private List<Service> services;
    private int discount;

    public Appointment() {
    }

    public Appointment(int appointmentId, Salon salon, AppointmentStatus status, Timestamp startTime, Timestamp endTime, List<Service> services, int discount) {
        this.appointmentId = appointmentId;
        this.salon = salon;
        this.status = status;
        this.startTime = startTime;
        this.endTime = endTime;
        this.services = services;
        this.discount = discount;
    }

    public Appointment(Salon salon, Timestamp startTime, Timestamp endTime, List<Service> services, int discount, AppointmentStatus status) {
        this.salon = salon;
        this.startTime = startTime;
        this.endTime = endTime;
        this.services = services;
        this.discount = discount;
        this.status = status;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
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
}
