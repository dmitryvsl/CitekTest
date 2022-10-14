package com.example.citektest.di;

import com.example.citektest.presentation.fragment_list.ListFragment;
import com.example.citektest.presentation.fragment_login.LoginFragment;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class, UserRepositoryModule.class})
@Singleton
public interface AppComponent {

    void inject(LoginFragment fragment);

    void inject(ListFragment fragment);
}
