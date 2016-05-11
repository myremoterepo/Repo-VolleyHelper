package com.example.fanzf.repo_volleyhelper;

import android.app.Application;

import com.example.fanzf.repo_volleyhelper.helper.VolleyHelper;

/**
 * Created by fanzf on 2016/5/10.
 */
public class VolleyHelperApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        VolleyHelper.initRequestQueue(this);
    }
}
