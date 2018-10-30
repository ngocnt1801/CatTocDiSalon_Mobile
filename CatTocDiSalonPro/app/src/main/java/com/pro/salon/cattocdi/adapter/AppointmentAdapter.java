package com.pro.salon.cattocdi.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
        holder.tvStatus.setText("Khách hàng");
        if(mode == MyContants.APPOINTMENT_SMALL){
            if(position == 0){
                holder.tvStatus.setText("Khách hàng");
                holder.tvStatus.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_calendar_active, 0,0,0);
                holder.tvStatus.setTextColor(Color.parseColor("#8d6aa1"));
            }else{
                holder.tvDate.setText("1/11/2018");
            }
            holder.item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, AppointmentDetailActivity.class);
                    intent.putExtra("from_page", MyContants.HOME_PAGE);
                    context.startActivity(intent);
                }
            });
        }else{
            if(position != 0){
                holder.tvStatus.setText("Đã hoàn thành");
                holder.rl.setBackgroundColor(Color.parseColor("#eeeeee"));
                holder.tvDate.setText("15/8/2018");
            }
            holder.item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, AppointmentDetailActivity.class);
                    intent.putExtra("from_page", MyContants.CLIENT_PAGE);
                    context.startActivity(intent);
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class AppointmentViewHolder extends RecyclerView.ViewHolder {

        public View item;
        public TextView tvStatus, tvDate;
        public RelativeLayout rl;
        public AppointmentViewHolder(View itemView) {
            super(itemView);
            this.item = itemView;
            tvStatus = itemView.findViewById(R.id.fg_appointment_upcomming_tv);
            tvDate = itemView.findViewById(R.id.fg_appointment_date_tv);
            rl = itemView.findViewById(R.id.fg_appointment_rv_item_rl);
        }
    }

}
