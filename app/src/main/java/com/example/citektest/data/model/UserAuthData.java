package com.example.citektest.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.citektest.domain.mapper.Mapper;
import com.example.citektest.domain.model.UserAuth;
import com.google.gson.annotations.SerializedName;

@Entity (tableName = "UserAuth")
public class UserAuthData {

    @PrimaryKey(autoGenerate = true)
    int id;

    @SerializedName("Response")
    boolean response;

    @SerializedName("ContinueWork")
    boolean continueWork;

    @SerializedName("PhotoHash")
    String photoHash;

    @SerializedName("CurrentDate")
    String currentDate;

    public UserAuthData(int id, boolean response, boolean continueWork, String photoHash, String currentDate) {
        this.id = id;
        this.response = response;
        this.continueWork = continueWork;
        this.photoHash = photoHash;
        this.currentDate = currentDate;
    }

    public int getId() {
        return id;
    }

    public boolean isResponse() {
        return response;
    }

    public boolean isContinueWork() {
        return continueWork;
    }

    public String getPhotoHash() {
        return photoHash;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public UserAuth mapToUserAuth(Mapper<UserAuthData, UserAuth> mapper){
        return mapper.map(this);
    }
}
