package com.example.fanzf.repo_volleyhelper.helper;

import com.example.fanzf.repo_volleyhelper.volley.VolleyError;

/**
 * OnResponseListener接口适配器类，方便使用，按照自己的需求进行方法的复写
 */
public interface ResponseListener{
    public void onStart();
    public void onFinish() ;
    public void onSuccess(Object result);
    public void onError(VolleyError error);
}
