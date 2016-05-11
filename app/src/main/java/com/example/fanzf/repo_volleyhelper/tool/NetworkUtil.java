package com.example.fanzf.repo_volleyhelper.tool;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by fanzf on 2016/5/10.
 */
public class NetworkUtil {
    /**
     * 判断是否，连接网络
     *
     * @param context
     * @return
     */
    public static boolean getNetWork(Context context) {
        try {
            ConnectivityManager cwjManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = cwjManager.getActiveNetworkInfo();
            if (info != null && info.isAvailable()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
