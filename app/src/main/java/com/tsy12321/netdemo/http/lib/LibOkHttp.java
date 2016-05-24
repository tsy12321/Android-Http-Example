package com.tsy12321.netdemo.http.lib;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by tsy on 16/5/23.
 */
public class LibOkHttp {
    private static OkHttpClient client;

    public static void post() {
        if(client == null) {
            client = new OkHttpClient();
        }


    }

    public static void get(String url, Callback callback) {
        if(client == null) {
            client = new OkHttpClient();
        }

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(callback);
    }
}
