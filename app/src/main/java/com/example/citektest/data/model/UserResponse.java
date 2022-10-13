package com.example.citektest.data.model;

import com.example.citektest.domain.model.User;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserResponse {

    @SerializedName("Users")
    ListUsers listUsers;

    public UserResponse(ListUsers listUsers) {
        this.listUsers = listUsers;
    }

    public ListUsers getListUsers() {
        return listUsers;
    }

    public static class ListUsers{

        @SerializedName("ListUsers")
        List<UserData> users;

        public ListUsers(List<UserData> users) {
            this.users = users;
        }

        public List<UserData> getUsers() {
            return users;
        }
    }
}
