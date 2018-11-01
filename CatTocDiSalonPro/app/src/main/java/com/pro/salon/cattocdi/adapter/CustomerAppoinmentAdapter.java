package com.pro.salon.cattocdi.adapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.pro.salon.cattocdi.AppointmentDetailActivity;
import com.pro.salon.cattocdi.R;
import com.pro.salon.cattocdi.models.Customer;
import com.pro.salon.cattocdi.utils.MyContants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CustomerAppoinmentAdapter extends ArrayAdapter<Customer> {
    private Context mContext;
    private ArrayList<Customer> customerList = new ArrayList<>();
    //private Customer[] customers;

    public CustomerAppoinmentAdapter(@NonNull Context context, @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<Customer> list) {
        super(context,0, list);
        mContext = context;
        customerList = list;
    }
    /*public CustomerAppoinmentAdapter(@NonNull Context context, Customer[] list) {
        super(context,0, list);
        mContext = context;
        this.customers = list;
    }*/


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = null;
        if (listItem == null){
                listItem = LayoutInflater.from(mContext).inflate(R.layout.appointment_cart_view, parent, false);
                Customer currentCustomer = customerList.get(position);
                TextView tvName = listItem.findViewById(R.id.fg_appointment_salon_name);
                tvName.setText(currentCustomer.getName());
                TextView tvDate = listItem.findViewById(R.id.fg_appointment_date_tv);
                tvDate.setText(currentCustomer.getDate());
                TextView startTime = listItem.findViewById(R.id.fg_appointment_start_time);
                startTime.setText(currentCustomer.getStartTime());
                TextView endTime = listItem.findViewById(R.id.fg_appointment_end_time);
                endTime.setText(currentCustomer.getEndTime());


        }
        listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, AppointmentDetailActivity.class);
                intent.putExtra("from_page", MyContants.HOME_PAGE);
                intent.putExtra("cusName", customerList.get(position).getName());
                intent.putExtra("date", customerList.get(position).getDate());
                intent.putExtra("startTime", customerList.get(position).getStartTime());
                intent.putExtra("endTime", customerList.get(position).getEndTime());
                mContext.startActivity(intent);
            }
        });
        return listItem;
    }

}
