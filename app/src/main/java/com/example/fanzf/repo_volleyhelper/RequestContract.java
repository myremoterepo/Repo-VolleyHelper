package com.example.fanzf.repo_volleyhelper;

/**
 * Created by fanzf on 2016/5/10.
 */
public interface RequestContract {

    interface Presenter extends BasePresenter{
        void stringRequest();
        void gsonRequest();
    }


    interface View<Presenter> extends BaseView{
        void showLoading();
        void hideLoading();
        void setContent(String content);
    }
}
