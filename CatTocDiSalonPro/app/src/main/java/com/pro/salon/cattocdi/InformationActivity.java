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

import com.pro.salon.cattocdi.service.ApiClient;
import com.pro.salon.cattocdi.service.SalonClient;
import com.pro.salon.cattocdi.utils.MyContants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InformationActivity extends AppCompatActivity {
    private EditText edtSalonName, edtCapital, edtPhone, edtAddress, edtmail, edtLong, edtLat;
    private TextView tvOK;

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
        tvOK = findViewById(R.id.activity_info_save_tv);
        tvOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiClient.getInstance()
                        .create(SalonClient.class)
                        .updateProfile("Bearer " + MyContants.TOKEN,edtSalonName.getText().toString(),
                                edtAddress.getText().toString(), Integer.parseInt(edtCapital.getText().toString()),
                                edtPhone.getText().toString(), edtmail.getText().toString(), 10.854637, 106.631690)
                        .enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                Log.d("RESPONSE", response.toString());
                                if(response.body().equals("Update Profile Success")){
                                    Intent intent = new Intent(InformationActivity.this, MainActivity.class);
                                    startActivity(intent);
                                }else{
                                    showDialogLoginFail("Failed ne");
                                }
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                                Log.d("FAIL", t.getMessage());
                                showDialogLoginFail("Failed");
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
        dialog.setTitle("Không chính xác");
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
