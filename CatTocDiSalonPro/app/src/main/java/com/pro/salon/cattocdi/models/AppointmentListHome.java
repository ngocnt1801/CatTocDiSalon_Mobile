package com.pro.salon.cattocdi.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class AppointmentListHome implements Serializable {

    @SerializedName("ListToday")
    private List<Appointment> nextAppointments;
    @SerializedName("ListNotApprove")
    private List<Appointment> notApproveAppointments;

    public List<Appointment> getNextAppointments() {
        return nextAppointments;
    }

    public void setNextAppointments(List<Appointment> nextAppointments) {
        this.nextAppointments = nextAppointments;
    }

    public List<Appointment> getNotApproveAppointments() {
        return notApproveAppointments;
    }

    public void setNotApproveAppointments(List<Appointment> notApproveAppointments) {
        this.notApproveAppointments = notApproveAppointments;
    }
}
