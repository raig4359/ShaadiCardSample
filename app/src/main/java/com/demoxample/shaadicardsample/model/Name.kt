package com.demoxample.shaadicardsample.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created by Gaurav on 11-03-2019.
 */
class Name {

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("first")
    @Expose
    var first: String? = null

    @SerializedName("last")
    @Expose
    var last: String? = null

}