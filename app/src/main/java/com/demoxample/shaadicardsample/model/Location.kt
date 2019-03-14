package com.demoxample.shaadicardsample.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created by Gaurav on 11-03-2019.
 */
class Location {

    @SerializedName("street")
    @Expose
    var street: String? = null

    @SerializedName("city")
    @Expose
    var city: String? = null

    @SerializedName("state")
    @Expose
    var state: String? = null

    @SerializedName("postcode")
    @Expose
    var postcode: String? = null

    @SerializedName("coordinates")
    @Expose
    var coordinates: Coordinates? = null

    @SerializedName("timezone")
    @Expose
    var timezone: Timezone? = null

}