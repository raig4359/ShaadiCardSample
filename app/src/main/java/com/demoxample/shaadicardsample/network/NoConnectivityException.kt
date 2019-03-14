package com.demoxample.shaadicardsample.network

import java.io.IOException

/**
 * Created by Gaurav on 11-03-2019.
 */
class NoConnectivityException : IOException() {
    override val message: String?
        get() = "No connectivity exception"
}