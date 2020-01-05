package com.eric.newsfeed.network;

import com.eric.base.http.retrofit.RetrofitWrapper;

/**
 * 某个base_url的网络请求基类
 */
public class BaseNetAPiImpl {

    private static final String BASE_URL = "https://gank.io/api/";

    private RetrofitWrapper retrofitWrapper = new RetrofitWrapper(BASE_URL);

    protected RetrofitWrapper getRetrofitWrapper() {
        return retrofitWrapper;
    }


}
