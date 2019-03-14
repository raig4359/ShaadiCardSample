package com.demoxample.shaadicardsample.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created by Gaurav on 11-03-2019.
 */
class Dob {

    @SerializedName("date")
    @Expose
    var date: String? = null

    @SerializedName("age")
    @Expose
    var age: Int = 0

}