package com.example.citektest.data;

import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.citektest.data.datasource.local.LocalUserDataSource;
import com.example.citektest.data.datasource.remote.ApiService;
import com.example.citektest.data.datasource.remote.RemoteUserDataSource;
import com.example.citektest.data.model.UserData;
import com.example.citektest.domain.mapper.Mapper;
import com.example.citektest.domain.model.User;
import com.example.citektest.domain.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepositoryImpl implements UserRepository {

    private final Mapper<UserData, User> mapper;
    private final RemoteUserDataSource remoteDataSource;
    private final LocalUserDataSource localDataSource;

    @Inject
    public UserRepositoryImpl(Mapper<UserData, User> mapper, RemoteUserDataSource remoteDataSource, LocalUserDataSource localDataSource) {
        this.mapper = mapper;
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
    }

    @Override
    public Single<List<User>> getUsers(String imei) {
        return remoteDataSource.getData(imei)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map(userData -> {
                    List<User> users = new ArrayList<>();
                    for (UserData user: userData)
                        users.add(user.mapToUser(mapper));

                    return users;
                });
    }
}
