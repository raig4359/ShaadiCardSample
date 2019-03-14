package com.demoxample.shaadicardsample.network

/**
 * Created by Gaurav on 11-03-2019.
 */
class Resource<T> private constructor(var status: Status, var data: T?, var message: String?) {

    companion object {

        fun <T> success(data: T, msg: String?): Resource<T> {
            return Resource(Status.SUCCESS, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, "")
        }

        fun <T> error(data: T, msg: String?): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> failure(data: T?): Resource<T> {
            return Resource(Status.FAILURE, data, "")
        }

        fun <T> noInternetConnection(data: T?): Resource<T> {
            return Resource(Status.NO_INTERNET, data, "There is no internet connection")
        }

    }

}