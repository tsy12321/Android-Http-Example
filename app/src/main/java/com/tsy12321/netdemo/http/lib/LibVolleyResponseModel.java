package com.tsy12321.netdemo.http.lib;

import org.json.JSONObject;

import java.util.Map;

/**
 * volley response数据模型
 * Created by tsy on 16/5/23.
 */
public class LibVolleyResponseModel {
    public Map<String, String> headers;     //response头
    public int status;      //response status
    public JSONObject data;      //response 数据
}
