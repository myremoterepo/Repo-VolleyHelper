package com.example.fanzf.repo_volleyhelper.helper;

import com.example.fanzf.repo_volleyhelper.volley.NetworkResponse;
import com.example.fanzf.repo_volleyhelper.volley.ParseError;
import com.example.fanzf.repo_volleyhelper.volley.Request;
import com.example.fanzf.repo_volleyhelper.volley.Response;
import com.example.fanzf.repo_volleyhelper.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;


/**
 * gson对象解析类
 * A canned request for retrieving the response body at a given URL as a Object.
 * Created by wangg on 2015/8/10.
 */
public class GsonObjectRequest<T> extends Request<T> {

    private Response.Listener<T> mListener;
    private Response.ErrorListener mErrorListener;
    private final Gson mGson;
    private Class<T> mClazz;

    /**
     * Creates a new request with the given method.
     *
     * @param method        the request {@link Method} to use
     * @param url           URL to fetch the string at
     * @param listener      Listener to receive the String response
     * @param errorListener Error listener, or null to ignore errors
     */
    public GsonObjectRequest(int method, String url, long cacheTime, Class<T> clazz,
                             Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        mClazz = clazz;
        mListener = listener;
        mErrorListener = errorListener;
        mGson = new Gson();
        this.cacheTime = cacheTime;
    }

    /**
     * Creates a new GET request.
     *
     * @param url           URL to fetch the string at
     * @param listener      Listener to receive the String response
     * @param errorListener Error listener, or null to ignore errors
     */
    public GsonObjectRequest(String url, Class<T> clazz, Response.Listener<T> listener,
                             Response.ErrorListener errorListener) {
        this(Request.Method.GET, url, 0, clazz, listener, errorListener);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        String parsed;
        try {
            parsed = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            return Response.success(mGson.fromJson(parsed, mClazz),
                    HttpHeaderParser.parseCacheHeaders(response, cacheTime));
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }

    @Override
    public void finish(String tag) {
        super.finish(tag);
        mListener = null;
    }
}
