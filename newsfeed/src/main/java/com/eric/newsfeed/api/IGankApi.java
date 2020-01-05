package com.eric.newsfeed.api;

import com.eric.newsfeed.bean.GankBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IGankApi {

    @GET("data/{type}/{pre_page}/{page}")
    Observable<GankBean> getGankData(@Path("type") String type,
                                     @Path("pre_page") int prePage,
                                     @Path("page") int page);
}
