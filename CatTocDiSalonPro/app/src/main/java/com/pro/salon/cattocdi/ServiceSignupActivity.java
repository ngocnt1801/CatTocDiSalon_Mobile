package com.pro.salon.cattocdi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.pro.salon.cattocdi.adapter.CategoryRecycleViewAdapter;
import com.pro.salon.cattocdi.adapter.ServiceRecycleViewAdapter;
import com.pro.salon.cattocdi.adapter.ServiceSignupRecycleViewAdapter;

import java.util.ArrayList;

public class ServiceSignupActivity extends AppCompatActivity {
    private RecyclerView rvCategory;
    private RecyclerView rvService;
    String[] categoryList = { "Cắt tóc","Trẻ em","Nhuộm màu","Uốn và Duỗi","Phục hồi tóc","Massage","Nail","Dịch vụ khác"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_signup);

        rvService = findViewById(R.id.activity_service_signup_rv);
        rvService.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        rvService.setAdapter(new ServiceSignupRecycleViewAdapter(this));

        rvCategory = findViewById(R.id.activity_category_signup_rv);
        rvCategory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvCategory.setAdapter(new CategoryRecycleViewAdapter(this,categoryList,rvService));

    }
}
