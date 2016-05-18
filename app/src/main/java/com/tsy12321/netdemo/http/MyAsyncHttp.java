package com.tsy12321.netdemo.http;

import android.content.Context;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.tsy12321.netdemo.http.lib.LibAsyncHttp;

import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

/**
 * android-async-http 胶水层
 * Created by tsy on 16/5/17.
 */
public class MyAsyncHttp {

    /**
     * 初始化context
     */
    public static void init(Context context) {
        LibAsyncHttp.init(context);
    }

    //android-async-http post json数据
    public static void doLibAsyncHttpPost(String url, Map<String, String>params, Map<String, File>files, final MyHttpJsonResponseHandler responseHandler) {
        //android-async-http
        RequestParams rparams = new RequestParams(params);

        //上传文件
        try {
            if(files != null && files.size() > 0) {
                for (Map.Entry<String, File> entry : files.entrySet()) {
                    rparams.put(entry.getKey(), entry.getValue());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

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

            @Override
            public void onProgress(long bytesWritten, long totalSize) {
                responseHandler.onProgress(bytesWritten, totalSize);
            }
        });
    }

    //android-async-http get json数据
    public static void doLibAsyncHttpGet(String url, Map<String, String>params, final MyHttpJsonResponseHandler responseHandler) {
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
