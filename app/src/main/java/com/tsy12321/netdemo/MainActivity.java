package com.tsy12321.netdemo;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.tsy12321.netdemo.http.MyHttp;
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

        //http初始化
        MyHttp.init(getApplicationContext());

        testHttp();
    }

    //android-async-http网络库
    private void testHttp() {

        Map<String, String> params = new HashMap<String, String>();
        params.put("uid", "asasa");

        File file = new File(Environment.getExternalStorageDirectory() + "/girls/head/output_tmp.jpg");
        Log.i("ysu", "exists " + file.exists() + " canExecute" + file.canExecute() + " canRead" + file.canRead() + " canWrite" + file.canWrite());
        Map<String, File> files = new HashMap<String, File>();
        files.put("avatar", file);
        MyHttp.doPost("http://192.168.3.1/test_post.php", params, files, new MyHttpJsonResponseHandler() {
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
        });
    }
}
