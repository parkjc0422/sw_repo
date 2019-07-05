package com.brion.subwayproject.route.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

open class Items: Serializable {
    @SerializedName("prefixes")
    lateinit var prefixes:List<Any>

    @SerializedName("items")
    lateinit var items:List<Item>


}
