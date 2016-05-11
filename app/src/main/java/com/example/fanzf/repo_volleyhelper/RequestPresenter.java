package com.example.fanzf.repo_volleyhelper;

import com.example.fanzf.repo_volleyhelper.bean.BaiDuBean;
import com.example.fanzf.repo_volleyhelper.helper.RequestEntity;
import com.example.fanzf.repo_volleyhelper.helper.ResponseListener;
import com.example.fanzf.repo_volleyhelper.helper.VolleyHelper;
import com.example.fanzf.repo_volleyhelper.tool.RequestAPI;
import com.example.fanzf.repo_volleyhelper.volley.VolleyError;

import java.util.LinkedHashMap;

/**
 * Created by fanzf on 2016/5/10.
 */
public class RequestPresenter implements RequestContract.Presenter {

    RequestContract.View mView;

    public RequestPresenter(RequestContract.View mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        mView.hideLoading();
    }

    private LinkedHashMap<String, String> params;

    @Override
    public void stringRequest() {
        RequestEntity entity = new RequestEntity();
        params = new LinkedHashMap<>();
        params.put("m", "LiveApi");
        params.put("do", "getTopLive");
        params.put("cf", "2345");
        params.put("gameId", "");
        entity.setParams(params);
        entity.setUri(RequestAPI.douyu);
        entity.setCacheTime(5 * 60 * 1000);
        entity.setIgnoreCache(false);
        VolleyHelper.addStringRequest(RequestEntity.GET, entity, new ResponseListener() {
            @Override
            public void onStart() {
                mView.showLoading();
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(Object result) {
                processResult((String)result);
            }

            @Override
            public void onError(VolleyError error) {
                mView.hideLoading();
                mView.setContent("请求出错！");
            }
        });
    }

    private void processResult(String content) {
        mView.hideLoading();
        mView.setContent(content);
    }

    @Override
    public void gsonRequest() {
        RequestEntity entity = new RequestEntity();
        entity.setUri(RequestAPI.baidu);
        VolleyHelper.addGsonObjectRequest(RequestEntity.GET, entity, BaiDuBean.class, new ResponseListener() {
            @Override
            public void onStart() {
                mView.showLoading();
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(Object result) {
                processResult((BaiDuBean) result);
            }

            @Override
            public void onError(VolleyError error) {
                mView.hideLoading();
                mView.setContent("请求出错！");
            }
        });
    }

    private void processResult(BaiDuBean result) {
        mView.setContent(result.toString());
        mView.hideLoading();
    }

}
