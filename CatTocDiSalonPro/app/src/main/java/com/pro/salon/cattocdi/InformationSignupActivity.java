package com.pro.salon.cattocdi;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;
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
    private Button btnGetLocation;
    private static double latitude, longitude;
    Location currentLocation = null;

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

        /*btnGetLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(InformationSignupActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {
                        LocationManager mLocationManager = (LocationManager) InformationSignupActivity.this.getSystemService(LOCATION_SERVICE);
                        List<String> providers = mLocationManager.getProviders(true);

                        for (String provider : providers) {
                            Location l = mLocationManager.getLastKnownLocation(provider);
                            if (l == null) {
                                continue;
                            }
                            if (currentLocation == null || l.getAccuracy() < currentLocation.getAccuracy()) {
                                // Found best last known location: %s", l);
                                currentLocation = l;
                            }
                        }
                    }
                }
            }
        });
*/
        ApiClient.getInstance()
                .create(SalonClient.class)
                .getSalonProfile("Bearer " + MyContants.TOKEN)
                .enqueue(new Callback<Salon>() {
                    @Override
                    public void onResponse(Call<Salon> call, Response<Salon> response) {
                        String salonName = response.body().getName();
                        edtSalonName.setText(salonName);
                        String address = response.body().getAddress();
                        edtAddress.setText(address);
                        String phone = response.body().getPhone();
                        edtPhone.setText(phone);
                        String email = response.body().getEmail();
                        edtmail.setText(email);
                    }

                    @Override
                    public void onFailure(Call<Salon> call, Throwable t) {

                    }
                });

        saveTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ApiClient.getInstance()
                        .create(SalonClient.class)
                        .updateProfile("Bearer " + MyContants.TOKEN, edtSalonName.getText().toString(),
                                edtAddress.getText().toString(), Integer.parseInt(edtCapital.getText().toString()),
                                edtPhone.getText().toString(), edtmail.getText().toString(), 106.6329596, 10.8466881)
                        .enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                Log.d("RESPONSE", response.toString());
                                if (response.code() == 200) {
                                    Intent intent = new Intent(InformationSignupActivity.this, WorkingHourSignupActivity.class);
                                    startActivity(intent);
                                } else {
                                    showDialogLoginFail("Có lỗi xảy ra. Vui lòng kiểm tra kết nối");

                                }
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                                Log.d("FAIL", t.getMessage());
                                showDialogLoginFail("Có lỗi xảy ra. Vui lòng kiểm tra kết nối");
                            }
                        });


            }
        });
    }

    private void showDialogLoginFail(String text) {
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
