// IMyAidlInterface.aidl
package com.eric.base.aidl;

// Declare any non-default types here with import statements

import com.eric.base.IRequestCallback;

interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void handle(int aa, String cc);

    void basicTypes(int anInt, IRequestCallback callback);


}
