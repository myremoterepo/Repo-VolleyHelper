package com.example.fanzf.repo_volleyhelper.helper;

import android.util.Log;

import com.example.fanzf.repo_volleyhelper.volley.Cache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 请求实体类
 * Created by wangg on 2015/8/10.
 */
public class RequestEntity {
    public static int POST = 1;
    public static int GET = 2;

    private int METHOD = 1;

    // 请求地址
    private String uri;

    // 请求参数
    private LinkedHashMap<String, String> params = new LinkedHashMap<String, String>();

    //缓存时间
    private long cacheTime = 0;

    //是否忽略缓存时间
    private boolean ignoreCache = false;

    public Cache.Entry cacheEntry;

    public Cache.Entry getCacheEntry() {
        return cacheEntry;
    }

    public void setCacheEntry(Cache.Entry cacheEntry) {
        this.cacheEntry = cacheEntry;
    }

    public String getUri() {
        if (METHOD == POST) {
            Log.e("Method", "POST");
            return uri;
        } else {
            Log.e("Method", "Get");
            return uri + getParamsForGet();
        }
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setParams(LinkedHashMap<String, String> params) {
        this.params = params;
    }
    public LinkedHashMap<String, String> getParams(){
        return params;
    }

    public void setMethod(int method) {
        this.METHOD = method;
    }

    public String getParamsForGet() {
        StringBuilder sBuffer = new StringBuilder("");
        for (Map.Entry<String, String> param : params.entrySet()) {
            sBuffer.append("&").append(param.getKey()).append("=").append(param.getValue());
        }
        return sBuffer.toString();
    }

    public long getCacheTime() {
        return cacheTime;
    }

    public void setCacheTime(long cacheTime) {
        this.cacheTime = cacheTime;
    }

    public boolean isIgnoreCache() {
        return ignoreCache;
    }

    public void setIgnoreCache(boolean ignoreCache) {
        this.ignoreCache = ignoreCache;
    }
}
