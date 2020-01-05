package com.eric.base.server;

import android.content.Context;
import android.util.Pair;

public class BaseConfigServer {

    public static class Response<T> {
        private final int code;
        private final T obj;

        public Response(int code, T obj) {
            this.code = code;
            this.obj = obj;
        }

        public int getCode() {
            return code;
        }

        public T getObj() {
            return obj;
        }
    }

    @androidx.annotation.Nullable
    static <U> Response<U> request(Context context, String url, Class<U> clazz, int retryTime) {
        Pair<Integer, String> ret = null;
        for(int i = 0; i < retryTime; i++){
            ret = HttpUtils.post(context, url);
            if (ret.first == 0) {
                break;
            }
            if (i == retryTime -1){
                break;
            }
            if (ret == null) {
                return new Response<>(-1, null);
            }
            // TODO: 2019/4/5 其他异常处理
        }
        // TODO: 2019/4/5 利用gson反序列化成class
//        return new Response<>(200, );
        return null;
    }
}
