package com.eric.base.http;

public class HttpTask<T> implements Runnable {

    private IHttpRequest iHttpRequest;
    private IHttpListener iHttpListener;

    public HttpTask(String url, T requestData, IHttpRequest iHttpRequest, IHttpListener iHttpListener) {
        this.iHttpListener = iHttpListener;
        this.iHttpRequest = iHttpRequest;
        this.iHttpRequest.setUrl(url);
        this.iHttpRequest.setListener(iHttpListener);
        if (requestData != null) {

        }
    }
    @Override
    public void run() {

        this.iHttpRequest.execute();
    }
}
