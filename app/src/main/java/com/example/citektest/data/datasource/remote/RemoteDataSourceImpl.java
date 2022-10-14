package com.example.citektest.data.datasource.remote;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.citektest.data.model.UserAuthResponse;
import com.example.citektest.data.model.UserData;
import com.example.citektest.data.model.UserResponse;
import com.example.citektest.domain.exceptions.IncorrectDataException;
import com.example.citektest.domain.exceptions.NullBodyResponseException;
import com.example.citektest.domain.exceptions.RequestErrorException;
import com.example.citektest.domain.exceptions.UnknownException;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteDataSourceImpl implements RemoteUserDataSource {

    private final ApiService apiService;

    @Inject
    public RemoteDataSourceImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    private static final String TAG = "RemoteDataSourceImpl";


    @Override
    public Single<List<UserData>> getData(String imei) {
        return Single.create(emitter -> {
            Call<UserResponse> call = apiService.getUsers(imei);

            call.enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(@NonNull Call<UserResponse> call, @NonNull Response<UserResponse> response) {

                    UserResponse userResponse = response.body();

                    if (userResponse == null) {
                        emitter.onError(new NullBodyResponseException());
                        return;
                    }
                    if (response.code() == 200) {
                        emitter.onSuccess(userResponse.getListUsers().getUsers());
                        return;
                    }
                    emitter.onError(new UnknownException());
                }

                @Override
                public void onFailure(@NonNull Call<UserResponse> call, @NonNull Throwable t) {
                    Log.e(TAG, "onFailure: " + t.getMessage());
                    emitter.onError(new RequestErrorException(t.getMessage()));
                }
            });
        });
    }

    @Override
    public Single<UserAuthResponse> auth(String imei, String uid, String pass) {
        return Single.create(emitter -> {
            Call<UserAuthResponse> call = apiService.auth(imei, uid, pass);

            call.enqueue(new Callback<UserAuthResponse>() {
                @Override
                public void onResponse(@NonNull Call<UserAuthResponse> call, @NonNull Response<UserAuthResponse> response) {
                    Log.d(TAG, "onResponse code: " + response.code() + " body: " + response.body());
                    if (response.code() == 200){
                        emitter.onSuccess(response.body());
                        return;
                    }

                    if (response.code() == 202 ){
                        emitter.onError(new IncorrectDataException());
                        return;
                    }

                    emitter.onError(new UnknownException());

                }

                @Override
                public void onFailure(@NonNull Call<UserAuthResponse> call, @NonNull Throwable t) {
                    Log.e(TAG, "onFailure: " + t.getMessage());
                    emitter.onError(new RequestErrorException(t.getMessage()));
                }
            });
        });
    }
}
