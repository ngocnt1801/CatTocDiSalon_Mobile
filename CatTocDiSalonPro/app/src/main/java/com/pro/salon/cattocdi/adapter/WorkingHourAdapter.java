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
import com.pro.salon.cattocdi.models.WorkingHour;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.List;

public class WorkingHourAdapter extends RecyclerView.Adapter<WorkingHourAdapter.WorkingHourViewHolder> {
    private Context context;
    private boolean isCheck = false;
    private List<WorkingHour> workingHourList;

    public WorkingHourAdapter(Context context) {
        this.context = context;
    }

    public WorkingHourAdapter(Context context, List<WorkingHour> list) {
        this.context = context;
        this.workingHourList = list;
    }

    public WorkingHourAdapter(Context context, boolean isCheck) {
        this.context = context;
        this.isCheck = isCheck;
    }


    @Override
    public WorkingHourViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_working_hour_item, parent, false);
        return new WorkingHourViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final WorkingHourViewHolder holder, final int position) {

        if (position == 6) {
            // Chu nhat
            holder.dayOfWeek.setText("Chủ nhật");
        } else {
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
        holder.dayOfWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.dayOfWeek.isChecked()){
                    workingHourList.get(position).setdate(position);
                    workingHourList.get(position).setStartTime(holder.fromHour.getText().toString());
                    workingHourList.get(position).setEndTime(holder.toHour.getText().toString());
                }
            }
        });

      //  holder.dayOfWeek.setChecked(isCheck);





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
