package com.example.xuanlan.nightwatchman;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HttpUtil {
    public static void sendOkHttpRequest(String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }

    public static void sendOkHttpRequestPost(Request request, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(callback);
    }
}
