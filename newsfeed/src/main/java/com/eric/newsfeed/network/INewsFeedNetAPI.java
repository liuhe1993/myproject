package com.eric.newsfeed.network;

import com.eric.newsfeed.bean.GankBean;

import io.reactivex.Observable;

public interface INewsFeedNetAPI {

    interface IGankNetApi {

        Observable<GankBean> getGankBean(String type, int prePage, int page);
    }

}
