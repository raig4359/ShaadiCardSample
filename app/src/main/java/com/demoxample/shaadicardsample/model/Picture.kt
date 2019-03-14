package com.demoxample.shaadicardsample.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created by Gaurav on 11-03-2019.
 */
class Picture {

    @SerializedName("large")
    @Expose
    var large: String? = null

    @SerializedName("medium")
    @Expose
    var medium: String? = null

    @SerializedName("thumbnail")
    @Expose
    var thumbnail: String? = null

}