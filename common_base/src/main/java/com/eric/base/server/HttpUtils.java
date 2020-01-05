package com.eric.base.server;


import android.content.Context;
import android.util.Pair;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

public class HttpUtils {

    public static  Pair<Integer, String> post(Context context, String url) {
        return exec(context, "POST", url, 20000);
    }

    private static Pair<Integer, String> exec(@androidx.annotation.Nullable Context context, String method, @androidx.annotation.Nullable String url, int timeout) {
        if (context == null || url == null) {
            return new Pair<>(1, "invalid params");
        }
        // TODO: 2019/4/5 判断网络
        HttpURLConnection conn = null;
        OutputStream outputStream = null;
        InputStream inputStream = null;

        try {
            String errMsg;
            byte resultCode;
            String rsp;
            URLConnection urlConnection = (new URL(url)).openConnection();
            if (!(urlConnection instanceof HttpURLConnection)) {
                errMsg = "not HttpURLConnection";
                return new Pair<>(2, errMsg);
            }
            conn = (HttpURLConnection)urlConnection;
            if (urlConnection instanceof HttpsURLConnection){
                // TODO: 2019/4/5 https ssl 处理
            }
            if (timeout <= 0) {
                timeout = 20000;
            }
            conn.setRequestMethod(method);
            conn.setConnectTimeout(timeout);
            conn.setReadTimeout(timeout);
            conn.setDoInput(true);
            conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            // TODO: 2019/4/5 其他参数设置
            conn.connect();

            String body = "{userName:zhangsan,password:123456}";
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
            writer.write(body);
            writer.close();

            int rspCode = conn.getResponseCode();
            if (rspCode == 200) {
                StringBuilder builder = new StringBuilder();
                char[] buf = new char[1024];
                InputStreamReader reader = new InputStreamReader(conn.getInputStream(), "UTF-8");
                for (int rv = reader.read(buf); rv > 0; rv = reader.read(buf)) {
                    builder.append(new String(buf, 0, rv));
                    if (builder.length() > 33554432) {
                        return new Pair<>(3, "fail to read");
                    }
                }
                rsp = builder.toString();
                return new Pair<>(0, rsp);
            }
            // TODO: 2019/4/5 其他返回以及异常处理
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Pair<>(3, "other err");
    }
}
