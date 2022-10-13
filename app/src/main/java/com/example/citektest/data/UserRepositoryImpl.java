package com.example.citektest.data;

import androidx.annotation.NonNull;

import com.example.citektest.data.datasource.remote.ApiService;
import com.example.citektest.data.model.UserData;
import com.example.citektest.domain.mapper.Mapper;
import com.example.citektest.domain.model.User;
import com.example.citektest.domain.repository.Repository;
import com.example.citektest.domain.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoryImpl implements UserRepository {

    private final ApiService apiService;
    private final Mapper<UserData, User> mapper;

    @Inject
    public RepositoryImpl(ApiService apiService, Mapper<UserData, User> mapper) {
        this.apiService = apiService;
        this.mapper = mapper;
    }

    @Override
    public Single<List<User>> getData(String imei) {
        return Single.create(emitter -> {
            Call<List<UserData>> call = apiService.getUsers(imei);

            call.enqueue(new Callback<List<UserData>>() {
                @Override
                public void onResponse(@NonNull Call<List<UserData>> call, @NonNull Response<List<UserData>> response) {
                    Throwable t = new Throwable("Some error was occurred");
                    List<User> users = new ArrayList<>();

                    if (response.body() == null) {
                        emitter.onError(t);
                        return;
                    }

                    for (UserData userData : response.body()) {
                        users.add(userData.mapToUser(mapper));
                    }

                    if (response.code() == 200)
                        emitter.onSuccess(users);
                    else
                        emitter.onError(t);
                }

                @Override
                public void onFailure(@NonNull Call<List<UserData>> call, @NonNull Throwable t) {
                    emitter.onError(t);
                }
            });
        });
    }

}
