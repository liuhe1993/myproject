package com.eric.base.http;

public interface IHttpRequest {

    void setUrl(String url);
    void setParms(byte[] params);
    void execute();
    void setListener(IHttpListener listener);
}
