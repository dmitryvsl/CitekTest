package com.example.citektest.data.model;

import com.example.citektest.domain.mapper.Mapper;
import com.example.citektest.domain.model.User;
import com.google.gson.annotations.SerializedName;

public class UserData {

    @SerializedName("User")
    private String user;
    @SerializedName("Uid")
    private String uid;
    @SerializedName("Language")
    private String language;

    public UserData(String user, String uid, String language) {
        this.user = user;
        this.uid = uid;
        this.language = language;
    }

    public String getUser() {
        return user;
    }

    public String getUid() {
        return uid;
    }

    public String getLanguage() {
        return language;
    }

    public User mapToUser(Mapper<UserData, User> mapper){
        return mapper.map(this);
    }

    public UserData mapToUserData(Mapper<User, UserData> mapper, User data){
        return mapper.map(data);
    }
}
