package com.pro.salon.cattocdi;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pro.salon.cattocdi.adapter.AppointmentAdapter;
import com.pro.salon.cattocdi.adapter.ContactHistoryAdapter;
import com.pro.salon.cattocdi.models.Appointment;
import com.pro.salon.cattocdi.utils.MyContants;

public class ContactDetailActivity extends AppCompatActivity {

    private RecyclerView rvAppointment;
    private TextView tvOK, tvName;
    private ImageView ivCall, ivSms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);
        final Intent intent = getIntent();
        tvName = findViewById(R.id.contact_name_customer);
        final String contactName = intent.getStringExtra("contactName");
        tvName.setText(contactName);

        ivCall = findViewById(R.id.contact_call);
        ivSms = findViewById(R.id.contact_SMS);

        ivCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:0967776167"));
                startActivity(callIntent);
            }
        });
        ivSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("smsto:"+ "0967776167");
                Intent it = new Intent(Intent.ACTION_SENDTO, uri);
                it.putExtra("sms_body", "Style Việt xin chào bạn!");
                startActivity(it);
            }
        });

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
