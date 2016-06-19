package com.example.metricslogger;

import android.util.Log;
/**
 * Created by Raymond on 2016-06-19.
 */

final class MetricsLogger {
    private static final String TAG = "MetricsLogger";
    static void logEvent(String category, String action, long value) {
        Log.d(TAG, "logEvent: " +
                "category=" + category +
                ", action=" + action +
                ", value=" + value);
    }
}
