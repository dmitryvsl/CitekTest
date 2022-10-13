package com.example.citektest.data.datasource.local;

import com.example.citektest.data.datasource.DataSource;
import com.example.citektest.data.model.UserData;

import java.util.List;

import io.reactivex.Single;

public interface LocalUserDataSource extends DataSource {

    Single<List<UserData>> getData();
}
