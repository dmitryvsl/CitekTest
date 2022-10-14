package com.example.citektest.presentation.fragment_login.event;

import androidx.annotation.StringRes;

import com.example.citektest.domain.model.User;

import java.util.List;

public abstract class LoginEvent {

    // represents when event channel created

    public static class Initial extends LoginEvent {

    }

    // represents when fetching users list in Spinner view

    public static class UserListLoading extends LoginEvent {}


    // represents when error occurred while fetching users list

    public static class UserListError extends LoginEvent {

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

    public static class UserListFetchingSuccessful extends LoginEvent {

        List<User> users;

        public UserListFetchingSuccessful(List<User> users) {
            this.users = users;
        }

        public List<User> getUsers() {
            return users;
        }
    }



    //represents when no internet connection

    public static class NoNetworkConnection extends LoginEvent {}



    //represents when made request to auth user

    public static class UserAuthLoading extends LoginEvent {}



    //represents when error occurred while auth user

    public static class UserAuthError extends LoginEvent {

        private final int error;

        public UserAuthError(int error) {
            this.error = error;
        }

        public int getError() {
            return error;
        }
    }



    //represents when user auth successful

    public static class UserAuthSuccessful extends LoginEvent {}
}