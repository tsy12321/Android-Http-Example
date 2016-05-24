package com.tsy12321.netdemo.http.glue;

import android.content.Context;
import android.os.Handler;

import com.tsy12321.netdemo.http.MyHttpJsonResponseHandler;
import com.tsy12321.netdemo.http.lib.LibOkHttp;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by tsy on 16/5/23.
 */
public class MyOkHttp {

    public static void doLibOkHttpGet(Context context, String url, Map<String, String> params, final MyHttpJsonResponseHandler responseHandler) {
        //拼接url
        if(params != null && params.size() > 0) {
            int i = 0;
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if(i++ == 0) {
                    url = url + "?" + entry.getKey() + "=" + entry.getValue();
                } else {
                    url = url + "&" + entry.getKey() + "=" + entry.getValue();
                }
            }
        }

        final Handler handler = new Handler();
        LibOkHttp.get(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        responseHandler.onFailure(0, "IOException");
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        String response_body = "";
                        try {
                            response_body = response.body().string();
                            if(response.isSuccessful()) {
                                JSONObject body = new JSONObject(response_body);
                                responseHandler.onSuccess(response.code(), body);
                            } else {
                                responseHandler.onFailure(response.code(), response_body);
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                            responseHandler.onFailure(response.code(), "IOException");
                        } catch (JSONException e) {
                            e.printStackTrace();
                            responseHandler.onFailure(response.code(), "parse json error:" + response_body);
                        }
                    }
                });
            }
        });
    }
}
