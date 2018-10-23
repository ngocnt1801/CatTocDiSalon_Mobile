package com.pro.salon.cattocdi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AppointmentDetailActivity extends AppCompatActivity {

    private TextView tvOK;
    private Button btnCancel, btnArrived;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_detail);

        Intent intent = getIntent();
        String fromPage = intent.getStringExtra("from_page");
        final boolean isClientPage = fromPage.equals("client");

        tvOK = findViewById(R.id.appointment_detail_save_tv);
        btnCancel = findViewById(R.id.appointment_detail_cancel_btn);
        btnArrived = findViewById(R.id.appointment_detail_arrived_btn);

        tvOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isClientPage){
                    goToContactDetail();
                }else{
                    goToScheduleFragment();
                }
            }
        });

        btnArrived.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isClientPage){
                    goToContactDetail();
                }else{
                    goToScheduleFragment();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isClientPage){
                    goToContactDetail();
                }else{
                    goToScheduleFragment();
                }
            }
        });

    }

    private void goToScheduleFragment(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("fragment_id", R.id.bottom_nav_schedule_item);
        startActivity(intent);
    }

    private void goToContactDetail(){
        Intent intent = new Intent(this, ContactDetailActivity.class);
        startActivity(intent);
    }

}
