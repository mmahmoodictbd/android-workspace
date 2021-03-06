package com.chumbok.dailypoetry.util;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.chumbok.dailypoetry.AppController;

public class NetworkUtil {

    public static boolean isNetworkAvailable() {

        Context appCtx = AppController.getInstance().getApplicationContext();

        ConnectivityManager connectivityManager
                = (ConnectivityManager) appCtx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
