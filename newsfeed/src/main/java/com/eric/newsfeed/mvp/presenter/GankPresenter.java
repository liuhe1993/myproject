package com.eric.newsfeed.mvp.presenter;

import android.annotation.SuppressLint;

import com.eric.base.mvp.BasePresenter;
import com.eric.newsfeed.bean.GankBean;
import com.eric.newsfeed.mvp.model.GankModel;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class GankPresenter extends BasePresenter implements IGankContract.IPresenter{

    private IGankContract.IView mView;

    String type = "Android";

    int prePage = 20;

    int page = 1;

    @Inject
    public GankPresenter(IGankContract.IView view) {
      mView = view;
    }

    @SuppressLint("CheckResult")
    @Override
    public void refresh() {
        GankModel.getInstance().getGankBean(type, prePage, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GankBean>() {
                    @Override
                    public void accept(GankBean gankBean) throws Exception {
                        mView.endRefresh();
                        if (gankBean == null || gankBean.isError()) {
                            mView.onError();
                            return;
                        }
                        mView.onSuccess(gankBean.getList());
                    }
                });
    }
}
