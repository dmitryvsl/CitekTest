package com.example.citektest.presentation.fragment_list.event;

import com.example.citektest.domain.model.UserAuth;

import java.util.List;

public class ListEvent {

    // represents when event channel created

    public static class Initial extends ListEvent {
    }


    //represents when data fetched successful

    public static class DataFetchingSuccessful extends ListEvent {

        private final List<UserAuth> userAuthList;

        public DataFetchingSuccessful(List<UserAuth> userAuthList) {
            this.userAuthList = userAuthList;
        }

        public List<UserAuth> getUserAuthList() {
            return userAuthList;
        }
    }


    //represents when error occurred while fetching data

    public static class DataFetchingFailed extends ListEvent {

        private final int errorMessage;

        public DataFetchingFailed(int errorMessage) {
            this.errorMessage = errorMessage;
        }

        public int getErrorMessage() {
            return errorMessage;
        }
    }
}
