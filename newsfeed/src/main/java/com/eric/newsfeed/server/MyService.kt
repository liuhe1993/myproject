package com.eric.newsfeed.server

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.RemoteException

import com.eric.base.IRequestCallback
import com.eric.base.aidl.IMyAidlInterface

class MyService : Service() {

    internal var iMyAidlInterface: IMyAidlInterface.Stub = object : IMyAidlInterface.Stub() {
        @Throws(RemoteException::class)
        override fun basicTypes(anInt: Int, callback: IRequestCallback) {
            val result = callback.request()
        }
    }


    override fun onBind(intent: Intent): IBinder? {
        return iMyAidlInterface
    }
}
