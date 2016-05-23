package com.tsy12321.netdemo.http;

import org.json.JSONObject;

/**
 * MyHttpResponse回调 Json数据
 * Created by tsy on 16/5/17.
 */
public abstract class MyHttpJsonResponseHandler {

    /**
     * 成功返回
     * @param statusCode
     * @param response
     */
    public abstract void onSuccess(int statusCode, JSONObject response);

    /**
     * 失败返回
     * @param statusCode
     * @param error_msg
     */
    public abstract void onFailure(int statusCode, String error_msg);

    /**
     * 请求被取消
     */
    public void onCancel() {
        //Log.v("myhttp", "request on cancel");
    }
}
