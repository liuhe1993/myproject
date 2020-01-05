package com.eric.base.utils;

import android.content.Context;

public class ContextUtils {
    @androidx.annotation.Nullable
    private static Context mContext;

    @androidx.annotation.Nullable
    public static Context getApplicationContext(){
        return mContext;
    }

    public static void initApplicationContext(@androidx.annotation.Nullable Context context){
        if (mContext != null || context == null){
            return;
        }
        mContext = context;
    }

    public static <T> T chooseOne(T...a) {
        return a[a.length/2];
    }
}
