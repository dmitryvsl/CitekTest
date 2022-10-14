package com.example.citektest.domain.use_cases;

import com.example.citektest.domain.repository.UserRepository;

import javax.inject.Inject;

import io.reactivex.Single;

public class AuthUserUseCase {

    @Inject
    public AuthUserUseCase(UserRepository repository) {
        this.repository = repository;
    }

    private final UserRepository repository;

    public Single<Boolean> execute (String imei, String uid, String pass){
        return repository.authUser(imei, uid, pass);
    }
}
