package com.pro.salon.cattocdi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.pro.salon.cattocdi.adapter.AppointmentAdapter;
import com.pro.salon.cattocdi.adapter.ContactHistoryAdapter;
import com.pro.salon.cattocdi.models.Appointment;
import com.pro.salon.cattocdi.utils.MyContants;

public class ContactDetailActivity extends AppCompatActivity {

    private RecyclerView rvAppointment;
    private TextView tvOK, tvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);
        final Intent intent = getIntent();
        tvName = findViewById(R.id.contact_name_customer);
        final String contactName = intent.getStringExtra("contactName");
        tvName.setText(contactName);

        //This is for demo
        //Intent intentSend = new Intent(this, AppointmentDetailActivity.class);
       // intentSend.putExtra("cusName", contactName);

        rvAppointment = findViewById(R.id.contact_detail_appointment_rv);
        rvAppointment.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvAppointment.setAdapter(new ContactHistoryAdapter(this, MyContants.APPOINTMENT_FULL, contactName));

        tvOK = findViewById(R.id.contact_detail_save_tv);

        tvOK.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                goToClientFragment();
            }
        });
        //startActivity(intentSend);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        goToClientFragment();
    }

    private void goToClientFragment(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("fragment_id", R.id.bottom_nav_client_item);
        startActivity(intent);
    }
}
