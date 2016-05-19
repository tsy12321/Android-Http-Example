package com.tsy12321.netdemo.http.glue;

import android.content.Context;

import com.loopj.android.http.FileAsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.tsy12321.netdemo.http.MyHttpFileResponseHandler;
import com.tsy12321.netdemo.http.MyHttpJsonResponseHandler;
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
     * 调用async-http post请求
     * @param context 当前context
     * @param url
     * @param params 文本参数
     * @param files 文件参数
     * @param responseHandler
     */
    public static void doLibAsyncHttpPost(Context context, String url, Map<String, String>params, Map<String, File>files, final MyHttpJsonResponseHandler responseHandler) {
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

        LibAsyncHttp.post(context, url, rparams, new JsonHttpResponseHandler() {
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

            @Override
            public void onCancel() {
                responseHandler.onCancel();
            }
        });
    }

    /**
     * 调用async-http get请求
     * @param context 当前context
     * @param url
     * @param params 文本参数
     * @param responseHandler
     */
    public static void doLibAsyncHttpGet(Context context, String url, Map<String, String>params, final MyHttpJsonResponseHandler responseHandler) {
        //android-async-http
        RequestParams rparams = new RequestParams(params);

        LibAsyncHttp.get(context, url, rparams, new JsonHttpResponseHandler() {
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
            public void onCancel() {
                responseHandler.onCancel();
            }
        });
    }

    /**
     * 调用async-http 下载文件
     * @param context 当前context
     * @param url
     * @param target 下载后存放file
     * @param responseHandler
     */
    public static void doLibAsyncHttpDownload(Context context, String url, File target, final MyHttpFileResponseHandler responseHandler) {
        LibAsyncHttp.get(context, url, null, new FileAsyncHttpResponseHandler(target) {
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
                responseHandler.onFailure(statusCode, "download error");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, File file) {
                responseHandler.onSuccess(statusCode);
            }

            @Override
            public void onProgress(long bytesWritten, long totalSize) {
                responseHandler.onProgress(bytesWritten, totalSize);
            }

            @Override
            public void onCancel() {
                responseHandler.onCancel();
            }
        });
    }

    /**
     * 取消当前context下发起的所有请求
     * @param context 当前context
     */
    public static void doLibAsyncHttpCacel(Context context) {
        LibAsyncHttp.cancelRequest(context);
    }
}
