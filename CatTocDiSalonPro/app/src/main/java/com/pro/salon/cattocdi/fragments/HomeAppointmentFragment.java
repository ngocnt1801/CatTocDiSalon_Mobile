package com.pro.salon.cattocdi.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pro.salon.cattocdi.R;
import com.pro.salon.cattocdi.adapter.AppointmentAdapter;
import com.pro.salon.cattocdi.utils.MyContants;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeAppointmentFragment extends Fragment {

    private RecyclerView rvAppointment;

    public HomeAppointmentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home_appointment, container, false);

        rvAppointment = view.findViewById(R.id.fg_home_appointment_rv);
        rvAppointment.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rvAppointment.setAdapter(new AppointmentAdapter(getActivity(), MyContants.APPOINTMENT_SMALL));

        return view;
    }

}
