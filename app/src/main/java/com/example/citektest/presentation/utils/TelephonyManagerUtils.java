package com.example.citektest.presentation.utils;

import android.content.Context;
import android.telephony.TelephonyManager;

public class TelephonManagerUtils {

    public static String getIMEI(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }
}
