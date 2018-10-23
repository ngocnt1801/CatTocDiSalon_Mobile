package com.pro.salon.cattocdi.adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.pro.salon.cattocdi.R;

import java.util.ArrayList;

public class ServiceSignupRecycleViewAdapter extends RecyclerView.Adapter<ServiceSignupRecycleViewAdapter.ServiceSignupViewHolder> {
    private Context context;
    private String[] serviceList;

    public ServiceSignupRecycleViewAdapter(Context context) {
        this.context = context;
    }

    public ServiceSignupRecycleViewAdapter(Context context, String[] serviceList) {
        this.context = context;
        this.serviceList = serviceList;
    }

    @Override
    public ServiceSignupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_service_signup_recycle_view, parent, false);
        return new ServiceSignupRecycleViewAdapter.ServiceSignupViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ServiceSignupViewHolder holder, int position) {
        if (serviceList != null) {
            holder.serviceTitle.setText(serviceList[position]);
            holder.itemView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if(holder.addBtn.getDrawable().getConstantState().equals(context.getDrawable(R.drawable.ic_add).getConstantState())){

                        holder.addBtn.setImageResource(R.drawable.ic_add_white);
                        Dialog dialog = new Dialog(context);
                        dialog.setContentView(R.layout.activity_information);
                        dialog.setTitle("Thêm dịch vụ");
                        dialog.show();
                    }else{
                        holder.addBtn.setImageResource(R.drawable.ic_add);
                    }
                    return false;
                }
            });
        } else {
            holder.serviceTitle.setText("Chọn các danh mục để thêm dịch vụ");
            holder.addBtn.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        if (serviceList == null) return 1;
        return serviceList.length;
    }

    public class ServiceSignupViewHolder extends RecyclerView.ViewHolder {
        public TextView serviceTitle;
        public ImageView addBtn;

        public ServiceSignupViewHolder(@NonNull View itemView) {
            super(itemView);
            serviceTitle = itemView.findViewById(R.id.service_signup_title);
            addBtn = itemView.findViewById(R.id.service_signup_add);
        }
    }
}
