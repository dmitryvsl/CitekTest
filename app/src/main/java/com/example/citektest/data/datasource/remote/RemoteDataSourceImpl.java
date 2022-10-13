package com.example.citektest.data.datasource.remote;

import com.example.citektest.domain.model.User;
import com.example.citektest.domain.repository.Repository;
import com.example.citektest.domain.repository.UserRepository;

import java.util.List;

import io.reactivex.Single;

public class RemoteDataSource implements UserRepository {
    @Override
    public Single<List<User>> getData(String imei) {
        return null;
    }
}
