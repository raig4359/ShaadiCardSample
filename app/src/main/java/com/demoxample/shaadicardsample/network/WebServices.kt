package com.demoxample.shaadicardsample.network

import android.arch.lifecycle.LiveData
import com.demoxample.shaadicardsample.model.User
import retrofit2.http.GET
import retrofit2.http.QueryMap


/**
 * Created by Gaurav on 11-03-2019.
 */
interface WebServices {

    @GET("api")
    fun getUsers(@QueryMap options: Map<String, Int>): LiveData<ApiResponse<ArrayList<User>>>

}