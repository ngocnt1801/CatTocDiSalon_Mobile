package com.pro.salon.cattocdi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.pro.salon.cattocdi.adapter.CategoryRecycleViewAdapter;
import com.pro.salon.cattocdi.adapter.ServiceRecycleViewAdapter;
import com.pro.salon.cattocdi.adapter.ServiceSignupRecycleViewAdapter;
import com.pro.salon.cattocdi.utils.MyContants;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ServiceSignupActivity extends AppCompatActivity {
    private RecyclerView rvCategory;
    private RecyclerView rvService;
    private int from_page = -1;
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

        Intent intent = getIntent();
        from_page = intent.getIntExtra("from_page", -1);

        TextView saveTv = findViewById(R.id.activity_service_save_tv);
        saveTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Change to Activity
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
        if(from_page == MyContants.SIGNUP_PAGE){
            Intent intent = new Intent(ServiceSignupActivity.this, WorkingHourSignupActivity.class);
            startActivity(intent);
        }else if(from_page == MyContants.MANAGER_SERVICE_PAGE){
            Intent intent = new Intent(ServiceSignupActivity.this, ServiceActivity.class);
            startActivity(intent);
        }
    }
}
