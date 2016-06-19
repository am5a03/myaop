package com.example.metricslogger;

import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by Raymond on 2016-06-19.
 */

public final class MetricsLogger {
    public static void logEvent(String category, String action, int value) {
        Log.d(TAG, "logEvent: " +
                "category=" + category +
                ", action=" + action +
                ", value=" + value);
    }
}
