package com.example.citektest.data.datasource.local.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.citektest.data.model.UserAuthData;

import java.util.List;

@Dao
public interface UserAuthDao {

    @Query("Select * FROM UserAuth")
    List<UserAuthData> getUserAuthList();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveUserAuthList(UserAuthData authResponses);

    @Query("DELETE FROM UserAuth")
    void clearUserAuthentication();
}
