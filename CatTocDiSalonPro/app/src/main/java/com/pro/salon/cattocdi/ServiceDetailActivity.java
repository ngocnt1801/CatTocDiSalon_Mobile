package com.pro.salon.cattocdi;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ServiceDetailActivity extends AppCompatActivity {

    private TextView tvPrice, tvPriceUnit, tvDuration, tvDurationUnit, tvSave, tvDelete;
    private EditText etPrice, etDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_detail);

        tvPrice = findViewById(R.id.service_detail_price_tv);
        tvPriceUnit = findViewById(R.id.service_detail_price_unit_tv);
        etPrice = findViewById(R.id.service_detail_price_et);
        tvDuration = findViewById(R.id.service_detail_duration_tv);
        tvDurationUnit = findViewById(R.id.service_detail_duration_unit_tv);
        etDuration = findViewById(R.id.service_detail_duration_et);
        tvSave = findViewById(R.id.service_detail_save_tv);
        tvDelete = findViewById(R.id.service_detail_delete_tv);

        etPrice.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    tvPrice.setTextColor(Color.parseColor("#8d6aa1"));
                    tvPriceUnit.setTextColor(Color.parseColor("#8d6aa1"));
                }else{
                    tvPrice.setTextColor(Color.parseColor("#000000"));
                    tvPriceUnit.setTextColor(Color.parseColor("#808080"));
                }
            }
        });

        etDuration.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    tvDuration.setTextColor(Color.parseColor("#8d6aa1"));
                    tvDurationUnit.setTextColor(Color.parseColor("#8d6aa1"));
                }else{
                    tvDuration.setTextColor(Color.parseColor("#000000"));
                    tvDurationUnit.setTextColor(Color.parseColor("#808080"));
                }
            }
        });

        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceDetailActivity.this, ServiceActivity.class);
                startActivity(intent);
            }
        });

        tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceDetailActivity.this, ServiceActivity.class);
                startActivity(intent);
            }
        });
    }
}
