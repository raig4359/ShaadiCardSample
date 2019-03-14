package com.demoxample.shaadicardsample.network

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData

/**
 * Created by Gaurav on 11-03-2019.
 */
abstract class NetworkBoundResource<ResultType> {

    private val result = MediatorLiveData<Resource<ResultType?>>()

    init {
        result.value = Resource.loading(null)
        fetchFromNetwork()
    }

    private fun fetchFromNetwork() {
        val apiResponse = createCall()
        result.addSource(apiResponse) {
            result.removeSource<ApiResponse<ResultType>>(apiResponse)
            if (it!!.throwable != null) {
                if (it.throwable is NoConnectivityException) {
                    result.value = Resource.noInternetConnection(null)
                } else {
                    result.value = Resource.failure(null)
                }
            } else {
                when (it.code) {
                    200 -> result.value = Resource.success(it.data, it.message)
                    else -> result.value = Resource.error(it.data, it.message)
                }
            }
        }
    }

    abstract fun createCall(): LiveData<ApiResponse<ResultType>>

    fun getAsLiveData(): LiveData<Resource<ResultType?>> {
        return result
    }
}