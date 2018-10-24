package com.pro.salon.cattocdi;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.pro.salon.cattocdi.utils.MyContants;

public class ServiceDetailActivity extends AppCompatActivity {

    private TextView tvPrice, tvPriceUnit, tvDuration, tvDurationUnit, tvSave, tvDelete;
    private EditText etPrice, etDuration;
    private int from_page;

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

        Intent intent = getIntent();
        from_page = intent.getIntExtra("from_page", -1);

        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToPrevious(from_page);
            }
        });

        tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToPrevious(from_page);
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        backToPrevious(from_page);
    }

    private void backToPrevious(int from_page){
        if(from_page == MyContants.PROFILE_PAGE){
            goToProfileFragment();
        }else if(from_page == MyContants.MANAGER_SERVICE_PAGE){
            Intent intent = new Intent(ServiceDetailActivity.this, ServiceActivity.class);
            startActivity(intent);
        }
    }

    private void goToProfileFragment(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("fragment_id", R.id.bottom_nav_profile_item);
        startActivity(intent);
    }
}
