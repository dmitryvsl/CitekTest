package com.example.citektest.domain.use_cases;

import com.example.citektest.domain.model.UserAuth;
import com.example.citektest.domain.repository.UserRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class GetAuthenticationUseCase {

    private final UserRepository repository;

    @Inject
    public GetAuthenticationUseCase(UserRepository repository) {
        this.repository = repository;
    }

    public Single<List<UserAuth>> execute(){
        return repository.getAuthentication();
    }
}
