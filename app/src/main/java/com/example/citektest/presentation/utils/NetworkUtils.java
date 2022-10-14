package com.example.citektest.presentation.utils;

import android.content.Context;
import android.net.ConnectivityManager;

public class NetworkUtils {

    Context context;


    public NetworkUtils(Context context) {
        this.context = context;
    }

    public boolean isNetworkConnected(){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}
