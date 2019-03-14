package com.demoxample.shaadicardsample

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/**
 * Created by Gaurav on 11-03-2019.
 */
class BaseApp : Application() {

    companion object {

        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context

        fun getAppContext(): Context {
            return context
        }

    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

}