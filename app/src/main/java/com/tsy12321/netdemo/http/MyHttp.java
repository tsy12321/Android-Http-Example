package com.tsy12321.netdemo.http;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.Map;

import cz.msebera.android.httpclient.Header;

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
        RequestParams rparams = new RequestParams(params);

        LibAsyncHttp.post(url, rparams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                responseHandler.onSuccess(statusCode, responseBody);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                responseHandler.onFailure(statusCode, responseBody);
            }
        });
    }

    /**
     * Post 回调Json数据
     * @param url
     * @param params
     * @param responseHandler
     */
    public static void doPost(String url, Map<String, String>params, final MyHttpJsonResponseHandler responseHandler) {
        //android-async-http
        RequestParams rparams = new RequestParams(params);

        LibAsyncHttp.post(url, rparams, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                responseHandler.onSuccess(statusCode, response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                responseHandler.onFailure(statusCode, responseString);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                responseHandler.onFailure(statusCode, errorResponse);
            }
        });
    }

    /**
     * Get 回调原始数据
     * @param url
     * @param params
     * @param responseHandler
     */
    public static void doGet(String url, Map<String, String>params, final MyHttpResponseHandler responseHandler) {
        //android-async-http
        RequestParams rparams = new RequestParams(params);

        LibAsyncHttp.get(url, rparams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                responseHandler.onSuccess(statusCode, responseBody);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                responseHandler.onFailure(statusCode, responseBody);
            }
        });
    }

    /**
     * Get 回调Json数据
     * @param url
     * @param params
     * @param responseHandler
     */
    public static void doGet(String url, Map<String, String>params, final MyHttpJsonResponseHandler responseHandler) {
        //android-async-http
        RequestParams rparams = new RequestParams(params);

        LibAsyncHttp.get(url, rparams, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                responseHandler.onSuccess(statusCode, response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                responseHandler.onFailure(statusCode, responseString);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                responseHandler.onFailure(statusCode, errorResponse);
            }
        });
    }
}
