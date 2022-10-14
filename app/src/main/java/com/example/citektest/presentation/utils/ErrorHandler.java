package com.example.citektest.presentation.utils;

import com.example.citektest.R;
import com.example.citektest.domain.exceptions.IncorrectDataException;
import com.example.citektest.domain.exceptions.NullBodyResponseException;
import com.example.citektest.domain.exceptions.RequestErrorException;

public class ErrorHandler {

    public static int handleError(RuntimeException e){
        if (e instanceof RequestErrorException)
            return R.string.request_error;

        else if (e instanceof NullBodyResponseException)
            return R.string.no_users_found;

        else if (e instanceof IncorrectDataException)
            return R.string.incorrect_password;

        else
            return R.string.error_occurred;
    }
}
