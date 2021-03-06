package com.pro.salon.cattocdi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pro.salon.cattocdi.utils.MyContants;

import static com.pro.salon.cattocdi.utils.MyContants.CLIENT_PAGE;
import static com.pro.salon.cattocdi.utils.MyContants.HOME_PAGE;
import static com.pro.salon.cattocdi.utils.MyContants.SCHEDULE_PAGE;

public class AppointmentDetailActivity extends AppCompatActivity {

    private TextView tvOK;
    private Button btnCancel, btnArrived;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_detail);

        Intent intent = getIntent();
        final int fromPage = intent.getIntExtra("from_page", -1);

        tvOK = findViewById(R.id.appointment_detail_save_tv);
        btnCancel = findViewById(R.id.appointment_detail_cancel_btn);
        btnArrived = findViewById(R.id.appointment_detail_arrived_btn);

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

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToPrevious(fromPage);
            }
        });

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
