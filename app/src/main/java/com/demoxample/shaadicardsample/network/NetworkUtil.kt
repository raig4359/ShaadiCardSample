package com.demoxample.shaadicardsample.network

import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by Gaurav on 11-03-2019.
 */
object NetworkUtil {
    fun isOnline(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}