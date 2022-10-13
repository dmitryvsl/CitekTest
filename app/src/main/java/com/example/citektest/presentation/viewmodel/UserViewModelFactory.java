package com.example.citektest.presentation.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.citektest.domain.use_cases.GetUsersUseCase;
import com.example.citektest.presentation.utils.NetworkUtils;

import javax.inject.Inject;

public class UserViewModelFactory implements ViewModelProvider.Factory {

    GetUsersUseCase getUsersUseCase;
    NetworkUtils networkUtils;

    @Inject
    public UserViewModelFactory(GetUsersUseCase getUsersUseCase, NetworkUtils networkUtils) {
        this.getUsersUseCase = getUsersUseCase;
        this.networkUtils = networkUtils;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(UserViewModel.class))
            return (T) new UserViewModel(getUsersUseCase, networkUtils);
        else
            throw new ClassCastException("No such class found: " + modelClass);
    }
}
