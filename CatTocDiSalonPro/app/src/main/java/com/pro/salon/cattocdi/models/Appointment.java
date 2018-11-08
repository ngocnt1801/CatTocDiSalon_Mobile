package com.pro.salon.cattocdi.models;

import com.google.gson.annotations.SerializedName;
import com.pro.salon.cattocdi.models.enums.AppointmentStatus;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class Appointment implements Serializable{
    @SerializedName("AppointmentId")
    private int appointmentId;
    private Salon salon;
    @SerializedName("Status")
    private AppointmentStatus status;
    private Timestamp startTime;
    private Timestamp endTime;
    @SerializedName("Services")
    private List<Service> services;
    private int discount;
    @SerializedName("BookedDate")
    private String bookedDateStr;
    @SerializedName("Duration")
    private int duration;
    @SerializedName("Customer")
    private Customer customer;

    public Appointment() {
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getBookedDateStr() {
        return bookedDateStr;
    }

    public void setBookedDateStr(String bookedDateStr) {
        this.bookedDateStr = bookedDateStr;
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
        String value = bookedDateStr.replace("T", " ");
        try {
            startTime = new Timestamp( new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(value).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        String value = bookedDateStr.replace("T", " ");
        try {
            endTime = new Timestamp( new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(value).getTime() + duration * 60*1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
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

    public String getStartToEnd(){
        return new SimpleDateFormat("hh:mm").format(getStartTime()) + " - " + new SimpleDateFormat("hh:mm").format(getEndTime());
    }

    public String getServicesName(){
        String result = "";
        for (Service service:
             services) {
            result += service.getName();
        }
        return result;
    }

}
