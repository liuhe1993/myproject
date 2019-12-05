package com.eric.base.http;

import java.io.InputStream;

public class JsonHttpListener<T> implements IHttpListener {

    private Class<T> response;
    @Override
    public void onSuccess(InputStream inputStream) {

    }

    @Override
    public void onFailure() {

    }
}
