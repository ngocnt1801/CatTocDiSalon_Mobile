package com.pro.salon.cattocdi.fragments;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.pro.salon.cattocdi.AppointmentDetailActivity;
import com.pro.salon.cattocdi.R;
import com.pro.salon.cattocdi.models.Appointment;
import com.pro.salon.cattocdi.service.ApiClient;
import com.pro.salon.cattocdi.service.SalonClient;
import com.pro.salon.cattocdi.utils.MyContants;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScheduleFragment extends Fragment {

    private List<Appointment> appointmentList;
    private TimeTableView scheduleTable;

    public ScheduleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);
        scheduleTable = view.findViewById(R.id.salon_schedule);

        scheduleTable.setOnTimeItemClickListener(new TimeTableItemViewHolder.OnTimeItemClickListener() {
            @Override
            public void onTimeItemClick(View view, int i, TimeGridData timeGridData) {
                Intent intent = new Intent(getActivity(), AppointmentDetailActivity.class);
                intent.putExtra("from_page", MyContants.SCHEDULE_PAGE);
                intent.putExtra("appointment", appointmentList.get(i));
                intent.putExtra("customer", (Serializable) appointmentList.get(i).getCustomer());
                startActivity(intent);
            }
        });

        scheduleTable.setStartHour(0);
        scheduleTable.setShowHeader(true);
        scheduleTable.setTableMode(TimeTableView.TableMode.SHORT);

        //assign data test
        scheduleTable.setMinimumWidth(MyContants.CAPACITY * 40);
        loadAppointment(Calendar.getInstance().getTime());


        final TextView tvDate = view.findViewById(R.id.fg_schedule_date_tv);
        tvDate.setText(DateTime.now().toString("dd/MM/yyyy"));
        final DatePickerDialog datePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                tvDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                loadAppointment(newDate.getTime());

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


    private ArrayList<TimeTableData> loadDataToScheduler(long date) {
        List<List<Appointment>> appointmentTable = parseToSchedule(appointmentList);

        ArrayList<TimeTableData> tables = new ArrayList<>();
        for (int i = 0; i < MyContants.CAPACITY; i++) {

            if(appointmentTable.size() <= i){
                appointmentTable.add(i, new ArrayList<Appointment>());
            }
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
                    if (appointmentTable.get(i).get(j).getend().getTime() < Calendar.getInstance().getTimeInMillis()) {
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
                        getMillis(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(currentAppointment.getstart())),
                        getMillis(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(currentAppointment.getend())));

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

    private void loadAppointment(final Date date){
        ApiClient.getInstance().create(SalonClient.class)
                .getAppointmentByDate("Bearer " + MyContants.TOKEN, new SimpleDateFormat("yyyy-MM-dd").format(date))
                .enqueue(new Callback<List<Appointment>>() {
                    @Override
                    public void onResponse(Call<List<Appointment>> call, Response<List<Appointment>> response) {
                        appointmentList = response.body();
                        scheduleTable.setTimeTable(date.getTime(), loadDataToScheduler(date.getTime()));
                    }

                    @Override
                    public void onFailure(Call<List<Appointment>> call, Throwable t) {

                    }
                });
    }

    private List<List<Appointment>> parseToSchedule(List<Appointment> appointments) {
        List<List<Appointment>> result = new ArrayList<>();
        if(appointments != null){
            int col;
            for (Appointment currentAppointment :
                    appointments) {
                col = 0;
                while (true) {
                    boolean isExist = false;
                    if (result.size() <= col) {
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
        }
        int col;


        return result;
    }

    private boolean isOveride(Appointment existing, Appointment current) {

        if (current.getstart().getTime() >= existing.getstart().getTime()
                && current.getstart().getTime() <= existing.getend().getTime()) {
            return true;
        }

        if (current.getend().getTime() >= existing.getstart().getTime()
                && current.getend().getTime() <= existing.getend().getTime()) {
            return true;
        }
        return false;

    }

    private long getMillis(String day){
        DateTime date = getDateTimePattern().parseDateTime(day);
        return date.getMillis();
    }
    private DateTimeFormatter getDateTimePattern(){
        return DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
    }



}
