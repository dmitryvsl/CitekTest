package com.example.citektest.presentation.utils;

import android.content.Context;

import java.util.UUID;

public class TelephonyManagerUtils {

    public static String getIMEI(Context context) {
        String imei = android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
        if (imei != null)
            return imei;

        return UUID.randomUUID().toString();
    }
}
