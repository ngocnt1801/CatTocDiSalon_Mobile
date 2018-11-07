package com.pro.salon.cattocdi;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.pro.salon.cattocdi.models.Salon;
import com.pro.salon.cattocdi.service.ApiClient;
import com.pro.salon.cattocdi.service.SalonClient;
import com.pro.salon.cattocdi.utils.MyContants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InformationActivity extends AppCompatActivity {
    private EditText edtSalonName, edtCapital, edtPhone, edtAddress, edtmail, edtLong, edtLat;
    private TextView tvOK;
    private Salon salon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        edtSalonName = findViewById(R.id.activity_info_salon_name);
        edtCapital = findViewById(R.id.activity_info_capacity);
        edtPhone = findViewById(R.id.activity_info_phone);
        edtAddress = findViewById(R.id.activity_info_address);
        //geocodeAddress(address.toString(), geocode);
        edtmail = findViewById(R.id.activity_info_mail);
        salon = (Salon) getIntent().getSerializableExtra("salon");

        edtSalonName.setText(salon.getName());

        edtCapital.setText(String.valueOf(salon.getCapital()));

        edtAddress.setText(salon.getAddress());

        edtPhone.setText(salon.getPhone());

        edtmail.setText(salon.getEmail());
       /* ApiClient.getInstance()
                .create(SalonClient.class)
                .getSalonProfile("Bearer " + MyContants.TOKEN)
                .enqueue(new Callback<Salon>() {
                    @Override
                    public void onResponse(Call<Salon> call, Response<Salon> response) {
                        if (response.code() == 200) {
                            String salonName = response.body().getName();
                            edtSalonName.setText(salonName);
                            String address = response.body().getAddress();
                            edtAddress.setText(address);
                            String phone = response.body().getPhone();
                            edtPhone.setText(phone);
                            String email = response.body().getEmail();
                            edtmail.setText(email);
                        }
                        else{
                            showDialogLoginFail("Có lỗi xảy ra. Vui lòng xem lại kết nối mạng");
                        }
                    }

                    @Override
                    public void onFailure(Call<Salon> call, Throwable t) {
                        showDialogLoginFail("Có lỗi xảy ra. Vui lòng xem lại kết nối mạng");
                    }
                });*/

        tvOK = findViewById(R.id.activity_info_save_tv);
        tvOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiClient.getInstance()
                        .create(SalonClient.class)
                        .updateProfile("Bearer " + MyContants.TOKEN, edtSalonName.getText().toString(),
                                edtAddress.getText().toString(), Integer.parseInt(edtCapital.getText().toString()),
                                edtPhone.getText().toString(), edtmail.getText().toString(), 106.635798, 10.856472)
                        .enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                Log.d("RESPONSE", response.toString());
                                if(response.code() == 200){
                                    Intent intent = new Intent(InformationActivity.this, MainActivity.class);
                                    startActivity(intent);
                                }else{
                                    showDialogLoginFail("Có lỗi xảy ra. Vui lòng xem lại kết nối mạng");
                                }
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                                Log.d("FAIL", t.getMessage());
                                showDialogLoginFail("Có lỗi xảy ra. Vui lòng xem lại kết nối mạng");
                            }
                        });
                goToProfileFragment();
            }
        });
    }

    private void goToProfileFragment(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("fragment_id", R.id.bottom_nav_profile_item);
        startActivity(intent);
    }
    private void showDialogLoginFail(String text){
        final AlertDialog dialog = new AlertDialog.Builder(InformationActivity.this).create();
        dialog.setTitle("Có lỗi xảy ra");
        dialog.setMessage(text);
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
