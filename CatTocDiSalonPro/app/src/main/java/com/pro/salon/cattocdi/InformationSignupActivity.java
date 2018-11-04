package com.pro.salon.cattocdi;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.pro.salon.cattocdi.models.Account;
import com.pro.salon.cattocdi.models.Salon;
import com.pro.salon.cattocdi.models.WorkingHour;
import com.pro.salon.cattocdi.service.ApiClient;
import com.pro.salon.cattocdi.service.SalonClient;
import com.pro.salon.cattocdi.utils.MyContants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class InformationSignupActivity extends AppCompatActivity {

    private EditText edtSalonName, edtCapital, edtPhone, edtAddress, edtmail, edtLong, edtLat;
    private Salon salon;
    //private Geocoder geocode = new Geocoder(this);
    String address = "";
    private static double latitude, longitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_information_signup);
        TextView saveTv = findViewById(R.id.activity_info_signup_save_tv);
        edtSalonName = findViewById(R.id.signup_activity_salon_name_edt);
        edtCapital = findViewById(R.id.signup_activity_capacity_edt);
        edtPhone = findViewById(R.id.signup_activity_phone_edt);
        edtAddress = findViewById(R.id.signup_activity_address_edt);
        //geocodeAddress(address.toString(), geocode);
        edtmail = findViewById(R.id.signup_activity_mail_edt);
       /* edtLong = findViewById(R.id.signup_activity_longtitude);
        edtLat = findViewById(R.id.signup_activity_lattitude);*/



        saveTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ApiClient.getInstance()
                        .create(SalonClient.class)
                        .updateProfile("Bearer " + MyContants.TOKEN,edtSalonName.getText().toString(),
                                edtAddress.getText().toString(), Integer.parseInt(edtCapital.getText().toString()),
                                edtPhone.getText().toString(), edtmail.getText().toString(), 10.0000, 15.000)
                        .enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                Log.d("RESPONSE", response.toString());
                                if(response.body().equals("Update Profile Success")){
                                    Intent intent = new Intent(InformationSignupActivity.this, MainActivity.class);
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



            }
        });
    }
    private void showDialogLoginFail(String text){
        final AlertDialog dialog = new AlertDialog.Builder(InformationSignupActivity.this).create();
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



    public void geocodeAddress(String addressStr, Geocoder gc) {
        Address address = null;
        List<Address> addressList = null;
        try {
            if (!TextUtils.isEmpty(addressStr)) {
                addressList = gc.getFromLocationName(addressStr, 5);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (null != addressList && addressList.size() > 0) {
            address = addressList.get(0);
        }
        if (null != address && address.hasLatitude()
                && address.hasLongitude()) {
          latitude = address.getLatitude();
          longitude = address.getLongitude();
        }

    }


}
