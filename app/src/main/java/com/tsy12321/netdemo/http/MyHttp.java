package com.tsy12321.netdemo.http;

import android.content.Context;

import com.tsy12321.netdemo.http.glue.MyAsyncHttp;

import java.io.File;
import java.util.Map;

/**
 * 网络请求的统一调用
 * 可以修改不同的网络请求库
 * Created by tsy on 16/5/17.
 */
public class MyHttp {

    /**
     * Post 文本参数
     * 回调Json数据
     * @param url
     * @param params
     * @param responseHandler
     */
    public static void doPost(String url, Map<String, String>params, final MyHttpJsonResponseHandler responseHandler) {
        doPost(null, url, params, null, responseHandler);
    }

    /**
     * Post 文本 文件混合参数
     * @param url
     * @param params
     * @param files key-file方式
     * @param responseHandler
     */
    public static void doPost(String url, Map<String, String>params, Map<String, File>files, final MyHttpJsonResponseHandler responseHandler) {
        doPost(null, url, params, files, responseHandler);
    }

    /**
     * Post 文本参数
     * 回调Json数据
     * @param context 当前context
     * @param url
     * @param params
     * @param responseHandler
     */
    public static void doPost(Context context, String url, Map<String, String>params, final MyHttpJsonResponseHandler responseHandler) {
        doPost(context, url, params, null, responseHandler);
    }

    /**
     * Post 文本 文件混合参数
     * @param context 当前context
     * @param url
     * @param params
     * @param files key-file方式
     * @param responseHandler
     */
    public static void doPost(Context context, String url, Map<String, String>params, Map<String, File>files, final MyHttpJsonResponseHandler responseHandler) {
        //android-async-http
        MyAsyncHttp.doLibAsyncHttpPost(context, url, params, files, responseHandler);
    }

    /**
     * Get请求
     * @param url
     * @param params
     * @param responseHandler
     */
    public static void doGet(String url, Map<String, String>params, final MyHttpJsonResponseHandler responseHandler) {
        doGet(null, url, params, responseHandler);
    }

    /**
     * Get请求
     * @param context 当前context
     * @param url
     * @param params
     * @param responseHandler
     */
    public static void doGet(Context context, String url, Map<String, String>params, final MyHttpJsonResponseHandler responseHandler) {
        //android-async-http
        MyAsyncHttp.doLibAsyncHttpGet(context, url, params, responseHandler);
    }

    /**
     * 下载文件
     * @param url 下载地址
     * @param target 下载目的file
     * @param responseHandler
     */
    public static void doDownload(String url, File target, final MyHttpFileResponseHandler responseHandler) {
        doDownload(null, url, target, responseHandler);
    }

    /**
     * 下载文件
     * @param context 当前context
     * @param url 下载地址
     * @param target 下载目的file
     * @param responseHandler
     */
    public static void doDownload(Context context, String url, File target, final MyHttpFileResponseHandler responseHandler) {
        //android-async-http
        MyAsyncHttp.doLibAsyncHttpDownload(context, url, target, responseHandler);
    }

    /**
     * 取消当前context的所有请求
     * @param context 当前context
     */
    public static void cancelRequest(Context context) {
        //android-async-http
        MyAsyncHttp.doLibAsyncHttpCacel(context);
    }
}
