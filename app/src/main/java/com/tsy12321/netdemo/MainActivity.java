package com.tsy12321.netdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.tsy12321.netdemo.http.MyHttp;
import com.tsy12321.netdemo.http.MyHttpResponseHandler;

public class MainActivity extends AppCompatActivity {

    private TextView txtTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTitle = (TextView) findViewById(R.id.txtTitle);
        testAsyncHttp();
    }

    //android-async-http网络库
    private void testAsyncHttp() {

        /*
        MyHttp.doPost("https://secure-service.ci123.com/account/main.php/json/login/phone", null, new MyHttpJsonResponseHandler() {
            @Override
            public void onSuccess(int statusCode, JSONObject response) {
                Log.i("tsy", "status code=" + statusCode + " response=" + response);
            }

            @Override
            public void onFailure(int statusCode, JSONObject error_response) {
                Log.i("tsy", "status code=" + statusCode + " error_response=" + error_response);
            }

            @Override
            public void onFailure(int statusCode, String error_msg, byte[] responseBody) {
                Log.i("tsy", "status code=" + statusCode + " error_msg=" + error_msg + " responsebody=" + new String(responseBody));
            }
        });
        */

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
    }
}
