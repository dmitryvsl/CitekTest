package com.example.citektest.data.datasource.local.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.citektest.data.model.UserAuthData;

@Database(entities = {UserAuthData.class}, version = 1)
public abstract class CitekTestDatabase extends RoomDatabase {

    public abstract UserAuthDao getDao();
}
