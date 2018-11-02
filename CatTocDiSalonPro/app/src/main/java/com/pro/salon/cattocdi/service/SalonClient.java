package com.pro.salon.cattocdi.service;

import com.pro.salon.cattocdi.models.Salon;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SalonClient {
    @POST("Salon")
   Call<Salon> createAccount(@Body Salon salon);
}
