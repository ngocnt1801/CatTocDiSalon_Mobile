package com.pro.salon.cattocdi.adapter;

import android.app.TimePickerDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import com.pro.salon.cattocdi.R;

import org.w3c.dom.Text;

import java.util.Calendar;

public class WorkingHourAdapter extends RecyclerView.Adapter<WorkingHourAdapter.WorkingHourViewHolder>{
    private Context context;

    public WorkingHourAdapter(Context context) {
        this.context = context;
    }

    @Override
    public WorkingHourViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_working_hour_item, parent, false);
        return new WorkingHourViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final WorkingHourViewHolder holder, int position) {

        if (position == 6) {
            // Chu nhat
            holder.dayOfWeek.setText("Chủ nhật");
        }
        else {
            holder.dayOfWeek.setText("Thứ " + (position + 2));
        }
        // set from
        holder.fromHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Current time
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);


                TimePickerDialog timePickerDialog = new TimePickerDialog(context,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                holder.fromHour.setText(hourOfDay + ":" + minute);
                            }
                        }, hour, minute, false);
                timePickerDialog.show();
            }
        });
        // set true
        holder.toHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Current time
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);


                TimePickerDialog timePickerDialog = new TimePickerDialog(context,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                holder.toHour.setText(hourOfDay + ":" + minute);
                            }
                        }, hour, minute, false);
                timePickerDialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return 7;
    }

    public class WorkingHourViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public Switch dayOfWeek;
        public TextView fromHour;
        public TextView toHour;

        public WorkingHourViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
            this.dayOfWeek = itemView.findViewById(R.id.working_hour_item_switch);
            this.fromHour = itemView.findViewById(R.id.working_hour_item_from);
            this.toHour = itemView.findViewById(R.id.working_hour_item_to);
        }
    }
}
