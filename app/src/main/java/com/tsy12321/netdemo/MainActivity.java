package com.tsy12321.netdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.tsy12321.netdemo.http.MyHttp;
import com.tsy12321.netdemo.http.MyHttpJsonResponseHandler;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testHttp();
    }

    //android-async-http网络库
    private void testHttp() {

        MyHttp.doPost("https://secure-service.ci123.com/account/main.php/json/login/phone", null, new MyHttpJsonResponseHandler() {
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

        /*
        MyHttp.doPost("https://secure-service.ci123.com/account/main.php/json/login/phone", null, new MyHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, byte[] responseBody) {
                Log.i("tsy", "status code=" + statusCode + " response=" + new String(responseBody));
            }

            @Override
            public void onFailure(int statusCode, byte[] responseBody) {
                Log.i("tsy", "status code=" + statusCode + " response=" + new String(responseBody));
            }
        });
        */
    }
}
