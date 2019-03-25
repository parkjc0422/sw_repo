package com.brion.subwayproject.route.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by jucherpark on 25/03/2019.
 */

class Transfer:Serializable {
    @SerializedName("afterLine")
    lateinit var afterLine:String

    @SerializedName("beforeLine")
    lateinit var beforeLine:String

    @SerializedName("door")
    lateinit var door:String

    @SerializedName("timeavg")
    lateinit var timeavg:String

    @SerializedName("train")
    lateinit var train:String
}