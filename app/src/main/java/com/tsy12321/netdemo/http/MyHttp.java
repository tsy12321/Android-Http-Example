package com.tsy12321.netdemo.http;

import android.content.Context;

import java.io.File;
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
        //android-async-http
        MyAsyncHttp.init(context);
    }

    /**
     * Post 文本参数
     * 回调Json数据
     * @param url
     * @param params
     * @param responseHandler
     */
    public static void doPost(String url, Map<String, String>params, final MyHttpJsonResponseHandler responseHandler) {
        doPost(url, params, null, responseHandler);
    }

    /**
     * Post 文本 文件混合参数
     * @param url
     * @param params
     * @param files key-file方式
     * @param responseHandler
     */
    public static void doPost(String url, Map<String, String>params, Map<String, File>files, final MyHttpJsonResponseHandler responseHandler) {
        //android-async-http
        MyAsyncHttp.doLibAsyncHttpPost(url, params, files, responseHandler);
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


}
