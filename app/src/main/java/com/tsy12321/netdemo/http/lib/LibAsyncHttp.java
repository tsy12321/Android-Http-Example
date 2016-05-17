package com.tsy12321.netdemo.http.lib;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;

/**
 * 对AndroidAsyncHttp库的使用封装
 * Created by tsy on 16/5/17.
 */
public class LibAsyncHttp {
    private static AsyncHttpClient client;
    private static Context mContext;

    public static void init(Context context) {
        mContext = context;
    }

    public static void post(String url, RequestParams params, ResponseHandlerInterface responseHandler) {
        if(client == null) {
            client = new AsyncHttpClient();
            client.setUserAgent("Android");
        }

        //cookie功能
        if(mContext != null) {
            PersistentCookieStore cookieStore = new PersistentCookieStore(mContext);
            client.setCookieStore(cookieStore);
        }
        client.post(url, params, responseHandler);
    }

    public static void get(String url, RequestParams params, ResponseHandlerInterface responseHandler) {
        if(client == null) {
            client = new AsyncHttpClient();
            client.setUserAgent("Android");
        }

        if(mContext != null) {
            PersistentCookieStore cookieStore = new PersistentCookieStore(mContext);
            client.setCookieStore(cookieStore);
        }
        client.get(url, params, responseHandler);
    }
}
