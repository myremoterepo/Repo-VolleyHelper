package com.example.fanzf.repo_volleyhelper.helper;

import android.content.Context;
import android.util.Log;

import com.example.fanzf.repo_volleyhelper.volley.AuthFailureError;
import com.example.fanzf.repo_volleyhelper.volley.Request;
import com.example.fanzf.repo_volleyhelper.volley.RequestQueue;
import com.example.fanzf.repo_volleyhelper.volley.Response.ErrorListener;
import com.example.fanzf.repo_volleyhelper.volley.Response.Listener;
import com.example.fanzf.repo_volleyhelper.volley.VolleyError;
import com.example.fanzf.repo_volleyhelper.volley.toolbox.StringRequest;
import com.example.fanzf.repo_volleyhelper.volley.toolbox.Volley;

import java.util.Map;

/**
 * @desc Volley请求简单封装
 * @since 1.0.0
 */
public class VolleyHelper {
    private static final String TAG = "volley_helper";
    private static RequestQueue mRequestQueue = null;

    /**
     * Volley请求对象初始化，建议放置在application子类或者app的第一个activity
     *
     * @param context
     */

    public static void initRequestQueue(Context context) {
        mRequestQueue = Volley.newRequestQueue(context);
    }

    public static RequestQueue getInstance() {
        return mRequestQueue;
    }

    /**
     * @param requestEntity      请求参数实体类
     * @param onResponseListener 请求结果回调
     * @desc 执行请求，返回字符串数据
     * @since 1.0.0
     */
    public static void addStringRequest(int method, final RequestEntity requestEntity,
                                        final ResponseListener onResponseListener) {
        // 开始请求
        onResponseListener.onStart();
        StringRequest request = new StringRequest(method, requestEntity.getUri(), requestEntity.getCacheTime(),
                new Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        onResponseListener.onFinish();
                        onResponseListener.onSuccess(response);
                    }

                }, new ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                onResponseListener.onFinish();
                onResponseListener.onError(error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return requestEntity.getParams();
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return super.getHeaders();
            }
        };
        request.setTag(TAG);
        request.setIgnoreCache(requestEntity.isIgnoreCache());
        mRequestQueue.add(request);
    }

    /**
     * @param requestEntity      请求参数实体类
     * @param onResponseListener 请求结果回调
     * @desc 执行请求，返回Gson解析得到对象
     * @since 1.0.0
     */
    public static <T> void addGsonObjectRequest(int method, final RequestEntity requestEntity,
                                                Class clazz, final ResponseListener onResponseListener) {
        // 开始请求
        onResponseListener.onStart();
        Request<T> request = new GsonObjectRequest<T>(method, requestEntity.getUri(), requestEntity.getCacheTime(),
                clazz, new Listener<T>() {

            @Override
            public void onResponse(T response) {
                onResponseListener.onFinish();
                onResponseListener.onSuccess(response);
            }
        }, new ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                onResponseListener.onFinish();
                onResponseListener.onError(error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return requestEntity.getParams();
            }
        };
        request.setTag(TAG);
        request.setIgnoreCache(requestEntity.isIgnoreCache());
        mRequestQueue.add(request);
    }




    /**
     * @desc 请求队列取消
     * @since 1.0.0
     */
    public static void cancelRequest() {
        try {
            mRequestQueue.cancelAll(TAG);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("info", "tag ==" + TAG + "的请求取消失败");
        }
    }

}