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
     * 失败返回 失败json
     * @param statusCode
     * @param error_response
     */
    public abstract void onFailure(int statusCode, JSONObject error_response);

    /**
     * 失败返回
     * @param statusCode
     * @param error_msg
     */
    public abstract void onFailure(int statusCode, String error_msg);

    /**
     * 上传进度
     * @param bytesWritten 已经长传字节
     * @param totalSize 总字节
     */
    public void onProgress(long bytesWritten, long totalSize) {
        //Log.v("myhttp", String.format("Progress %d from %d (%2.0f%%)", bytesWritten, totalSize, (totalSize > 0) ? (bytesWritten * 1.0 / totalSize) * 100 : -1));
    }

    /**
     * 请求被取消
     */
    public void onCancel() {
        //Log.v("myhttp", "request on cancel");
    }
}
