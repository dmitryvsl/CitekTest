package com.example.citektest.data;

import com.example.citektest.data.datasource.local.LocalUserDataSource;
import com.example.citektest.data.datasource.remote.RemoteUserDataSource;
import com.example.citektest.data.model.UserAuthData;
import com.example.citektest.data.model.UserData;
import com.example.citektest.domain.mapper.Mapper;
import com.example.citektest.domain.model.User;
import com.example.citektest.domain.model.UserAuth;
import com.example.citektest.domain.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class UserRepositoryImpl implements UserRepository {

    private final Mapper<UserData, User> userDataUserMapper;
    private final Mapper<UserAuthData, UserAuth> userAuthMapper;
    private final RemoteUserDataSource remoteDataSource;
    private final LocalUserDataSource localDataSource;

    @Inject
    public UserRepositoryImpl(Mapper<UserData, User> userDataUserMapper, Mapper<UserAuthData, UserAuth> userAuthMapper, RemoteUserDataSource remoteDataSource, LocalUserDataSource localDataSource) {
        this.userDataUserMapper = userDataUserMapper;
        this.userAuthMapper = userAuthMapper;
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
                    for (UserData user : userData)
                        users.add(user.mapToUser(userDataUserMapper));

                    return users;
                });
    }


    @Override
    public Single<Boolean> authUser(String imei, String uid, String pass) {
        return remoteDataSource.auth(imei, uid, pass)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .doOnSuccess(userAuthResponse -> localDataSource.saveData(userAuthResponse.getUserAuthData()))
                .map(userAuthResponse -> userAuthResponse.getUserAuthData() != null);
    }

    @Override
    public Single<List<UserAuth>> getAuthentication() {
        return localDataSource.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map(userAuthResponses -> {
                    List<UserAuth> userAuths = new ArrayList<>();
                    for (UserAuthData userAuthResponse : userAuthResponses)
                        userAuths.add(userAuthResponse.mapToUserAuth(userAuthMapper));
                    return userAuths;
                });
    }

}
