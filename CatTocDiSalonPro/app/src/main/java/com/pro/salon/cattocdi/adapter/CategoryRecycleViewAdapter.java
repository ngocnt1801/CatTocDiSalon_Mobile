package com.pro.salon.cattocdi.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.pro.salon.cattocdi.R;

import java.util.ArrayList;

public class CategoryRecycleViewAdapter extends RecyclerView.Adapter<CategoryRecycleViewAdapter.CategoryViewHolder>{
    private Context context;
    private String[] categoryList;
    private RecyclerView serviceRv;
    private TextView previousClick = null;

    public CategoryRecycleViewAdapter(Context context, String[] categoryList, RecyclerView serviceRv) {
        this.context = context;
        this.categoryList = categoryList;
        this.serviceRv = serviceRv;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_category_signup_recycle_view, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CategoryViewHolder holder, final int position) {
        holder.tvCategory.setText(categoryList[position]);
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(previousClick != null){
                    previousClick.setTextColor(Color.parseColor("#808080"));
                }
                holder.tvCategory.setTextColor(Color.parseColor("#8d6aa1"));
                previousClick = holder.tvCategory;
                String[] serviceList = {"Something","Something","Something"};
                String[] hairCutList = {"Cắt tóc nam","Cắt tóc nữ"};
                String[] kidList = {"Cắt tóc","Gội đầu","Bính tóc"};
                String[] colorList = {"Nhuộm tóc nam","Nhuộm tóc nữ","Phủ bóng","Nhuộm Henna (THUỐC NHUỘM DẠNG BỘT)","Móc Light"};
                String[] curlAndStraighList = {"Uốn tóc (Lạnh)","Uốn tóc (Nóng)","Duỗi tóc","Nhuộm 1 điểm trên tóc","Uốn duỗi 1 điểm trên tóc"};
                String[] repairList = {"Phục hồi (Phủ Nano)","Milbon (Phủ Nano)"};
                String[] massageList = {"Message đầu","Message thư giãn (gội, massage mặt, đầu)","Massage đặc biệt (Sản phẩm chuyên dụng)"};
                String[] nailList = {"Làm móng","Sơn móng"};
                String[] otherList = {"Rửa mặt","Tỉa chân mày","Cạo râu","Ráy tai"};
                switch (position) {
                    case 0: // Cắt tóc
                        serviceList = hairCutList;
                        break;
                    case 1:
                        serviceList = kidList;
                        break;
                    case 2:
                        serviceList = colorList;
                        break;
                    case 3:
                        serviceList = curlAndStraighList;
                        break;
                    case 4:
                        serviceList = repairList;
                        break;
                    case 5:
                        serviceList = massageList;
                        break;
                    case 6:
                        serviceList = nailList;
                        break;
                    case 7:
                        serviceList = otherList;
                        break;

                }
                serviceRv.setAdapter(new ServiceSignupRecycleViewAdapter(context, serviceList));
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.length;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        public View item;
        public Button btnEditService;
        public TextView tvCategory;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCategory = itemView.findViewById(R.id.category_signup_title);
            item = itemView;
        }
    }
}
