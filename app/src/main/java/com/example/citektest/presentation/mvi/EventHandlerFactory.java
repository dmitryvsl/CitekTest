package com.example.citektest.presentation.mvi;

public class EventHandlerFactory {

    public EventHandler create(Event event){
        if (event instanceof  Event.UserListLoading)
            return new EventHandler.OnUserListLoadingEvent();

        else if (event instanceof Event.ShowUserList)
            return new EventHandler.OnShowUserListEvent();

        else if(event instanceof Event.ShowNoNetworkConnection)
            return new EventHandler.OnShowNoNetworkConnectionEvent();

        else if (event instanceof Event.UserListError)
            return new EventHandler.OnUserListErrorEvent();
        else
            return new EventHandler.OnInitialEvent();
    }
}
