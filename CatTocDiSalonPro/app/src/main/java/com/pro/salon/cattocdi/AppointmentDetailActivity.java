package com.pro.salon.cattocdi;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pro.salon.cattocdi.utils.MyContants;

import static com.pro.salon.cattocdi.R.layout.dialog_cancel_appointment;
import static com.pro.salon.cattocdi.utils.MyContants.CLIENT_PAGE;
import static com.pro.salon.cattocdi.utils.MyContants.HOME_PAGE;
import static com.pro.salon.cattocdi.utils.MyContants.SCHEDULE_PAGE;

public class AppointmentDetailActivity extends AppCompatActivity {

    private TextView tvOK, tvname, tvDate, tvTime;
    private Button btnCancel, btnArrived;
    String cusName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_detail);

        Intent intent = getIntent();
        final int fromPage = intent.getIntExtra("from_page", -1);
        final int expired = intent.getIntExtra("expired", -1);
        final  String date = intent.getStringExtra("date");
        final String startTime = intent.getStringExtra("startTime");
        final String endTime = intent.getStringExtra("endTime");
        cusName = intent.getStringExtra("cusName");

        //Contact
        //final String conName = intent.getStringExtra("name_from_contact");

        //String nameCus = intent.getStringExtra("name_cus");

        tvOK = findViewById(R.id.appointment_detail_save_tv);
        tvname = findViewById(R.id.appointment_item_expand_name_tv);
        tvname.setText(cusName);
        tvDate = findViewById(R.id.appointment_item_expand_date_tv);
        tvTime = findViewById(R.id.appointment_item_expand_time_tv);
        tvDate.setText(date);
        tvTime.setText(startTime + " - " + endTime);
        btnCancel = findViewById(R.id.appointment_detail_cancel_btn);
        btnArrived = findViewById(R.id.appointment_detail_arrived_btn);
        final Dialog dialog = new Dialog(this);
        tvOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToPrevious(fromPage);
            }
        });

        btnArrived.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               backToPrevious(fromPage);
            }
        });
      if(expired == 1){
            btnCancel.setBackground(getDrawable(R.drawable.ripple_circle_outline_error_disable));
            btnCancel.setEnabled(false);
        }
       else{
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button btnOk, btnCancel;
                    dialog.setContentView(R.layout.dialog_cancel_appointment);
                    btnOk = dialog.findViewById(R.id.dialog_ok);
                    btnCancel = dialog.findViewById(R.id.dialog_cancel);
                    btnOk.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            backToPrevious(fromPage);
                        }
                    });
                    btnCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //backToPrevious(fromPage);
                            dialog.dismiss();
                        }
                    });
                    dialog.show();

                }
            });
        }


    }

    private void goToScheduleFragment() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("fragment_id", R.id.bottom_nav_schedule_item);
        startActivity(intent);
    }

    private void goToHomeFragment() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void goToContactDetail() {
        Intent intent = new Intent(this, ContactDetailActivity.class);
        intent.putExtra("contactName",cusName);
        startActivity(intent);
    }

    private void backToPrevious(int fromPage) {
        switch (fromPage) {
            case HOME_PAGE:
                goToHomeFragment();
                break;
            case SCHEDULE_PAGE:
                goToScheduleFragment();
                break;
            case CLIENT_PAGE:
                goToContactDetail();
                break;
            default:
                goToHomeFragment();

        }
    }

}

