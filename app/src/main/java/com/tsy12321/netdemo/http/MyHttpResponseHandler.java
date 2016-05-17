package com.tsy12321.netdemo.http;

/**
 * MyHttpResponse回调 原始数据
 * Created by tsy on 16/5/17.
 */
public abstract class MyHttpResponseHandler {

    public abstract void onSuccess(int statusCode, byte[] responseBody);

    public abstract void onFailure(int statusCode, byte[] responseBody);
}
