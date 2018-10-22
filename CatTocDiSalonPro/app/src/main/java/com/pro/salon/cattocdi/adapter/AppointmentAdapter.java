package com.pro.salon.cattocdi.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pro.salon.cattocdi.AppointmentDetailActivity;
import com.pro.salon.cattocdi.R;
import com.pro.salon.cattocdi.utils.MyContants;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder> {

    private Context context;
    private int mode;

    public AppointmentAdapter(Context context, int mode) {
        this.context = context;
        this.mode = mode;
    }

    @Override
    public AppointmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (mode) {
            case MyContants.APPOINTMENT_SMALL:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.appointment_cart_view, parent, false);
                break;
            case MyContants.APPOINTMENT_FULL:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_appointment_cart_view, parent, false);
                break;
            default:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_appointment_cart_view, parent, false);
                break;
        }
        return new AppointmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AppointmentViewHolder holder, int position) {
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AppointmentDetailActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class AppointmentViewHolder extends RecyclerView.ViewHolder {

        public View item;

        public AppointmentViewHolder(View itemView) {
            super(itemView);
            this.item = itemView;
        }
    }

}
