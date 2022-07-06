package com.cartelnetwork.feproblem1.utils

import android.content.Context
import android.net.ConnectivityManager

object NetworkUtils {
    fun isConnectionAvailable(context: Context): Boolean =
        try {
            val connectivityManager = context
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo
            networkInfo != null && networkInfo.isConnectedOrConnecting
        } catch (e: Exception) {
            false
        }
}
