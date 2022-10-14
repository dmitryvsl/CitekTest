package com.example.citektest.presentation.fragment_login.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.citektest.domain.use_cases.AuthUserUseCase;
import com.example.citektest.domain.use_cases.GetUsersUseCase;
import com.example.citektest.presentation.utils.NetworkUtils;
import com.example.citektest.presentation.utils.UserDataValidator;

import javax.inject.Inject;

public class UserViewModelFactory implements ViewModelProvider.Factory {

    GetUsersUseCase getUsersUseCase;
    AuthUserUseCase authUserUseCase;
    NetworkUtils networkUtils;
    UserDataValidator userDataValidator;

    @Inject
    public UserViewModelFactory(GetUsersUseCase getUsersUseCase, AuthUserUseCase authUserUseCase, NetworkUtils networkUtils, UserDataValidator userDataValidator) {
        this.getUsersUseCase = getUsersUseCase;
        this.authUserUseCase = authUserUseCase;
        this.networkUtils = networkUtils;
        this.userDataValidator = userDataValidator;
    }





    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(UserViewModel.class))
            return (T) new UserViewModel(getUsersUseCase,authUserUseCase, networkUtils, userDataValidator);
        else
            throw new ClassCastException("No such class found: " + modelClass);
    }
}
