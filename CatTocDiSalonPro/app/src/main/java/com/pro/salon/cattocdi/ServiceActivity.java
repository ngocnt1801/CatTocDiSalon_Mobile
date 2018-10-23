package com.pro.salon.cattocdi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pro.salon.cattocdi.adapter.ServiceRecycleViewAdapter;

public class ServiceActivity extends AppCompatActivity {

    private RecyclerView rvService;
    private TextView tvSave;
    private TextView btnAddService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        rvService = findViewById(R.id.activity_service_rv);
        rvService.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvService.setAdapter(new ServiceRecycleViewAdapter(this));

        tvSave = findViewById(R.id.activity_service_save_tv);
        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToProfileFragment();
            }
        });

        btnAddService = findViewById(R.id.activity_service_add_tv);
        btnAddService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceActivity.this, ServiceSignupActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        goToProfileFragment();
    }

    private void goToProfileFragment(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("fragment_id", R.id.bottom_nav_profile_item);
        startActivity(intent);
    }
}
