package com.demoxample.shaadicardsample.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Gaurav on 11-03-2019.
 */
class ApiResponse<T> {
    @SerializedName("code")
    @Expose
    var code: Int = 0
    @SerializedName("message")
    @Expose
    var message: String? = null
    @SerializedName("results")
    @Expose
    var data: T? = null

    var throwable: Throwable? = null

    override fun toString(): String {
        return "ApiResponse(code=$code, message=$message, data=$data)"
    }
}