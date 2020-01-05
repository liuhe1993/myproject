package com.eric.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class LocalReceiver  {
    Context context;
    LocalBroadcastManager mReceiver;

    public void init(Context context) {
        this.context = context;
       mReceiver = LocalBroadcastManager.getInstance(context);

    }

    public void regist(BroadcastReceiver receiver) {
//        LocalBroadcastManager.getInstance(context).registerReceiver();
//        mReceiver.registerReceiver();
    }

}
