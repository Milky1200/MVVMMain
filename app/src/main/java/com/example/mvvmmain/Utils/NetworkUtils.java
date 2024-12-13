package com.example.mvvmmain.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Build;

public class NetworkUtils {

    public static boolean isInternetAvailable(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Network activeNetwork = connectivityManager.getActiveNetwork();
                if (activeNetwork != null) {
                    NetworkCapabilities networkCapabilities =
                            connectivityManager.getNetworkCapabilities(activeNetwork);
                    return networkCapabilities != null &&
                            networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET);
                }
                return false;
            } else {
                // For devices below Android M
                @SuppressWarnings("deprecation")
                android.net.NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                return activeNetworkInfo != null && activeNetworkInfo.isConnected();
            }
        }

        return false;
    }
}
