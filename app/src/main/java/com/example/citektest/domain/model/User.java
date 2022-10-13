package com.example.citektest.domain.model;

public class User {

    private String user;
    private String uid;
    private String language;

    public User(String user, String uid, String language) {
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

}
