package com.tsy12321.netdemo.http.glue;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.tsy12321.netdemo.http.MyHttpJsonResponseHandler;
import com.tsy12321.netdemo.http.lib.LibVolley;
import com.tsy12321.netdemo.http.lib.LibVolleyResponseModel;

import java.util.Map;

/**
 * volley调用胶水层
 * Created by tsy on 16/5/23.
 */
public class MyVolley {

    /**
     * post请求
     * @param context 当前上下文
     * @param url
     * @param params
     * @param responseHandler
     */
    public static void doLibVolleyPost(Context context, String url, Map<String, String> params, final MyHttpJsonResponseHandler responseHandler) {
        LibVolley.post(context, url, params, new Response.Listener<LibVolleyResponseModel>() {
            @Override
            public void onResponse(LibVolleyResponseModel response) {
                responseHandler.onSuccess(response.status, response.data);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                responseHandler.onFailure(0, error.getMessage());
            }
        });
    }

    /**
     * get请求
     * @param context 当前上下文
     * @param url
     * @param params
     * @param responseHandler
     */
    public static void doLibVolleyGet(Context context, String url, Map<String, String> params, final MyHttpJsonResponseHandler responseHandler) {
        LibVolley.get(context, url, params, new Response.Listener<LibVolleyResponseModel>() {
            @Override
            public void onResponse(LibVolleyResponseModel response) {
                responseHandler.onSuccess(response.status, response.data);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                responseHandler.onFailure(0, error.getMessage());
            }
        });
    }

    /**
     * 取消请求
     * @param context 当前上下文
     */
    public static void doLibVolleyCancel(Context context) {
        LibVolley.cancelRequest(context);
    }
}
