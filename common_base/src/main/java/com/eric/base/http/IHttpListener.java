package com.eric.base.http;

import java.io.InputStream;

public interface IHttpListener {
    void onSuccess(InputStream inputStream);
    void onFailure();
}
