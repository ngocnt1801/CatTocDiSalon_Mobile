package com.pro.salon.cattocdi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.pro.salon.cattocdi.adapter.WorkingHourAdapter;

public class WorkingHoursActivity extends AppCompatActivity {

    private TextView tvSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_working_hours);
        RecyclerView rv = findViewById(R.id.activity_working_hours_rv);
        rv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rv.setAdapter(new WorkingHourAdapter(this, true));

        tvSave = findViewById(R.id.activity_working_hours_save_tv);
        tvSave.setOnClickListener(new View.OnClickListener() {
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
