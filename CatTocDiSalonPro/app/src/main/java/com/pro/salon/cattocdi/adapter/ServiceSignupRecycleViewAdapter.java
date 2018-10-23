package com.pro.salon.cattocdi.adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
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

                        final Dialog dialog = new Dialog(context);
                        dialog.setContentView(R.layout.fragment_service_update_dialog);
                        dialog.setTitle("Thêm dịch vụ");
                        String[] durations = context.getResources().getStringArray(R.array.service_duration_array);
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item,durations);
                        Spinner spinner = dialog.findViewById(R.id.fragment_service_update_durations_spinner);
                        spinner.setAdapter(adapter);
                        dialog.show();

                        // Set on click for save service
                        TextView dialogSaveBtn = dialog.findViewById(R.id.fragment_service_update_save_tv);
                        dialogSaveBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                TextView priceTxt = dialog.findViewById(R.id.fragment_service_update_price_txt);
                                Spinner spinnerDetail = dialog.findViewById(R.id.fragment_service_update_durations_spinner);
                                String price = priceTxt.getText().toString();
                                String duration = spinnerDetail.getSelectedItem().toString();
                                if (!price.isEmpty()) {
                                    // Set service description for service
                                    String detailSrc = "Giá: " + price + " - " + "Thời gian: " + duration;
                                    holder.serviceDetails.setText(detailSrc);
                                    holder.addBtn.setImageResource(R.drawable.ic_add_white);
                                    holder.serviceDetails.setVisibility(View.VISIBLE);
                                    dialog.dismiss();
                                }
                            }
                        });
                        // Set on click for cancel service;
                        TextView dialogCancelBtn = dialog.findViewById(R.id.fragment_service_update_cancel_tv);
                        dialogCancelBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });

                    }else{
                        // Remove
                        holder.serviceDetails.setText("");
                        holder.serviceDetails.setVisibility(View.GONE);
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
        public TextView serviceDetails;
        public ImageView addBtn;

        public ServiceSignupViewHolder(@NonNull View itemView) {
            super(itemView);
            serviceTitle = itemView.findViewById(R.id.service_signup_title);
            addBtn = itemView.findViewById(R.id.service_signup_add);
            serviceDetails = itemView.findViewById(R.id.service_signup_details);
        }
    }
}
