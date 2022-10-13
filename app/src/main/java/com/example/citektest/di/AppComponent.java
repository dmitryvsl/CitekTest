package com.example.citektest.di;

import com.example.citektest.presentation.LoginFragment;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class, UserRepositoryModule.class})
@Singleton
public interface AppComponent {

    void inject(LoginFragment fragment);
}
