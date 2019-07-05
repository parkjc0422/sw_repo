package com.brion.subwayproject.route.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

open class Item: Serializable {
    @SerializedName("name")
    lateinit var name:String

    @SerializedName("bucket")
    lateinit var bucket:String


}
