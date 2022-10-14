package com.example.citektest.data.model;

import com.google.gson.annotations.SerializedName;

public class UserAuthResponse {

    // should be list, but api responses single object
    @SerializedName("Authentication")
    UserAuthData userAuthData;

    @SerializedName("Code")
    int code;

    public UserAuthResponse(UserAuthData userAuthData, int code) {
        this.userAuthData = userAuthData;
        this.code = code;
    }

    public UserAuthData getUserAuthData() {
        return userAuthData;
    }

    public int getCode() {
        return code;
    }
}
