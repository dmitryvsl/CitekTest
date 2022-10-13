package com.example.citektest.data.datasource.remote;

import com.example.citektest.data.datasource.DataSource;
import com.example.citektest.data.model.UserData;
import com.example.citektest.domain.model.User;

import java.util.List;

import io.reactivex.Single;

public interface RemoteUserDataSource extends DataSource {

    Single<List<UserData>> getData(String imei);

}
