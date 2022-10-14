package com.example.citektest.domain.mapper;

import android.util.Log;

import com.example.citektest.data.model.UserAuthData;
import com.example.citektest.domain.model.UserAuth;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class UserAuthDataToUserAuthMapper implements Mapper<UserAuthData, UserAuth> {
    @Override
    public UserAuth map(UserAuthData data) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        Date date = null;
        try {
            date = simpleDateFormat.parse(data.getCurrentDate());
        } catch (ParseException e){
            Log.e("UserDataToUserAuthMaper", "map: " + e.getMessage());
        }
        return new UserAuth(
                data.isResponse(),
                data.isContinueWork(),
                data.getPhotoHash(),
                date
        );
    }
}
