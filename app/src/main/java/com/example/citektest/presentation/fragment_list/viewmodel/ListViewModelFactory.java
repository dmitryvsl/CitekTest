package com.example.citektest.presentation.fragment_list.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.citektest.domain.use_cases.GetAuthenticationUseCase;

import javax.inject.Inject;

public class ListViewModelFactory implements ViewModelProvider.Factory {

    GetAuthenticationUseCase useCase;

    @Inject
    public ListViewModelFactory(GetAuthenticationUseCase useCase) {
        this.useCase = useCase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ListViewModel.class))
            return (T) new ListViewModel(useCase);
        else
            throw new ClassCastException("No such class found: " + modelClass);
    }
}
