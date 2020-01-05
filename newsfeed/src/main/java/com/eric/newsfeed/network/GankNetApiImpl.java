package com.eric.newsfeed.network;

import com.eric.newsfeed.api.IGankApi;
import com.eric.newsfeed.bean.GankBean;

import io.reactivex.Observable;
import retrofit2.Call;

public class GankNetApiImpl extends BaseNetAPiImpl implements INewsFeedNetAPI.IGankNetApi {

    private IGankApi mGankApi = getRetrofitWrapper().create(IGankApi.class);


    @Override
    public Observable<GankBean> getGankBean(String type, int prePage, int page) {
        return mGankApi.getGankData(type, prePage, page);
    }
}
