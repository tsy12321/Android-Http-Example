package com.tsy12321.netdemo;

import android.app.Application;
import android.content.Context;

/**
 * 全局 application
 * Created by tsy on 16/5/19.
 */
public class GlobalApp extends Application {

    private static GlobalApp app;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        app = this;
        context = getApplicationContext();
    }

    public static synchronized GlobalApp getInstance() {
        return app;
    }

    public Context getContext() {
        return context;
    }
}
