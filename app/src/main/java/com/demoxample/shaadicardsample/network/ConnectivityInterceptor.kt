package com.demoxample.shaadicardsample.network

import com.demoxample.shaadicardsample.BaseApp
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * Created by Gaurav on 11-03-2019.
 */
class ConnectivityInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain?): Response {
        if (!NetworkUtil.isOnline(BaseApp.getAppContext())) throw NoConnectivityException()
        val builder: Request.Builder = chain!!.request()!!.newBuilder()
        return chain.proceed(builder.build())
    }
}