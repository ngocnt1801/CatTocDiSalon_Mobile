package com.pro.salon.cattocdi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.pro.salon.cattocdi.adapter.WorkingHourAdapter;

public class WorkingHourSignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_working_hour_signup);
        RecyclerView rv = findViewById(R.id.activity_working_hours_rv);
        rv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rv.setAdapter(new WorkingHourAdapter(this));

        TextView saveTv = findViewById(R.id.activity_working_hours_signup_save_tv);
        saveTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WorkingHourSignupActivity.this, InformationSignupActivity.class);
                startActivity(intent);
            }
        });
    }
}
