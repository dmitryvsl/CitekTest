package com.example.citektest.data.datasource.local;

import com.example.citektest.data.model.UserData;
import com.example.citektest.domain.model.User;

import java.util.List;

import io.reactivex.Single;

public class LocalDataSourceImpl implements LocalUserDataSource{


    @Override
    public Single<List<UserData>> getData() {
        return null;
    }
}
