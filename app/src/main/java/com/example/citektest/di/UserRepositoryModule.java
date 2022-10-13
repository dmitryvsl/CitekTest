package com.example.citektest.di;

import com.example.citektest.data.UserRepositoryImpl;
import com.example.citektest.data.datasource.local.LocalDataSourceImpl;
import com.example.citektest.data.datasource.local.LocalUserDataSource;
import com.example.citektest.data.datasource.remote.ApiService;
import com.example.citektest.data.datasource.remote.RemoteDataSourceImpl;
import com.example.citektest.data.datasource.remote.RemoteUserDataSource;
import com.example.citektest.domain.mapper.UserDataToUserMapper;
import com.example.citektest.data.model.UserData;
import com.example.citektest.domain.mapper.Mapper;
import com.example.citektest.domain.model.User;
import com.example.citektest.domain.repository.UserRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class UserRepositoryModule {

    @Provides
    @Singleton
    Mapper<UserData, User> provideUserDataToUserMapper(){
        return new UserDataToUserMapper();
    }

    @Provides
    @Singleton
    RemoteUserDataSource provideRemoteUserDataSource(ApiService apiService){
        return new RemoteDataSourceImpl(apiService);
    }

    @Provides
    @Singleton
    LocalUserDataSource provideLocalUserDataSource(ApiService apiService){
        return new LocalDataSourceImpl();
    }

    @Provides
    @Singleton
    UserRepository provideUserRepository(Mapper<UserData, User> mapper, RemoteUserDataSource remoteUserDataSource, LocalUserDataSource localUserDataSource){
        return new UserRepositoryImpl(mapper, remoteUserDataSource, localUserDataSource);
    }


}
