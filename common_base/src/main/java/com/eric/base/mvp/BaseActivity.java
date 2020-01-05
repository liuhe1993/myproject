package com.eric.base.mvp;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

public abstract class BaseActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        
        initView();
        initListener();
        initData();
    }

    protected abstract int getContentView();

    protected abstract void initData();

    protected abstract void initListener();

    protected abstract void initView();
    
    
}
