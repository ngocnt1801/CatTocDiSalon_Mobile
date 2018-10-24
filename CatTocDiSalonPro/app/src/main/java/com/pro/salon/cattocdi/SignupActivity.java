package com.pro.salon.cattocdi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.pro.salon.cattocdi.utils.MyContants;

public class SignupActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    public void signupStepOne(View view) {
        Intent intent = new Intent(this, ServiceSignupActivity.class);
        intent.putExtra("from_page", MyContants.SIGNUP_PAGE);
        startActivity(intent);
    }
}
