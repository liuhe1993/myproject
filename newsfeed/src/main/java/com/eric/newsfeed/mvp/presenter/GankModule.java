package com.eric.newsfeed.mvp.presenter;

import dagger.Module;
import dagger.Provides;

@Module
public class GankModule {

    private final IGankContract.IView view;

    public GankModule(IGankContract.IView view) {
        this.view = view;
    }

    @Provides
    IGankContract.IView provideGankView() {
        return view;
    }
}
