package com.eric.newsfeed.mvp.model;


import com.eric.newsfeed.bean.GankBean;
import com.eric.newsfeed.mvp.presenter.IGankContract;
import com.eric.newsfeed.network.GankNetApiImpl;
import com.eric.newsfeed.network.INewsFeedNetAPI;

import io.reactivex.Observable;

public class GankModel implements IGankContract.IModel {

    private static GankModel mInstance;

    /**
     * model依赖抽象
     */
    private INewsFeedNetAPI.IGankNetApi mGankApi;

    public static GankModel getInstance() {
        if (mInstance == null) {
            mInstance = new GankModel();
        }
        return mInstance;
    }

    private GankModel() {
        mGankApi = new GankNetApiImpl();
    }


    @Override
    public Observable<GankBean> getGankBean(String type, int prePage, int page) {
        return mGankApi.getGankBean(type, prePage, page);
    }
}

