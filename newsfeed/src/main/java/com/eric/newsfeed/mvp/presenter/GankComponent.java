package com.eric.newsfeed.mvp.presenter;

import com.eric.newsfeed.ui.fragment.GankFragment;

import dagger.Component;

@Component(modules = GankModule.class)
public interface GankComponent {
    void inject(GankFragment fragment);
}
