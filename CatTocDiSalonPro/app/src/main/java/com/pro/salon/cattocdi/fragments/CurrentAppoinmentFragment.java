package com.pro.salon.cattocdi.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.pro.salon.cattocdi.R;
import com.pro.salon.cattocdi.adapter.CustomerAppoinmentAdapter;

import java.util.ArrayList;

/**

 */
public class CurrentAppoinmentFragment extends Fragment {
    private CustomerAppoinmentAdapter mAdapter;
    private ListView listView;

    public CurrentAppoinmentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_current_appoinment, container, false);
        listView = (ListView) view.findViewById(R.id.fg_home_appointment_rv);
        final ArrayList<Customer> customers = new ArrayList<>();
        customers.add(new Customer("Ngọc Nguyễn", "31/10/2018", "3:00PM", "4:00PM"));
        customers.add(new Customer("Nhi Nguyễn", "31/10/2018", "3:00PM", "4:30PM"));
        customers.add(new Customer("Phong Nguyễn", "31/10/2018", "4:00PM", "5:00PM"));
        customers.add(new Customer("Đạt Trần", "31/10/2018", "2:00PM", "4:00PM"));
        mAdapter = new CustomerAppoinmentAdapter(getActivity(),customers);
        listView.setAdapter(mAdapter);
        return view;
    }


}
