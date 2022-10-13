package com.example.citektest.presentation;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.citektest.domain.use_cases.GetUsersUseCase;

import javax.inject.Inject;

public class UserViewModelFactory implements ViewModelProvider.Factory {

    GetUsersUseCase getUsersUseCase;

    @Inject
    public UserViewModelFactory(GetUsersUseCase getUsersUseCase) {
        this.getUsersUseCase = getUsersUseCase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(UserViewModel.class))
            return (T) new UserViewModel(getUsersUseCase);
        else
            throw new ClassCastException("No such class found: " + modelClass);
    }
}
