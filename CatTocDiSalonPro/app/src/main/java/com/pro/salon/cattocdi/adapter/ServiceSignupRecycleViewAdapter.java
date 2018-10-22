package com.pro.salon.cattocdi.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class ServiceSignupRecycleViewAdapter extends RecyclerView.Adapter<ServiceSignupRecycleViewAdapter.ServiceSignupViewHolder>{
    private Context context;
    @Override
    public ServiceSignupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ServiceSignupViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ServiceSignupViewHolder extends RecyclerView.ViewHolder {
        public ServiceSignupViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
