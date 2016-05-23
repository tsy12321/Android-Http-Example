package com.tsy12321.netdemo.http.lib;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.tsy12321.netdemo.GlobalApp;

import java.util.Map;

/**
 * 对volley的方法封装
 * Created by tsy on 16/5/23.
 */
public class LibVolley {
    private static RequestQueue mRequestQueue;

    public static void post(Context context, String url, Map<String, String> params, Response.Listener<LibVolleyResponseModel> listener, Response.ErrorListener err_listener) {
        if(mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(GlobalApp.getInstance().getContext());
        }

        LibVolleyJsonObjectRequest request = new LibVolleyJsonObjectRequest(Request.Method.POST, url
                , null, params, listener, err_listener);

        request.setTag(context);
        mRequestQueue.add(request);
    }

    public static void get(Context context, String url, Map<String, String> params, Response.Listener<LibVolleyResponseModel> listener, Response.ErrorListener err_listener) {
        if(mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(GlobalApp.getInstance().getContext());
        }

        LibVolleyJsonObjectRequest request = new LibVolleyJsonObjectRequest(Request.Method.GET, url
                , null, params, listener, err_listener);

        request.setTag(context);
        mRequestQueue.add(request);
    }
}
