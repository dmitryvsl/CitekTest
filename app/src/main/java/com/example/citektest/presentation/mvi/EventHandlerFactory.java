package com.example.citektest.presentation.mvi;

import com.example.citektest.presentation.mvi.Event;
import com.example.citektest.presentation.mvi.EventHandler;

public class EventFactory {

    public EventHandler create(Event event){
        if (event instanceof  Event.UserListLoading)
            return new EventHandler.OnUserListLoadingEvent();
        if (event instanceof Event.ShowUserList)
            return new EventHandler.OnShowUserListEvent();
        else
            return new EventHandler.OnInitialEvent();
    }
}
