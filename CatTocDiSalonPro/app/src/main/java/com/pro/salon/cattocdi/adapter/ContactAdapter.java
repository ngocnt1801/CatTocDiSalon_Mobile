package com.pro.salon.cattocdi.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pro.salon.cattocdi.ContactDetailActivity;
import com.pro.salon.cattocdi.R;
import com.pro.salon.cattocdi.models.Customer;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private Context context;
    private Customer[] customers;

    public ContactAdapter(Context context) {
        this.context = context;
    }

    public ContactAdapter(Context context, Customer[] list) {
        this.context = context;
        this.customers = list;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, final int position) {

        final Customer currentCustomer = customers[position];
        holder.tvName.setText(customers[position].getName());
        String symbol1 = Character.toString(currentCustomer.getName().charAt(0));
        holder.tvSymbol.setText(symbol1);
        /*switch (position) {
            case 0:
                holder.tvSymbol.setText("TN");
                holder.tvName.setText("Thảo Nhi");
                break;
            case 1:
                holder.tvSymbol.setText("TĐ");
                holder.tvName.setText("Tiến Đạt");

                break;
            case 2:
                holder.tvSymbol.setText("TP");
                holder.tvName.setText("Thành Phong");
                break;
            case 3:
                holder.tvSymbol.setText("NN");
                holder.tvName.setText("Ngọc Nguyễn");
                break;
            }*/

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ContactDetailActivity.class);
                intent.putExtra("contactName", currentCustomer.getName());
                intent.putExtra("contactPhone", currentCustomer.getPhone());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return customers.length;
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {

        public View item;
        public TextView tvSymbol, tvName;

        public ContactViewHolder(View itemView) {
            super(itemView);
            this.item = itemView;
            this.tvSymbol = itemView.findViewById(R.id.contact_item_symbol);
            this.tvName = itemView.findViewById(R.id.contact_item_name);
        }
    }
}
