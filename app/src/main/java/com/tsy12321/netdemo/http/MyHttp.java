package com.tsy12321.netdemo.http;

import java.util.Map;

/**
 * 网络请求的统一调用
 * 可以修改不同的网络请求库
 * Created by tsy on 16/5/17.
 */
public class MyHttp {

    /**
     * Post 回调原始数据
     * @param url
     * @param params
     * @param responseHandler
     */
    public static void doPost(String url, Map<String, String>params, final MyHttpResponseHandler responseHandler) {
        //android-async-http
        MyAsyncHttp.doLibAsyncHttpPost(url, params, responseHandler);
    }

    /**
     * Post 回调Json数据
     * @param url
     * @param params
     * @param responseHandler
     */
    public static void doPost(String url, Map<String, String>params, final MyHttpJsonResponseHandler responseHandler) {
        //android-async-http
        MyAsyncHttp.doLibAsyncHttpPost(url, params, responseHandler);
    }

    /**
     * Get 回调原始数据
     * @param url
     * @param params
     * @param responseHandler
     */
    public static void doGet(String url, Map<String, String>params, final MyHttpResponseHandler responseHandler) {
        //android-async-http
        MyAsyncHttp.doLibAsyncHttpGet(url, params, responseHandler);
    }

    /**
     * Get 回调Json数据
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
