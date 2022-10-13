package com.example.citektest.presentation.mvi;

import androidx.annotation.StringRes;

import com.example.citektest.domain.model.User;

import java.util.List;

public abstract class Event{

    // represents when event channel created

    public static class Initial extends Event{    }



    // represents when fetching users list in Spinner view

    public static class UserListLoading extends Event{}



    // represents when error occurred while fetching users list

    public static class UserListError extends Event {

        @StringRes
        int error;

        /***
         * Expects string resource as parameter
         * @param error
         */
        public UserListError(int error) {
            this.error = error;
        }

        public int getError() {
            return error;
        }
    }



    //represents when fetching users list is successful

    public static class ShowUserList extends Event {

        List<User> users;

        public ShowUserList(List<User> users) {
            this.users = users;
        }

        public List<User> getUsers() {
            return users;
        }
    }



    //represents when no internet connection

    public static class ShowNoNetworkConnection extends Event {}




}