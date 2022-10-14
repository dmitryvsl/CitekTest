package com.example.citektest.domain.repository;

import com.example.citektest.domain.model.User;
import com.example.citektest.domain.model.UserAuth;

import java.util.List;

import io.reactivex.Single;

public interface UserRepository extends Repository {

    Single<List<User>> getUsers(String imei);

    Single<Boolean> authUser(String imei, String uid, String pass);

    Single<List<UserAuth>> getAuthentication();

}
