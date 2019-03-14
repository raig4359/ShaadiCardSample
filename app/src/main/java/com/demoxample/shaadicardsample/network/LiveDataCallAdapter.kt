package com.demoxample.shaadicardsample.network

import android.arch.lifecycle.LiveData
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Created by Gaurav on 11-03-2019.
 */
class LiveDataCallAdapter<R, T>(private val responseType: Type, private val t: T) :
    CallAdapter<R, LiveData<ApiResponse<T>>> {

    override fun adapt(call: Call<R>?): LiveData<ApiResponse<T>> {
        return object : LiveData<ApiResponse<T>>() {
            val started = AtomicBoolean(false)

            val callback = object : Callback<R> {
                override fun onFailure(call: Call<R>?, t: Throwable?) {
                    postException(t)
                }

                override fun onResponse(call: Call<R>?, response: Response<R>?) {
                    try {
                        if (response!!.raw().isSuccessful) {
                            val bd = response.body() as ApiResponse<T>
                            if (response.isSuccessful) {
                                bd.code = 200
                                postValue(bd)
                            }
                        } else {
                            postServerFailure(response.raw().message())
                        }
                    } catch (e: Exception) {
                        postException(Throwable())
                    }
                }
            }

            override fun onActive() {
                super.onActive()
                if (started.compareAndSet(false, true)) {
                    call?.enqueue(callback)
                }
            }

            fun postException(throwable: Throwable?) {
                val apiResponse = ApiResponse<T>()
                apiResponse.throwable = throwable
                postValue(apiResponse)
            }

            fun postServerFailure(message: String) {
                val apiResponse = ApiResponse<T>()
                apiResponse.message = message
                apiResponse.data = null
                postValue(apiResponse)
            }

            fun refreshToken() {
                // to refresh token
            }
        }
    }


    override fun responseType(): Type {
        return responseType
    }
}