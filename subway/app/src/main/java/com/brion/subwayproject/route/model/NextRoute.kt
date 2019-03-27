package com.brion.subwayproject.route.model

import com.google.gson.annotations.SerializedName

/**
 * Created by jucherpark on 27/03/2019.
 */
class NextRoute:Route() {
    @SerializedName("sPath")
    lateinit var sPath:Path

    @SerializedName("sTransfer")
    lateinit var sTransfer:String
}