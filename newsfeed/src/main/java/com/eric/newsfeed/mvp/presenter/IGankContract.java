package com.eric.newsfeed.mvp.presenter;

import com.eric.newsfeed.bean.GankBean;

import java.util.List;

import io.reactivex.Observable;

public interface IGankContract {

    interface IModel {
        Observable<GankBean> getGankBean(String type, int prePage, int page);

    }

    interface IView {

        void endRefresh();

        void onError();

        void onSuccess(List<GankBean.ResultData> list);

    }

    interface IPresenter {
        void refresh();
    }

}
