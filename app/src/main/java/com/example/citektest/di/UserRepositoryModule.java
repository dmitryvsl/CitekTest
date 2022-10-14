package com.example.citektest.di;

import android.content.Context;

import androidx.room.Room;

import com.example.citektest.data.UserRepositoryImpl;
import com.example.citektest.data.datasource.local.LocalDataSourceImpl;
import com.example.citektest.data.datasource.local.LocalUserDataSource;
import com.example.citektest.data.datasource.local.database.CitekTestDatabase;
import com.example.citektest.data.datasource.remote.ApiService;
import com.example.citektest.data.datasource.remote.RemoteDataSourceImpl;
import com.example.citektest.data.datasource.remote.RemoteUserDataSource;
import com.example.citektest.data.model.UserAuthData;
import com.example.citektest.data.model.UserData;
import com.example.citektest.domain.mapper.Mapper;
import com.example.citektest.domain.mapper.UserAuthDataToUserAuthMapper;
import com.example.citektest.domain.mapper.UserDataToUserMapper;
import com.example.citektest.domain.model.User;
import com.example.citektest.domain.model.UserAuth;
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
    Mapper<UserAuthData, UserAuth> provideUserAuthMapper(){
        return new UserAuthDataToUserAuthMapper();
    }

    @Provides
    @Singleton
    RemoteUserDataSource provideRemoteUserDataSource(ApiService apiService){
        return new RemoteDataSourceImpl(apiService);
    }

    @Provides
    @Singleton
    LocalUserDataSource provideLocalUserDataSource(ApiService apiService, CitekTestDatabase database){
        return new LocalDataSourceImpl(database);
    }

    @Provides
    @Singleton
    UserRepository provideUserRepository(Mapper<UserData, User> userDataUserMapper, Mapper<UserAuthData, UserAuth> userAuthMapper, RemoteUserDataSource remoteUserDataSource, LocalUserDataSource localUserDataSource){
        return new UserRepositoryImpl(userDataUserMapper, userAuthMapper, remoteUserDataSource, localUserDataSource);
    }

    @Provides
    @Singleton
    CitekTestDatabase provideCitekTestDatabase(Context context){
        return Room.databaseBuilder(context, CitekTestDatabase.class, "citektest-database").build();
    }

}
