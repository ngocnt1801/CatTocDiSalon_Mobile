package com.pro.salon.cattocdi.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pro.salon.cattocdi.R;
import com.pro.salon.cattocdi.adapter.AppointmentAdapter;
import com.pro.salon.cattocdi.models.Appointment;

import java.util.List;

/**

 */
@SuppressLint("ValidFragment")
public class CurrentAppoinmentFragment extends Fragment {
    private RecyclerView rvAppointment;
    private List<Appointment> appointments;

    @SuppressLint("ValidFragment")
    public CurrentAppoinmentFragment(List<Appointment> appointments) {
        // Required empty public constructor
        this.appointments = appointments;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_current_appoinment, container, false);
        rvAppointment = view.findViewById(R.id.fg_home_appointment_rv);
        rvAppointment.setAdapter(new AppointmentAdapter(getActivity(), appointments));
        return view;
    }


}
