package com.example.citektest.data.mapper;

import com.example.citektest.data.model.UserData;
import com.example.citektest.domain.mapper.Mapper;
import com.example.citektest.domain.model.User;

public class UserDataToUserMapper implements Mapper<UserData, User> {
    @Override
    public User map(UserData data) {
        return new User(
                data.getUser(),
                data.getUid(),
                data.getLanguage()
        );
    }
}
