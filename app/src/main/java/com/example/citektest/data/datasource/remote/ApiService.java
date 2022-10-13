package com.example.citektest.data.datasource.remote;

import com.example.citektest.data.model.UserData;
import com.example.citektest.data.model.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("{imei}/form/users")
    Call<UserResponse> getUsers(@Path(value="imei") String imei);

}
