package com.pro.salon.cattocdi.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pro.salon.cattocdi.AppointmentDetailActivity;
import com.pro.salon.cattocdi.R;
import com.pro.salon.cattocdi.adapter.CustomerAppoinmentAdapter;
import com.pro.salon.cattocdi.models.Customer;
import com.pro.salon.cattocdi.utils.MyContants;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeAppointmentFragment extends Fragment {

    private RecyclerView rvAppointment;
    private CustomerAppoinmentAdapter mAdapter;
    private ListView listView;

    public HomeAppointmentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home_appointment, container, false);
        listView = (ListView) view.findViewById(R.id.fg_home_appointment_rv);
       final ArrayList<Customer> customers = new ArrayList<>();
        customers.add(new Customer("Ngọc Nguyễn", "31/10/2018", "3:00PM", "4:00PM"));
        customers.add(new Customer("Nhi Nguyễn", "1/11/2018", "3:00PM", "4:00PM"));
        customers.add(new Customer("Phong Nguyễn", "1/11/2018", "4:00PM", "5:00PM"));
        customers.add(new Customer("Đạt Trần", "2/11/2018", "3:00PM", "4:00PM"));

        mAdapter = new CustomerAppoinmentAdapter(getActivity(),customers);
        listView.setAdapter(mAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), AppointmentDetailActivity.class);
                intent.putExtra("from_page", MyContants.HOME_PAGE);
               //intent.putExtra("cusName", MyContants.CUSTOMERS);
                if (listView.getPositionForView(view) == 0){
                    intent.putExtra("name_cus", "Ngọc Nguyễn");
                }
                if (listView.getPositionForView(view) == 1){
                    intent.putExtra("name_cus", "Nhi Nguyễn");
                }
                if (listView.getPositionForView(view) == 2){
                    intent.putExtra("name_cus", "Phong Nguyễn");
                }
                if (listView.getPositionForView(view) == 3){
                    intent.putExtra("name_cus", "Đạt Trần");
                }

                startActivity(intent);
            }
        });
       // rvAppointment = view.findViewById(R.id.fg_home_appointment_rv);
       // rvAppointment.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
       // rvAppointment.setAdapter(new AppointmentAdapter(getActivity(), MyContants.APPOINTMENT_SMALL));

        return view;
    }

}
