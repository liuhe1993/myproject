package com.eric.base.utils;

import android.content.Context;

public class ContextUtils {
    @android.support.annotation.Nullable
    private static Context mContext;

    @android.support.annotation.Nullable
    public static Context getApplicationContext(){
        return mContext;
    }

    public static void initApplicationContext(@android.support.annotation.Nullable Context context){
        if (mContext != null || context == null){
            return;
        }
        mContext = context;
    }

    public static <T> T chooseOne(T...a) {
        return a[a.length/2];
    }
}
