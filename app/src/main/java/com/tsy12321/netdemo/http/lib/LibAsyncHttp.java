package com.tsy12321.netdemo.http.lib;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;

/**
 * 对AndroidAsyncHttp库的使用封装
 * Created by tsy on 16/5/17.
 */
public class LibAsyncHttp {
    private static AsyncHttpClient client;

    public static void post(String url, RequestParams params, ResponseHandlerInterface responseHandler) {
        if(client == null) {
            client = new AsyncHttpClient();
            client.setUserAgent("Android");
        }

        client.post(url, params, responseHandler);
    }

    public static void get(String url, RequestParams params, ResponseHandlerInterface responseHandler) {
        if(client == null) {
            client = new AsyncHttpClient();
            client.setUserAgent("Android");
        }

        client.get(url, params, responseHandler);
    }
}
