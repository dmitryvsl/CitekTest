package com.example.citektest.presentation.utils;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class BasicAuthInterceptor implements Interceptor {

    String credentials = Credentials.basic("http", "http");

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        request = request.newBuilder().addHeader("Authorization", credentials).build();
        return chain.proceed(request);
    }
}
