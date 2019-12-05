package com.eric.application1.activity;

import android.os.Bundle;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;

import androidx.annotation.Nullable;

public class LauncherActivity extends BaseActivity {
    private static final String TAG = "LauncherActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        ARouter.getInstance().build("/app/MainActivity").navigation();
    }
}
