package com.eric.newsfeed.ui;

import android.content.Context;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.TextView;

import com.eric.base.database.InfoFlowRecord;
import com.eric.newsfeed.R;
import com.eric.newsfeed.observer.DownloadObserver;
import com.eric.newsfeed.observer.DownlodManager;


public class InfoFlowView extends InfoFlowBaseView implements DownloadObserver<String> {

    private TextView mTitle;

    private ImageView mImage;

    private InfoFlowRecord record;
    
    public InfoFlowView(@NonNull Context context) {
        super(context);
    }

    public void setRecord(InfoFlowRecord record) {
        this.record = record;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        // TODO: 2019/4/5 设置监听
//        mTitle = findViewById(R.id.tile);
        DownlodManager.getInstance().registerObserver(this);

    }

    @Override
    public void notifyUpdateState(String a) {

    }
    
}
