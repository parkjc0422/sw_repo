package com.brion.subwayproject.route.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by jucherpark on 25/03/2019.
 */


class TimeStempInfo:Serializable {
    @SerializedName("lastTime")
    lateinit var lastTime:List<TimeStemp>
}