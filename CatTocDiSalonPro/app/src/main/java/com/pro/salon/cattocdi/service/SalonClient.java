package com.pro.salon.cattocdi.service;

import com.pro.salon.cattocdi.models.Account;
import com.pro.salon.cattocdi.models.Category;
import com.pro.salon.cattocdi.models.ResponseMsg;
import com.pro.salon.cattocdi.models.Salon;
import com.pro.salon.cattocdi.models.Service;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface SalonClient {
    @FormUrlEncoded
    @POST("api/Account/Register")
   Call<ResponseMsg> createAccount(@Field("SalonName") String name, @Field("Address") String address, @Field("Username") String username, @Field("password") String password,
                             @Field("Email") String email, @Field("PhoneNumber") String phone, @Field("Role") String role, @Field("grant_type") String grantType);

    @FormUrlEncoded
    @POST("token")
    Call<Account> login(@Field("Username") String username, @Field("password") String password, @Field("grant_type") String grantType);

    @FormUrlEncoded
    @POST("token")
    Call<Account> login(@Field("Username") String username, @Field("password") String password, @Field("grant_type") String grantType,@Header("Authorization") String authHeader);

    @GET("api/Categories")
    Call<List<Category>> getCategoried(@Header("Authorization") String auth);

    @POST("api/Services/Update")
    Call<List<Service>> updateServices(@Header("Authorization") String auth, @Field("ServiceId") int serviceId
            , @Field("Price") double price, @Field("Duration") int duration);
}
