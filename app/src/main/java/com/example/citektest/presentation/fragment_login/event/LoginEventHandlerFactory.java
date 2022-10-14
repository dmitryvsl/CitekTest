package com.example.citektest.presentation.fragment_login.event;

public class LoginEventHandlerFactory {

    public LoginEventHandler create(LoginEvent event){

        if (event instanceof  LoginEvent.UserListLoading)
            return new LoginEventHandler.OnUserListLoadingEvent();

        else if (event instanceof LoginEvent.UserListFetchingSuccessful)
            return new LoginEventHandler.OnUserListFetchingSuccessfulEvent();

        else if(event instanceof LoginEvent.NoNetworkConnection)
            return new LoginEventHandler.OnNoNetworkConnectionEvent();

        else if (event instanceof LoginEvent.UserListError)
            return new LoginEventHandler.OnUserListErrorEvent();

        else if (event instanceof LoginEvent.UserAuthLoading)
            return new LoginEventHandler.OnUserAuthLoadingEvent();

        else if (event instanceof LoginEvent.UserAuthError)
            return new LoginEventHandler.OnUserAuthErrorEvent();

        else if (event instanceof LoginEvent.UserAuthSuccessful)
            return new LoginEventHandler.OnUserAuthSuccessfulEvent();

        else
            return new LoginEventHandler.OnInitialEvent();
    }
}
