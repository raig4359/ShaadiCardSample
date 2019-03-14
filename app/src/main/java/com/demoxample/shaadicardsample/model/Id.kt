package com.demoxample.shaadicardsample.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created by Gaurav on 11-03-2019.
 */
class Id {

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("value")
    @Expose
    var value: Any? = null

}