package com.example.citektest;

import android.app.Application;
import android.content.Context;

import com.example.citektest.di.AppComponent;
import com.example.citektest.di.DaggerAppComponent;

public class App extends Application {

    AppComponent component;

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent.create();
        context = getApplicationContext();
    }

    public AppComponent getComponent() {
        return component;
    }

    public static Context getContext(){
        return context;
    }
}
