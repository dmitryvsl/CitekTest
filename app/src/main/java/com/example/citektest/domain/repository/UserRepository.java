package com.example.citektest.domain.repository;

import com.example.citektest.domain.model.User;

import java.util.List;

import io.reactivex.Single;

public interface UserRepository extends Repository {

    Single<List<User>> getUsers(String imei);

}
