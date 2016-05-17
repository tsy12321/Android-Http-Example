package com.tsy12321.netdemo.http;

import android.content.Context;

import com.tsy12321.netdemo.http.lib.LibAsyncHttp;

import java.util.Map;

/**
 * 网络请求的统一调用
 * 可以修改不同的网络请求库
 * Created by tsy on 16/5/17.
 */
public class MyHttp {

    /**
     * 初始化(某些网络请求库需要传入context)
     */
    public static void init(Context context) {
        LibAsyncHttp.init(context);
    }

    /**
     * Post 回调原始数据 如果需要其他格式返回则增加不同的responseHandler
     * @param url
     * @param params
     * @param responseHandler
     */
    public static void doPost(String url, Map<String, String>params, final MyHttpResponseHandler responseHandler) {
        //android-async-http
        MyAsyncHttp.doLibAsyncHttpPost(url, params, responseHandler);
    }

    /**
     * Post 文本参数
     * 回调Json数据
     * @param url
     * @param params
     * @param responseHandler
     */
    public static void doPost(String url, Map<String, String>params, final MyHttpJsonResponseHandler responseHandler) {
        //android-async-http
        MyAsyncHttp.doLibAsyncHttpPost(url, params, responseHandler);
    }



    /**
     * Get请求
     * @param url
     * @param params
     * @param responseHandler
     */
    public static void doGet(String url, Map<String, String>params, final MyHttpJsonResponseHandler responseHandler) {
        //android-async-http
        MyAsyncHttp.doLibAsyncHttpGet(url, params, responseHandler);
    }

    /******************************android-async-http封装*****************************/


}
