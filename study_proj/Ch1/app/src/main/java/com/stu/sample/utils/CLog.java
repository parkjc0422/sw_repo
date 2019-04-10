package com.stu.sample.utils;

import android.util.Log;

public class CLog {
    final static boolean debug = true;

    public static void d(String TAG, String message) {
        if(debug)
            Log.d(TAG, message);
    }

    public static void e(String TAG, String message) {
        if(debug)
            Log.e(TAG, message);
    }

    public static void i(String TAG, String message) {
        if(debug)
            Log.i(TAG, message);
    }

    public static void w(String TAG, String message) {
        if(debug)
            Log.w(TAG, message);
    }
}
