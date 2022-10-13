package com.example.citektest.presentation.utils;

import com.example.citektest.R;
import com.example.citektest.domain.exceptions.NullBodyResponseException;
import com.example.citektest.domain.exceptions.RequestErrorException;

public class ErrorHandler {

    public static Integer handleError(RuntimeException e){
        if (e instanceof RequestErrorException)
            return R.string.request_error;
        else if (e instanceof NullBodyResponseException)
            return R.string.no_users_found;
        else
            return R.string.error_occurred;
    }
}
