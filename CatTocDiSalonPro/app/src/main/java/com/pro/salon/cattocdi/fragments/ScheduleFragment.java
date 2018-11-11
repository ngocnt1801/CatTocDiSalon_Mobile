package com.pro.salon.cattocdi.fragments;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.GridLayout;
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
    private GridLayout scheduleTable;

    public ScheduleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);
        scheduleTable = view.findViewById(R.id.schedule_table);
        scheduleTable.setColumnCount(MyContants.CAPACITY + 1);
        scheduleTable.setRowCount(96 + 1);

//        scheduleTable.setOnTimeItemClickListener(new TimeTableItemViewHolder.OnTimeItemClickListener() {
//            @Override
//            public void onTimeItemClick(View view, int i, TimeGridData timeGridData) {
//                Intent intent = new Intent(getActivity(), AppointmentDetailActivity.class);
//                intent.putExtra("from_page", MyContants.SCHEDULE_PAGE);
//                intent.putExtra("appointment", appointmentList.get(i));
//                intent.putExtra("customer", (Serializable) appointmentList.get(i).getCustomer());
//                startActivity(intent);
//            }
//        });
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

    private void setHeaders(){
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        int now = getSlotIndex(Calendar.getInstance().getTime());

        for (int i = 1; i <= MyContants.CAPACITY; i++) {
            TextView cell = (TextView) inflater.inflate(R.layout.table_cell_slot, null);
            cell.setText(i + "");
            cell.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = 500;
            params.height = 100;
            params.columnSpec = GridLayout.spec(i);
            params.rowSpec = GridLayout.spec(0);
            cell.setLayoutParams(params);
            scheduleTable.addView(cell);
        }

        for (int i = 1; i <= 96; i++) {

            TextView cell = (TextView) inflater.inflate(R.layout.table_cell_slot, null);
            cell.setText(slotToString(i - 1));
            cell.setGravity(View.TEXT_ALIGNMENT_CENTER);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = 200;
            params.height = 200;
            params.columnSpec = GridLayout.spec(0);
            params.rowSpec = GridLayout.spec(i);
            cell.setLayoutParams(params);
            if(i == now + 1){
                cell.setFocusableInTouchMode(true);
                cell.requestFocus();

            }
            scheduleTable.addView(cell);
        }
    }


    private void loadDataToScheduler() {
        setHeaders();
        List<List<Appointment>> appointmentTable = parseToSchedule(appointmentList);
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        for (int i = 0; i < MyContants.CAPACITY; i++) {

            if (appointmentTable.size() <= i) {
                appointmentTable.add(i, new ArrayList<Appointment>());
            }
            for (int j = 0; j < appointmentTable.get(i).size(); j++) {
                final Appointment currentAppointment = appointmentTable.get(i).get(j);
                int slotIndex = currentAppointment.getSlotIndex();
                int totalSlot = currentAppointment.getTotalSlot();
                for (int s = slotIndex; s < totalSlot + slotIndex; s++) {
                    TextView cell = (TextView) inflater.inflate(R.layout.table_cell_slot, null);
                    if (s == slotIndex) {
                        cell.setText(currentAppointment.getCustomer().getFullName() + "\n" + currentAppointment.getServicesName() + "\n" + currentAppointment.getStartToEnd());
                    }
                    cell.setBackgroundColor(Color.parseColor("#C8E6C9"));
                    GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                    params.width = 500;
                    params.height = 200;
                    params.columnSpec = GridLayout.spec(i + 1);
                    params.rowSpec = GridLayout.spec(s + 1);
                    cell.setLayoutParams(params);
                    cell.setClickable(true);
                    scheduleTable.addView(cell);
                    cell.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), AppointmentDetailActivity.class);
                            intent.putExtra("from_page", MyContants.SCHEDULE_PAGE);
                            intent.putExtra("appointment", currentAppointment);
                            intent.putExtra("customer", (Serializable) currentAppointment.getCustomer());
                            startActivity(intent);
                        }
                    });
                }

            }

        }

    }

    private void loadAppointment(final Date date) {
        ApiClient.getInstance().create(SalonClient.class)
                .getAppointmentByDate("Bearer " + MyContants.TOKEN, new SimpleDateFormat("yyyy-MM-dd").format(date))
                .enqueue(new Callback<List<Appointment>>() {
                    @Override
                    public void onResponse(Call<List<Appointment>> call, Response<List<Appointment>> response) {
                        appointmentList = response.body();
                        scheduleTable.removeAllViews();
                        loadDataToScheduler();
                    }

                    @Override
                    public void onFailure(Call<List<Appointment>> call, Throwable t) {

                    }
                });
    }

    private List<List<Appointment>> parseToSchedule(List<Appointment> appointments) {
        List<List<Appointment>> result = new ArrayList<>();
        if (appointments != null) {
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

    private String slotToString(int index) {
        int duration = index * 15;
        String hour = duration / 60 > 9 ? duration / 60 + "" : "0" + duration / 60;
        String minute = duration % 60 > 9 ? duration % 60 + "" : "0" + duration % 60;
        return hour + ":" + minute;
    }

    private int getSlotIndex(Date date){
        int duration = date.getHours() * 60 + date.getMinutes();
        int slotTmp = duration / 15;
        if (duration > slotTmp * 15) {
            slotTmp++;
        }
        return slotTmp;
    }

}
