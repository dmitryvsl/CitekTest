package com.example.citektest.presentation.fragment_list.event;

public class ListEventHandlerFactory {

    public ListEventHandler create(ListEvent event){

        if (event instanceof  ListEvent.DataFetchingSuccessful)
            return new ListEventHandler.OnDataFetchingSuccessfulEvent();

        else if (event instanceof ListEvent.DataFetchingFailed)
            return new ListEventHandler.OnDataFetchingFailedEvent();

        else
            return new ListEventHandler.OnInitialEvent();
    }
}