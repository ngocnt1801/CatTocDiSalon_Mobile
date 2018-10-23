package com.pro.salon.cattocdi.fragments;


import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.github.eunsiljo.timetablelib.data.TimeData;
import com.github.eunsiljo.timetablelib.data.TimeGridData;
import com.github.eunsiljo.timetablelib.data.TimeTableData;
import com.github.eunsiljo.timetablelib.view.TimeTableView;
import com.github.eunsiljo.timetablelib.viewholder.TimeTableItemViewHolder;
import com.greasemonk.timetable.TimeTable;
import com.pro.salon.cattocdi.AppointmentDetailActivity;
import com.pro.salon.cattocdi.R;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScheduleFragment extends Fragment {

    int failed = 8;
    public ScheduleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_schedule, container, false);
        final TimeTableView scheduleTable = view.findViewById(R.id.salon_schedule);

        scheduleTable.setOnTimeItemClickListener(new TimeTableItemViewHolder.OnTimeItemClickListener() {
            @Override
            public void onTimeItemClick(View view, int i, TimeGridData timeGridData) {
                Intent intent = new Intent(getActivity(), AppointmentDetailActivity.class);
                intent.putExtra("from_page", "schedule");
                startActivity(intent);
            }
        });

        scheduleTable.setStartHour(0);
        scheduleTable.setShowHeader(true);
        scheduleTable.setTableMode(TimeTableView.TableMode.SHORT);

        //assign data test
        ArrayList<TimeTableData> table = null;
        table = getSamples(DateTime.now().withTimeAtStartOfDay().getMillis());
        scheduleTable.setMinimumWidth(table.size() * 40);
        scheduleTable.setTimeTable(DateTime.now().withTimeAtStartOfDay().getMillis(), table);

        final TextView tvDate = view.findViewById(R.id.fg_schedule_date_tv);
        tvDate.setText(DateTime.now().toString("dd/MM/yyyy"));
        final DatePickerDialog datePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                tvDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                scheduleTable.setTimeTable(newDate.getTimeInMillis(), getSamples(newDate.getTimeInMillis()));
            }

        }, DateTime.now().getYear(),DateTime.now().getMonthOfYear() - 1,DateTime.now().getDayOfMonth());

        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker.show();
            }
        });

        return view;
    }


    private ArrayList<TimeTableData> getSamples(long date){

        ArrayList<TimeTableData> tables = new ArrayList<>();
        for(int i=0; i< 7; i++){
            ArrayList<TimeData> values = new ArrayList<>();
            DateTime start = new DateTime(date);
            DateTime end = start.plusMinutes((int)((Math.random() * 10) + 1) * 60);
            for(int j=0; j< 3; j++){
                int textColor = R.color.black;
                int cellColor = R.color.tableLight;
                if(DateTime.now().withTimeAtStartOfDay().getMillis() > date){
                    cellColor = R.color.tableLightDisable;
                }else if(DateTime.now().withTimeAtStartOfDay().getMillis() + (24*3600*1000) < date){

                    cellColor = R.color.tableLight;
                }else{
                    if(end.getMillis() < DateTime.now().getMillis()){
                        cellColor = R.color.tableLightDisable;
                    }
                }

                TimeData timeData = new TimeData(j, "Ngọc Nguyễn:\nCắt tóc, uốn tóc, nhuộm tóc (xanh)\n"+start.toString("HH:mm") + " - " + end.toString("HH:mm"), cellColor, textColor, start.getMillis(), end.getMillis());

                values.add(timeData);

                start = end.plusMinutes((int)((Math.random() * 10) + 1) * 10);
                end = start.plusMinutes((int)((Math.random() * 10) + 1) * 30);
            }

            tables.add(new TimeTableData("" + (i+1), values));
        }
        return tables;
    }


}
