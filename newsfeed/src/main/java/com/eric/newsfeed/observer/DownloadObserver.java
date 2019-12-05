package com.eric.newsfeed.observer;

public interface DownloadObserver<T> {
    void notifyUpdateState(T a);
}
