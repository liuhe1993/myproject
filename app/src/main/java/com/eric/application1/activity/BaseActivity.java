package com.eric.application1.activity;

import android.app.Activity;
import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;

import androidx.annotation.Nullable;
import butterknife.ButterKnife;

public abstract class BaseActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
