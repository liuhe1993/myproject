package com.eric.application1;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.eric.base.classloader.LoadUtil;

public class LHApplication extends Application {
    private static final String TAG = "LHApplication";

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");
        ARouter.openDebug();
        ARouter.openLog();
        ARouter.init(this);

        LoadUtil.loadClass(this, "/sdcard/Download/plugin/plugin-debug.apk");
    }
}
