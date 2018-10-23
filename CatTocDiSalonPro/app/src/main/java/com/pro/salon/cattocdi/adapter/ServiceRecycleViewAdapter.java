package com.pro.salon.cattocdi.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.pro.salon.cattocdi.R;
import com.pro.salon.cattocdi.ServiceActivity;
import com.pro.salon.cattocdi.ServiceDetailActivity;
import com.pro.salon.cattocdi.utils.MyContants;

public class ServiceRecycleViewAdapter extends RecyclerView.Adapter<ServiceRecycleViewAdapter.ServiceViewHolder> {
    private Context context;
    private boolean isPreview = false;

    public ServiceRecycleViewAdapter(Context context) {
        this.context = context;
    }

    public ServiceRecycleViewAdapter(Context context, boolean isPreview) {
        this.context = context;
        this.isPreview = isPreview;
    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_salon_detail_service_recycle_view, viewGroup, false);
        return new ServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder serviceViewHolder, int i) {
        if (!isPreview) {
            serviceViewHolder.btnEditService.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ServiceDetailActivity.class);
                    context.startActivity(intent);
                }
            });
        } else {
            serviceViewHolder.btnEditService.setText("Đặt");
        }
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public class ServiceViewHolder extends RecyclerView.ViewHolder {
        public View item;
        public Button btnEditService;

        public ServiceViewHolder(@NonNull View itemView) {
            super(itemView);
            this.item = itemView;
            this.btnEditService = itemView.findViewById(R.id.edit_service_btn);
        }
    }
}

