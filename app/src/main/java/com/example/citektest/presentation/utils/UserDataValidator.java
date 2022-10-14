package com.example.citektest.presentation.utils;

import com.example.citektest.R;

public class UserDataValidator {

    /**
     * @param uid user uid
     * @param pass  user password
     * @return Return -1 if no error found. Otherwise return string resource with error description
     */
    public int validate(String uid, String pass) {
        if (uid.isEmpty())
            return R.string.no_one_user_selected;

//        if (pass.isEmpty())
//            return R.string.empty_password;

        return -1;
    }
}
