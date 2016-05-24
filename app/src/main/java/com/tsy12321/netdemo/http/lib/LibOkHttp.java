package com.tsy12321.netdemo.http.lib;

import java.util.Map;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by tsy on 16/5/23.
 */
public class LibOkHttp {
    private static OkHttpClient client;

    public static void post(String url, Map<String, String> params, Callback callback) {
        if(client == null) {
            client = new OkHttpClient();
        }

        FormBody.Builder builder = new FormBody.Builder();
        if(params != null && params.size() > 0) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                builder.add(entry.getKey(), entry.getValue());
            }
        }

        Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                .build();

        client.newCall(request).enqueue(callback);
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
