package com.pro.salon.cattocdi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pro.salon.cattocdi.models.Salon;
import com.pro.salon.cattocdi.service.SalonClient;
import com.pro.salon.cattocdi.service.SalonClient;
import com.pro.salon.cattocdi.utils.MyContants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignupActivity extends AppCompatActivity {
    private EditText etName, etPhone, etEmail, etPassword;
    private Button btnSignup1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        etName = findViewById(R.id.signup_salonname_txt);
        etPhone = findViewById(R.id.signup_phone_txt);
        etEmail = findViewById(R.id.signup_email_txt);
        etPassword = findViewById(R.id.signup_password_txt);
        btnSignup1 = findViewById(R.id.signup_activity_create_first_btn);
        btnSignup1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Salon salon = new Salon(
                        etName.getText().toString(),
                        etPhone.getText().toString(),
                        etEmail.getText().toString(),
                        etPassword.getText().toString()
                );
                sendNetworkRequest(salon);
            }
        });
    }

    public void signupStepOne(View view) {
        Intent intent = new Intent(this, ServiceSignupActivity.class);
        intent.putExtra("from_page", MyContants.SIGNUP_PAGE);
        startActivity(intent);
    }
    private void sendNetworkRequest(Salon salon){
        //create Retrofit
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http:/192.168.1.106:51249/api/account/register/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        //get salon & call for the request
        SalonClient user = retrofit.create(SalonClient.class);
        Call<Salon> call = user.createAccount(salon);
        call.enqueue(new Callback<Salon>() {
            @Override
            public void onResponse(Call<Salon> call, Response<Salon> response) {
                Toast.makeText(SignupActivity.this, "yeadd", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Salon> call, Throwable t) {
                Toast.makeText(SignupActivity.this, "failled", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
