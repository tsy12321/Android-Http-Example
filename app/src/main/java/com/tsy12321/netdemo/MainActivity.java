package com.tsy12321.netdemo;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.tsy12321.netdemo.http.MyHttp;
import com.tsy12321.netdemo.http.MyHttpFileResponseHandler;
import com.tsy12321.netdemo.http.MyHttpJsonResponseHandler;

import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        doHttp();
        //doDownload();
    }

    //页面销毁关闭请求 防止crash 建议放到BaseActivity BaseFragrament里面
    @Override
    protected void onDestroy() {
        super.onDestroy();

        MyHttp.cancelRequest(this);     //页面退出 关闭所有请求
    }

    private void doHttp() {
        //文本参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("uid", "111");
        //文件参数
        File file = new File(Environment.getExternalStorageDirectory() + "/girls/head/output_tmp.jpg");
        Map<String, File> files = new HashMap<String, File>();
        files.put("avatar", file);
        //post
        MyHttp.doPost(this, "http://192.168.3.1/test_post.php", params, files, new MyHttpJsonResponseHandler() {
            @Override
            public void onSuccess(int statusCode, JSONObject response) {
                Log.i("tsy", "onSuccess status code=" + statusCode + " response=" + response);
            }

            @Override
            public void onFailure(int statusCode, JSONObject error_response) {
                Log.i("tsy", "onFailure status code=" + statusCode + " error_response=" + error_response);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                Log.i("tsy", "onFailure status code=" + statusCode + " error_msg=" + error_msg);
            }

            @Override
            public void onProgress(long bytesWritten, long totalSize) {
                Log.v("myhttp", String.format("Progress %d from %d (%2.0f%%)", bytesWritten, totalSize, (totalSize > 0) ? (bytesWritten * 1.0 / totalSize) * 100 : -1));
            }

            @Override
            public void onCancel() {
                Log.v("myhttp", "request on cancel");
            }
        });
    }

    private void doDownload() {
        File file = new File(Environment.getExternalStorageDirectory() + "/girls/head/output_tmp2.jpg");    //下载后存储的file位置
        MyHttp.doDownload(this, "http://192.168.3.1/head.jpg", file, new MyHttpFileResponseHandler() {
            @Override
            public void onSuccess(int statusCode) {
                Log.i("tsy", "onSuccess status code=" + statusCode);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                Log.i("tsy", "onFailure status code=" + statusCode);
            }

            @Override
            public void onProgress(long bytesWritten, long totalSize) {
                Log.v("myhttp", String.format("Progress %d from %d (%2.0f%%)", bytesWritten, totalSize, (totalSize > 0) ? (bytesWritten * 1.0 / totalSize) * 100 : -1));
            }

            @Override
            public void onCancel() {
                Log.v("myhttp", "request on cancel");
            }
        });
    }
}
