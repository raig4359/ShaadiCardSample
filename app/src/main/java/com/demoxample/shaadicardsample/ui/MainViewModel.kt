package com.demoxample.shaadicardsample.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.demoxample.shaadicardsample.model.User
import com.demoxample.shaadicardsample.network.ApiResponse
import com.demoxample.shaadicardsample.network.NetworkBoundResource
import com.demoxample.shaadicardsample.network.Resource
import com.demoxample.shaadicardsample.network.RestClient

/**
 * Created by Gaurav on 12-03-2019.
 */
class MainViewModel : ViewModel() {

    fun getShaadiUsers(params: HashMap<String, Int>): LiveData<Resource<ArrayList<User>?>> {
        return object : NetworkBoundResource<ArrayList<User>>() {
            override fun createCall(): LiveData<ApiResponse<ArrayList<User>>> {
                return RestClient.webServices.getUsers(params)
            }
        }.getAsLiveData()
    }

}