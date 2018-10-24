package com.pro.salon.cattocdi.fragments;


import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.DatePicker;
import android.widget.TextView;

import com.github.eunsiljo.timetablelib.data.TimeData;
import com.github.eunsiljo.timetablelib.data.TimeGridData;
import com.github.eunsiljo.timetablelib.data.TimeTableData;
import com.github.eunsiljo.timetablelib.view.TimeTableView;
import com.github.eunsiljo.timetablelib.viewholder.TimeTableItemViewHolder;
import com.google.android.gms.common.util.CrashUtils;
import com.greasemonk.timetable.TimeTable;
import com.pro.salon.cattocdi.AppointmentDetailActivity;
import com.pro.salon.cattocdi.R;
import com.pro.salon.cattocdi.utils.MyContants;

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
    private View currentTimeBar;
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
                intent.putExtra("from_page", MyContants.SCHEDULE_PAGE);
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

        //custom time bar

//        currentTimeBar = view.findViewById(R.id.fg_schedult_time_current_bar);
//        ViewTreeObserver vto = scheduleTable.getViewTreeObserver();
//        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
//                    scheduleTable.getViewTreeObserver().removeGlobalOnLayoutListener(this);
//                } else {
//                    scheduleTable.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                }
//                int width  = scheduleTable.getMeasuredWidth();
//                int height = scheduleTable.getMeasuredHeight();
//                //        currentTimeBar.getLayoutParams().width = get;
//                int tableHeight = height;
//                int unitMinute = (int)tableHeight / (24*60);
//                int totalCurrentMinute = DateTime.now().getMinuteOfDay();
//                if (currentTimeBar.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
//                    ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) currentTimeBar.getLayoutParams();
//                    Log.d("MINUTE", )
//                    p.setMarginStart(totalCurrentMinute * unitMinute + 40);
//                    currentTimeBar.requestLayout();
//                }
//
//            }
//        });
//


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
            values.add(new TimeData(3, "", R.color.currentTimeBar, R.color.lightTextColor, DateTime.now().getMillis(), DateTime.now().plus(5*60*1000).getMillis()));
            tables.add(new TimeTableData("" + (i+1), values));
        }
        return tables;
    }


}
