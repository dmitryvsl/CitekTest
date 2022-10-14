package com.example.citektest.data.datasource.local;

import com.example.citektest.data.datasource.local.database.CitekTestDatabase;
import com.example.citektest.data.model.UserAuthData;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class LocalDataSourceImpl implements LocalUserDataSource{

    CitekTestDatabase database;

    @Inject
    public LocalDataSourceImpl(CitekTestDatabase database) {
        this.database = database;
    }

    @Override
    public Single<List<UserAuthData>> getData() {
        return Single.create(emitter -> {
            List<UserAuthData> userAuthResponseList = database.getDao().getUserAuthList();
            emitter.onSuccess(userAuthResponseList);
        });
    }

    @Override
    public void saveData(UserAuthData userAuthResponseList) {
        database.getDao().clearUserAuthentication();
        database.getDao().saveUserAuthList(userAuthResponseList);
    }
}
