package com.eric.base.http.retrofit;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitWrapper {

    private Retrofit mRetrofit;

    private OkHttpClient.Builder builder;

//    public static RetrofitWrapper getInstance(String baseUrl) {
//        RetrofitWrapper retrofitWrapper;
//            synchronized (RetrofitWrapper.class) {
//                retrofitWrapper = new RetrofitWrapper(baseUrl);
//            }
//        return retrofitWrapper;
//    }

    public RetrofitWrapper(String baseUrl) {
        builder = new OkHttpClient.Builder();
        initSSL();
        mRetrofit = new Retrofit.Builder()
                //需要添加rxjava2的适配器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //还需要转换器从json到javabean
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .client(builder.build())
                .build();

    }


    public <T> T create(Class<T> clazz) {
        return mRetrofit.create(clazz);

    }


    /**
     * 初始化完全信任的信任管理器
     */
    @SuppressWarnings("deprecation")
    private void initSSL() {
        try {
            final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[]{};
                }
            }};

            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new SecureRandom());
            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            builder.sslSocketFactory(sslSocketFactory);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
