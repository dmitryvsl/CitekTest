package com.example.citektest.domain.use_cases;

import com.example.citektest.domain.model.User;
import com.example.citektest.domain.repository.UserRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class GetUsersUseCase {

    UserRepository repository;

    @Inject
    public GetUsersUseCase(UserRepository repository) {
        this.repository = repository;
    }

    public Single<List<User>> execute(String imei){
        return repository.getUsers(imei);
    }
}
