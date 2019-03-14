package com.demoxample.shaadicardsample.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



/**
 * Created by Gaurav on 11-03-2019.
 */
class Timezone {

    @SerializedName("offset")
    @Expose
    var offset: String? = null

    @SerializedName("description")
    @Expose
    var description: String? = null

}