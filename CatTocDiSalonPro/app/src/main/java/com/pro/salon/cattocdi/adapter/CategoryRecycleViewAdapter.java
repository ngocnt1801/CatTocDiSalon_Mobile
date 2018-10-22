package com.pro.salon.cattocdi.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.pro.salon.cattocdi.R;

import java.util.ArrayList;

public class CategoryRecycleViewAdapter extends RecyclerView.Adapter<CategoryRecycleViewAdapter.CategoryViewHolder>{
    private Context context;
    private String[] categoryList;

    public CategoryRecycleViewAdapter(Context context, String[] categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_category_signup_recycle_view, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        holder.tvCategory.setText(categoryList[position]);
        holder.tvCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
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
        }

    }
}
