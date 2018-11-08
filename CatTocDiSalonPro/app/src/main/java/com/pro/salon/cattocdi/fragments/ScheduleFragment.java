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
import com.pro.salon.cattocdi.models.Appointment;
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

    private int capacity = 9;
    private List<Appointment> appointmentList;

    public ScheduleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);
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
        table = loadData(DateTime.now().withTimeAtStartOfDay().getMillis());
        scheduleTable.setMinimumWidth(table.size() * 40);
        scheduleTable.setTimeTable(DateTime.now().withTimeAtStartOfDay().getMillis(), table);

        final TextView tvDate = view.findViewById(R.id.fg_schedule_date_tv);
        tvDate.setText(DateTime.now().toString("dd/MM/yyyy"));
        final DatePickerDialog datePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                tvDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                scheduleTable.setTimeTable(newDate.getTimeInMillis(), loadData(newDate.getTimeInMillis()));
            }

        }, DateTime.now().getYear(), DateTime.now().getMonthOfYear() - 1, DateTime.now().getDayOfMonth());



        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker.show();
            }
        });

        return view;
    }


    private ArrayList<TimeTableData> loadData(long date) {
        List<List<Appointment>> appointmentTable = parseToSchedule(appointmentList);

        ArrayList<TimeTableData> tables = new ArrayList<>();
        for (int i = 0; i < appointmentTable.size(); i++) {
            ArrayList<TimeData> values = new ArrayList<>();
            for (int j = 0; j < appointmentTable.get(i).size(); j++) {
                Appointment currentAppointment = appointmentTable.get(i).get(j);
                int textColor = R.color.black;
                int cellColor = R.color.tableLight;
                if (DateTime.now().withTimeAtStartOfDay().getMillis() > date) {
                    cellColor = R.color.tableLightDisable;
                } else if (DateTime.now().withTimeAtStartOfDay().getMillis() + (24 * 3600 * 1000) < date) {

                    cellColor = R.color.tableLight;
                } else {
                    if (appointmentTable.get(i).get(j).getEndTime().getTime() < Calendar.getInstance().getTimeInMillis()) {
                        cellColor = R.color.tableLightDisable;
                    }
                }

                TimeData timeData = new TimeData(j,
                        currentAppointment.getCustomer().getFullName()
                                + ":\n"
                                + currentAppointment.getServicesName()
                                + "\n"
                                + currentAppointment.getStartToEnd(),
                        cellColor,
                        textColor,
                        currentAppointment.getStartTime().getTime(),
                        currentAppointment.getEndTime().getTime());

                values.add(timeData);

            }
            values.add(new TimeData(appointmentTable.get(i).size(),
                    "",
                    R.color.currentTimeBar,
                    R.color.lightTextColor,
                    DateTime.now().getMillis(),
                    DateTime.now().plus(5 * 60 * 1000).getMillis()));
            tables.add(new TimeTableData("" + (i + 1), values));
        }
        return tables;
    }

    private List<List<Appointment>> parseToSchedule(List<Appointment> appointments) {
        List<List<Appointment>> result = new ArrayList<>();
        int col;
        for (Appointment currentAppointment :
                appointments) {

            col = 0;
            while (true) {
                boolean isExist = false;
                if (result.get(col) == null) {
                    result.add(new ArrayList<Appointment>());
                }

                for (Appointment existingAppointment :
                        result.get(col)) {
                    if (isOveride(existingAppointment, currentAppointment)) {
                        isExist = true;
                        break;
                    }
                    ;
                }
                if (isExist) {
                    col++;
                } else {
                    result.get(col).add(currentAppointment);
                    break;
                }

            }
        }
        return result;
    }

    private boolean isOveride(Appointment existing, Appointment current) {

        if (current.getStartTime().getTime() >= existing.getStartTime().getTime()
                && current.getStartTime().getTime() <= existing.getEndTime().getTime()) {
            return true;
        }

        if (current.getEndTime().getTime() >= existing.getStartTime().getTime()
                && current.getEndTime().getTime() <= existing.getEndTime().getTime()) {
            return true;
        }
        return false;

    }

}
