package com.example.citektest.data.datasource.remote;

import com.example.citektest.data.model.UserAuthResponse;
import com.example.citektest.data.model.UserResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("{imei}/form/users")
    Call<UserResponse> getUsers(@Path(value="imei") String imei);

    @GET("{imei}/authentication?nfc&copyFromDevice=false")
    Call<UserAuthResponse> auth(@Path(value = "imei") String imei, @Query("uid") String uid, @Query("pass") String pass);

}
