package com.niks.mylibrary;


import android.util.Log;

public class CustomLogger {

    private static boolean enableLogs = false;

    public static void Log(String tag, String message) {
        if (enableLogs) {
            Log.d(tag,message);
        }
    }
}
