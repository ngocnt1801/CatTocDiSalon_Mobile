package com.pro.salon.cattocdi.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.pro.salon.cattocdi.R;
import com.pro.salon.cattocdi.ServiceActivity;
import com.pro.salon.cattocdi.ServiceDetailActivity;
import com.pro.salon.cattocdi.models.Category;
import com.pro.salon.cattocdi.models.Service;
import com.pro.salon.cattocdi.utils.MyContants;

import java.util.List;

public class ServiceRecycleViewAdapter extends RecyclerView.Adapter<ServiceRecycleViewAdapter.ServiceViewHolder> {
    private Context context;
    private int fromPage = -1;
    //private List<Service> serviceList;
    private List<Category> categoryList;

    public ServiceRecycleViewAdapter(Context context, int fromPage) {
        this.context = context;
        this.fromPage = fromPage;

    }
   /* public ServiceRecycleViewAdapter(Context context, int fromPage, List<Category> services) {
        this.context = context;
        this.fromPage = fromPage;
        this.serviceList = services;
    }*/
    public ServiceRecycleViewAdapter(Context context, int fromPage, List<Category> categories) {
        this.context = context;
        this.fromPage = fromPage;
        this.categoryList = categories;
    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_salon_detail_service_recycle_view, viewGroup, false);
        return new ServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder serviceViewHolder, int i) {

        serviceViewHolder.tvServiceTitle.setText(categoryList.get(i).getServices().get(i).getName());
        serviceViewHolder.tvPriceTime.setText(Double.toString(categoryList.get(i).getServices().get(i).getPrice()) + " " +
                Integer.toString(categoryList.get(i).getServices().get(i).getDurantion()));
       /* switch (i){
            case 0:
                serviceViewHolder.tvServiceTitle.setText("Cắt tóc");
                serviceViewHolder.tvPriceTime.setText("30,000 vnd trong 15 phút");
                break;
            case 1:
                serviceViewHolder.tvServiceTitle.setText("Uốn tóc");
                serviceViewHolder.tvPriceTime.setText("200,000 vnd trong 2 tiếng");
                break;
            case 2:
                serviceViewHolder.tvServiceTitle.setText("Duỗi tóc");
                serviceViewHolder.tvPriceTime.setText("200,000 vnd trong 1 tiếng 30 phút");
                break;
            case 3:
                serviceViewHolder.tvServiceTitle.setText("Nhuộm tóc");
                serviceViewHolder.tvPriceTime.setText("450,000 vnd trong 3 tiếng");
                break;
            case 4:
                serviceViewHolder.tvServiceTitle.setText("Highlight");
                serviceViewHolder.tvPriceTime.setText("400,000 vnd trong 2 tiếng");
                break;
        }*/

        if (fromPage == MyContants.PROFILE_PAGE) {
            serviceViewHolder.btnEditService.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ServiceDetailActivity.class);
                    intent.putExtra("from_page", MyContants.PROFILE_PAGE);
                    context.startActivity(intent);
                }
            });
        } else if (fromPage == MyContants.PREVIEW_PAGE){
            serviceViewHolder.btnEditService.setText("Đặt");
        }else if(fromPage == MyContants.MANAGER_SERVICE_PAGE){
            serviceViewHolder.btnEditService.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ServiceDetailActivity.class);
                    intent.putExtra("from_page", MyContants.MANAGER_SERVICE_PAGE);
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (categoryList == null) return 0;
        return categoryList.size();
    }

    public class ServiceViewHolder extends RecyclerView.ViewHolder {
        public View item;
        public Button btnEditService;
        public TextView tvServiceTitle, tvPriceTime;

        public ServiceViewHolder(@NonNull View itemView) {
            super(itemView);
            this.item = itemView;
            this.btnEditService = itemView.findViewById(R.id.edit_service_btn);
            this.tvPriceTime = itemView.findViewById(R.id.service_price_time);
            this.tvServiceTitle = itemView.findViewById(R.id.service_title);
        }
    }
}

