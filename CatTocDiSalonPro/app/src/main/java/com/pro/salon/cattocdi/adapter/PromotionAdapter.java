package com.pro.salon.cattocdi.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.pro.salon.cattocdi.PromotionDetailActivity;
import com.pro.salon.cattocdi.R;

public class PromotionAdapter extends RecyclerView.Adapter<PromotionAdapter.PromotionViewHolder>{

    private Context context;

    public PromotionAdapter(Context context) {
        this.context = context;
    }

    @Override
    public PromotionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.promotion_item, parent, false);
        return new PromotionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PromotionViewHolder holder, int position) {
        //just for test
        if(position > 1){
            holder.icState.setImageResource(R.drawable.ic_stop);
        }

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PromotionDetailActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public class PromotionViewHolder extends RecyclerView.ViewHolder{
        private View item;
        private ImageView icState;
        public PromotionViewHolder(View itemView) {
            super(itemView);
            this.item = itemView;
            icState = itemView.findViewById(R.id.promotion_state_iv);
        }
    }
}
