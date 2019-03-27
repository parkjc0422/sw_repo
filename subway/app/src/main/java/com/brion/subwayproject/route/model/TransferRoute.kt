package com.brion.subwayproject.route.model

import com.google.gson.annotations.SerializedName

/**
 * Created by jucherpark on 27/03/2019.
 */
class TransferRoute :Route() {
    @SerializedName("sTransfer")
    lateinit var sTransfer:TransferInfo

    @SerializedName("sPath")
    lateinit var sPath:PathInfo
}