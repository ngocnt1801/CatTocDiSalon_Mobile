package com.pro.salon.cattocdi.adapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.pro.salon.cattocdi.R;
import com.pro.salon.cattocdi.fragments.HomeAppointmentFragment;
import com.pro.salon.cattocdi.utils.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerAppoinmentAdapter extends ArrayAdapter<Customer> {
    private Context mContext;
    private List<Customer> customerList = new ArrayList<>();

    public CustomerAppoinmentAdapter(@NonNull Context context, @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<Customer> list) {
        super(context,0, list);
        mContext = context;
        customerList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null){
            listItem = LayoutInflater.from(mContext).inflate(R.layout.appointment_cart_view, parent, false);
            Customer currentCustomer = customerList.get(position);
            TextView name = listItem.findViewById(R.id.fg_appointment_salon_name);
            name.setText(currentCustomer.getName());
            TextView date = listItem.findViewById(R.id.fg_appointment_date_tv);
            date.setText(currentCustomer.getDate());
            TextView startTime = listItem.findViewById(R.id.fg_appointment_start_time);
            startTime.setText(currentCustomer.getStartTime());
            TextView endTime = listItem.findViewById(R.id.fg_appointment_end_time);
            endTime.setText(currentCustomer.getEndTime());


        }
        return listItem;
    }

}
