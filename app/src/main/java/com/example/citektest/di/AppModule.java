package com.example.citektest.di;

import android.content.Context;

import com.example.citektest.App;
import com.example.citektest.data.datasource.remote.ApiService;
import com.example.citektest.data.datasource.remote.okhttpclient.BasicAuthInterceptor;
import com.example.citektest.data.datasource.remote.okhttpclient.UnsafeOkHttpClient;
import com.example.citektest.domain.repository.UserRepository;
import com.example.citektest.domain.use_cases.AuthUserUseCase;
import com.example.citektest.domain.use_cases.GetAuthenticationUseCase;
import com.example.citektest.domain.use_cases.GetUsersUseCase;
import com.example.citektest.presentation.fragment_list.event.ListEventHandlerFactory;
import com.example.citektest.presentation.fragment_list.viewmodel.ListViewModelFactory;
import com.example.citektest.presentation.fragment_login.event.LoginEventHandlerFactory;
import com.example.citektest.presentation.fragment_login.viewmodel.UserViewModelFactory;
import com.example.citektest.presentation.utils.NetworkUtils;
import com.example.citektest.presentation.utils.UserDataValidator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(){
        return App.getContext();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient client){

        return new Retrofit.Builder()
                .baseUrl("https://dev.sitec24.ru/UKA_TRADE/hs/MobileClient/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(){
        return UnsafeOkHttpClient.getUnsafeOkHttpClient();
    }

    @Provides
    @Singleton
    BasicAuthInterceptor provideBasicAuthInterceptor(){
        return new BasicAuthInterceptor();
    }

    @Provides
    @Singleton
    ApiService provideApiService(Retrofit retrofit){
        return  retrofit.create(ApiService.class);
    }


    @Provides
    @Singleton
    GetUsersUseCase provideGetUsersUseCase(UserRepository repository){
        return new GetUsersUseCase(repository);
    }

    @Provides
    @Singleton
    AuthUserUseCase provideAuthUserUseCase(UserRepository repository){
        return new AuthUserUseCase(repository);
    }

    @Provides
    UserViewModelFactory provideFactory(GetUsersUseCase getUsersUseCase, AuthUserUseCase authUserUseCase,NetworkUtils networkUtils, UserDataValidator validator){
        return new UserViewModelFactory(getUsersUseCase,authUserUseCase,networkUtils, validator);
    }

    @Provides
    @Singleton
    LoginEventHandlerFactory provideEventFactory(){
        return new LoginEventHandlerFactory();
    }

    @Provides
    @Singleton
    ListEventHandlerFactory provideEventHandlerFactory(){
        return new ListEventHandlerFactory();
    }

    @Provides
    @Singleton
    ListViewModelFactory provideListViewModelFactory(GetAuthenticationUseCase useCase){
        return new ListViewModelFactory(useCase);
    }

    @Provides
    @Singleton
    NetworkUtils provideNetworkUtils(Context context){
        return new NetworkUtils(context);
    }

    @Provides
    @Singleton
    UserDataValidator provideUserDataValidator(){
        return new UserDataValidator();
    }
}
