package com.pro.salon.cattocdi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.github.eunsiljo.timetablelib.data.TimeData;
import com.github.eunsiljo.timetablelib.data.TimeGridData;
import com.github.eunsiljo.timetablelib.data.TimeTableData;
import com.github.eunsiljo.timetablelib.view.TimeTableView;
import com.github.eunsiljo.timetablelib.viewholder.TimeTableItemViewHolder;
import com.pro.salon.cattocdi.fragments.ClientFragment;
import com.pro.salon.cattocdi.fragments.HomeAppointmentFragment;
import com.pro.salon.cattocdi.fragments.HomeFragment;
import com.pro.salon.cattocdi.fragments.ProfileFragment;
import com.pro.salon.cattocdi.fragments.ScheduleFragment;
import com.pro.salon.cattocdi.utils.MyContants;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TimeTableView scheduleTable;
    private BottomNavigationView bottomNav;
    private int currentPos = 0, nextPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        int fragmentId = intent.getIntExtra("fragment_id", 0);
        if(fragmentId != 0){
            goToSpecificFragment(fragmentId);
        }else{

            bottomNav = findViewById(R.id.bottom_nav);

            bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    switch (menuItem.getItemId()) {
                        case R.id.bottom_nav_home_item:
                            currentPos = nextPos;
                            nextPos = 0;
                            HomeFragment homeFragment = new HomeFragment(MainActivity.this);
//                        HomeAppointmentFragment homeAppointmentFragment = new HomeAppointmentFragment();
                            showFragment(homeFragment);
//                        showFragment(homeAppointmentFragment, MyContants.FRAGMENT_BELOW);
                            return true;
                        case R.id.bottom_nav_schedule_item:
                            currentPos = nextPos;
                            nextPos = 1;
                            ScheduleFragment scheduleFragment = new ScheduleFragment();
                            showFragment(scheduleFragment);
                            return true;
                        case R.id.bottom_nav_client_item:
                            currentPos = nextPos;
                            nextPos = 2;
                            ClientFragment clientFragment = new ClientFragment();
                            showFragment(clientFragment);
                            return true;
                        case R.id.bottom_nav_profile_item:
                            currentPos = nextPos;
                            nextPos = 4;
                            ProfileFragment profileFragment = new ProfileFragment(MainActivity.this);
                            showFragment(profileFragment);
                            return true;
                    }
                    return false;
                }
            });
            //HOME FRAGMENT will show first
            showFragment(new HomeFragment(MainActivity.this));
//        showFragment(new HomeAppointmentFragment(),MyContants.FRAGMENT_BELOW);

        }
    }

    private ArrayList<TimeTableData> getSamples(long date) {

        ArrayList<TimeTableData> tables = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            ArrayList<TimeData> values = new ArrayList<>();
            DateTime start = new DateTime(date);
            DateTime end = start.plusMinutes((int) ((Math.random() * 10) + 1) * 30);
            for (int j = 0; j < 7; j++) {
                int textColor = R.color.black;


                TimeData timeData = new TimeData(j, "", R.color.light, textColor, start.getMillis(), end.getMillis());

                values.add(timeData);

                start = end.plusMinutes((int) ((Math.random() * 10) + 1) * 10);
                end = start.plusMinutes((int) ((Math.random() * 10) + 1) * 30);
            }

            tables.add(new TimeTableData("", values));
        }
        return tables;
    }

    private long getMillis(String day) {
        DateTime date = getDateTimePattern().parseDateTime(day);
        return date.getMillis();
    }

    private DateTimeFormatter getDateTimePattern() {
        return DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
    }

    @SuppressLint("ResourceType")
    private void showFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (currentPos < nextPos) {
            transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
        } else if (currentPos > nextPos) {
            transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
        }

        transaction.replace(R.id.activity_main_container_fl, fragment);
        transaction.commit();
    }

    private void goToSpecificFragment(int fragmentId) {
        switch (fragmentId) {
            case R.id.bottom_nav_home_item:
                currentPos = nextPos;
                nextPos = 0;
                HomeFragment homeFragment = new HomeFragment(MainActivity.this);
//                        HomeAppointmentFragment homeAppointmentFragment = new HomeAppointmentFragment();
                showFragment(homeFragment);
//                        showFragment(homeAppointmentFragment, MyContants.FRAGMENT_BELOW);
                return;
            case R.id.bottom_nav_schedule_item:
                currentPos = nextPos;
                nextPos = 1;
                ScheduleFragment scheduleFragment = new ScheduleFragment();
                showFragment(scheduleFragment);
                return;
            case R.id.bottom_nav_client_item:
                currentPos = nextPos;
                nextPos = 2;
                ClientFragment clientFragment = new ClientFragment();
                showFragment(clientFragment);
                return;
            case R.id.bottom_nav_profile_item:
                currentPos = nextPos;
                nextPos = 4;
                ProfileFragment profileFragment = new ProfileFragment(MainActivity.this);
                showFragment(profileFragment);
                return;
            default:
                currentPos = nextPos;
                nextPos = 0;
                HomeFragment homeFragment2 = new HomeFragment(MainActivity.this);
                showFragment(homeFragment2);
                return;
        }
    }
}
