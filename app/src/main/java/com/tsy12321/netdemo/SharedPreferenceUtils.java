package com.tsy12321.netdemo;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by tsy on 16/5/23.
 */
public class SharedPreferenceUtils {

    //本地持久化存储name
    private static final String SHARED_PREFERENCES_NAME = "sharedpreference";

    /**
     * 本地持久化存储
     * 注:存储可用App本身存储
     */
    private static SharedPreferences.Editor mSharePreferenceEditor;

    /**
     * 保存数据
     * @param context 上下文
     * @param name key
     * @param data value
     */
    public static void saveSharedPreferences(Context context, String name, String data) {
        if (mSharePreferenceEditor == null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
            mSharePreferenceEditor = sharedPreferences.edit();
        }
        mSharePreferenceEditor.putString(name, data);
        mSharePreferenceEditor.commit();
    }

    /**
     * 读取存储数据
     * @param context 上下文
     * @param name 读取的key
     * @return value
     */
    public static String readSharedPreferences(Context context, String name) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(name, "");
    }

    /**
     * 移除指定name的信息
     * @param context
     * @param name
     */
    public static void removeSharedPreferences(Context context, String name) {
        if (mSharePreferenceEditor == null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
            mSharePreferenceEditor = sharedPreferences.edit();
        }
        mSharePreferenceEditor.remove(name);
        mSharePreferenceEditor.commit();
    }
}
