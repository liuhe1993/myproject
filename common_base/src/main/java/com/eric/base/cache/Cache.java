package com.eric.base.cache;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.concurrent.ThreadPoolExecutor;

public abstract class Cache<T> {

    private ThreadPoolExecutor executor;

    private StoreManager storeManager;

    private CacheData<T> cacheData;

    @android.support.annotation.NonNull
    private Gson mGson = new Gson();


    public interface StoreManager{
        void clear();
        void putString(String key, String data);
        void putLong(String key, long data);
        @android.support.annotation.NonNull
        String getString(String key);
        long getLong(String key);
    }

    public static class CacheData<U>{
        private long lastupdate;

        private long validInterval;

        private U data;

        private boolean isValid(){
            return System.currentTimeMillis() - lastupdate > validInterval;
        }

        CacheData(long validInterval) {
            this.validInterval = validInterval;
        }
    }

    public Cache(ThreadPoolExecutor executor, long validInterval, StoreManager manager) {
        this.executor = executor;
        cacheData = new CacheData<T>(validInterval);
        storeManager = manager;
        load();
    }

    private void load() {
        cacheData.lastupdate = storeManager.getLong("lastupdate");
        cacheData.data = mGson.fromJson(storeManager.getString("data"), getDataType());
    }

    public abstract Type getDataType();

    public T getWithoutCache() {
        T data = getData();
        updateCache(data);
        return data;
    }

    private boolean isValid() {
        return cacheData.isValid();
    }

    @android.support.annotation.Nullable
    public T getCache() {
        if (isValid()){
            return cacheData.data;
        }
        return null;
    }

    protected void updateCache(T data) {
        cacheData.lastupdate = System.currentTimeMillis();
        cacheData.data = data;
        storeManager.putLong("lastupdate", cacheData.lastupdate);
        storeManager.putString("data", mGson.toJson(data));
    }

    public void update() {
        if (isValid()) {
            return;
        }
        getWithoutCache();
    }

    public abstract T getData();
}
