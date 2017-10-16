package com.alex.pattern.utils;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by Alex
 */

public class NetworkUtils {

    public static final String SERVER_URL = "https://api.github.com/";
    public static final int DISK_CACHE_SIZE = 10 * 1024 * 1024;
    public static final int TIME_CONNECTION = 10;
    public static final int READ_TIMEOUT = 30;
    public static final int TRY_COUNT = 3;

    public static boolean isNetworkConnected(Context context) {
        try {
            ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            return conMgr.getActiveNetworkInfo() != null && conMgr.getActiveNetworkInfo().isAvailable() && conMgr.getActiveNetworkInfo().isConnected();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
