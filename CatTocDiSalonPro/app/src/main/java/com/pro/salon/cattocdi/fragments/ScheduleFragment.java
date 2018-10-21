package com.pro.salon.cattocdi.fragments;


import android.app.DatePickerDialog;
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
import com.pro.salon.cattocdi.R;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Calendar;
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
        TimeTableView scheduleTable = view.findViewById(R.id.salon_schedule);

        scheduleTable.setOnTimeItemClickListener(new TimeTableItemViewHolder.OnTimeItemClickListener() {
            @Override
            public void onTimeItemClick(View view, int i, TimeGridData timeGridData) {
                Log.d("CLICK NE", "" + timeGridData.getTime().getTitle());
            }
        });

        scheduleTable.setStartHour(7);
        scheduleTable.setTableMode(TimeTableView.TableMode.SHORT);

        //assign data test
        ArrayList<TimeTableData> table = null;
        table = getSamples(DateTime.now().withTimeAtStartOfDay().getMillis());
        scheduleTable.setTimeTable(DateTime.now().withTimeAtStartOfDay().getMillis(), table);

        final TextView tvDate = view.findViewById(R.id.fg_schedule_date_tv);
        final DatePickerDialog datePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                tvDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
            }

        }, 2018,10,21);

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
            DateTime end = start.plusMinutes((int)((Math.random() * 10) + 1) * 30);
            for(int j=0; j< 3; j++){
                int textColor = R.color.black;
                TimeData timeData = new TimeData(j, "", R.color.light, textColor, start.getMillis(), end.getMillis());

                values.add(timeData);

                start = end.plusMinutes((int)((Math.random() * 10) + 1) * 10);
                end = start.plusMinutes((int)((Math.random() * 10) + 1) * 30);
            }

            tables.add(new TimeTableData("", values));
        }
        return tables;
    }

}
