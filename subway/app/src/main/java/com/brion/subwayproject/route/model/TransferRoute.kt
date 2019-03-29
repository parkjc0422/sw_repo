package com.brion.subwayproject.route.model

import com.brion.subwayproject.route.model.PathInfo
import com.brion.subwayproject.route.model.Route
import com.brion.subwayproject.route.model.TransferInfo
import com.google.gson.annotations.SerializedName

/**
 * Created by jucherpark on 27/03/2019.
 */
class TransferRoute {
    @SerializedName("sTransfer")
    lateinit var sTransfer: TransferInfo

    @SerializedName("sPath")
    lateinit var sPath: PathInfo
}