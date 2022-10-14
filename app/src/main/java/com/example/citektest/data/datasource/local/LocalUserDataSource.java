package com.example.citektest.data.datasource.local;

import com.example.citektest.data.datasource.DataSource;
import com.example.citektest.data.model.UserAuthData;

import java.util.List;

import io.reactivex.Single;

public interface LocalUserDataSource extends DataSource {

    Single<List<UserAuthData>> getData();

    void saveData(UserAuthData userAuthResponseList);

}
