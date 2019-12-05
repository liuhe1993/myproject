package com.eric.base.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import com.eric.base.IRequestCallback;

import static android.content.Context.BIND_AUTO_CREATE;

public class ClientAidlManager {

    private static volatile ClientAidlManager instance;

    private ClientAidlManager() {

    };

    public static ClientAidlManager getInstance() {
        if (instance == null) {
            synchronized (ClientAidlManager.class) {
                if (instance == null) {
                    instance = new ClientAidlManager();
                }
            }
        }
        return instance;
    }

    private IMyAidlInterface myAidlInterface;

    private final Object lock = new Object();

    @android.support.annotation.Nullable
    private IRequestCallback callback = new IRequestCallback.Stub() {
        @Override
        public String request(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
            return null;
        }
    };

    @android.support.annotation.Nullable
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, @android.support.annotation.Nullable IBinder service) {
            synchronized (lock) {
                if (service != null) {
                    myAidlInterface = IMyAidlInterface.Stub.asInterface(service);
                }
                lock.notify();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };



    public boolean bindService(@android.support.annotation.NonNull Context context) {

        synchronized (lock) {
//        Intent intent1 = new Intent(context.getApplicationContext(), MyService.class);
//        bindService(intent1, connection, BIND_AUTO_CREATE);
//            try {
//                lock.wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        return true;
    }
    }


    private  void test() throws RemoteException {

        myAidlInterface.basicTypes(1, callback);
    }

}
