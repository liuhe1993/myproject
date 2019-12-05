package com.eric.base.http;

public interface IDataListenner<T> {
    void onSuccess(T t);
    void onFailure();
}
