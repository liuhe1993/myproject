// IRequestCallback.aidl
package com.eric.base;

// Declare any non-default types here with import statements

interface IRequestCallback {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    String request(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
}
