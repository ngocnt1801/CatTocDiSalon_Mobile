package com.pro.salon.cattocdi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.pro.salon.cattocdi.adapter.CategoryRecycleViewAdapter;
import com.pro.salon.cattocdi.adapter.ServiceRecycleViewAdapter;

public class ServiceSignupActivity extends AppCompatActivity {
    private RecyclerView rvService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_signup);
        rvService = findViewById(R.id.activity_service_signup_rv);
        rvService.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvService.setAdapter(new CategoryRecycleViewAdapter(this));
    }
}
