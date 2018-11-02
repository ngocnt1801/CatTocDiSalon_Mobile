package com.pro.salon.cattocdi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InformationActivity extends AppCompatActivity {

    private TextView tvOK;
    //private Button btnSMS, btnCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        tvOK = findViewById(R.id.activity_info_save_tv);
       /* btnSMS = findViewById(R.id.btn_SMS);
        btnCall = findViewById(R.id.btn_Call);
        btnSMS.setVisibility(View.INVISIBLE);
        btnCall.setVisibility(View.INVISIBLE);*/
        tvOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToProfileFragment();
            }
        });
    }

    private void goToProfileFragment(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("fragment_id", R.id.bottom_nav_profile_item);
        startActivity(intent);
    }
}
