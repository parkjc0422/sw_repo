package com.brion.subwayproject.route.model

import com.google.gson.annotations.SerializedName

/**
 * Created by jucherpark on 27/03/2019.
 */
class NoTranferRoute :Route(){
    @SerializedName("sTransfer")
    lateinit var sTransfer:String

    @SerializedName("sPath")
    lateinit var sPath:PathInfo
}