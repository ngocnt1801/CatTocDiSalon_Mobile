package com.pro.salon.cattocdi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.pro.salon.cattocdi.models.Promotion;

import java.util.List;

public class PromotionDetailActivity extends AppCompatActivity {

    private TextView tvOK, tvStop, tvDes, tvDate, tvDiscount;
    private List<Promotion> promotions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotion_detail);
        tvDes = findViewById(R.id.activity_promotion_detail_des);
        tvDiscount = findViewById(R.id.activity_promotion_detail_discount);
        tvDate = findViewById(R.id.activity_promotion_detail_date);

        Intent intent = getIntent();
        promotions = (List<Promotion>) intent.getSerializableExtra("promotion");
        tvDes.setText(promotions.get(id).getStartToEndstr());


        tvOK = findViewById(R.id.promotion_detail_save_tv);
        tvOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToPromotionActivity();
            }
        });

        tvStop = findViewById(R.id.service_detail_delete_tv);
        tvStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToPromotionActivity();
            }
        });

    }
    private void goToPromotionActivity(){
        Intent intent = new Intent(this, PromotionActivity.class);
        startActivity(intent);
    }
}
