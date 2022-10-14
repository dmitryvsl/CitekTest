package com.example.citektest.domain.model;

import java.util.Date;

public class UserAuth {

    private final boolean response;
    private final boolean continueWork;
    private final String photoHash;
    private final Date currentDate;

    public UserAuth(boolean response, boolean continueWork, String photoHash, Date currentDate) {
        this.response = response;
        this.continueWork = continueWork;
        this.photoHash = photoHash;
        this.currentDate = currentDate;
    }

    public boolean isResponse() {
        return response;
    }

    public boolean isContinueWork() {
        return continueWork;
    }

    public String getPhotoHash() {
        return photoHash;
    }

    public Date getCurrentDate() {
        return currentDate;
    }
}
