package com.brion.subwayproject.route.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by jucherpark on 25/03/2019.
 */

class TimeStemp :Serializable{
    @SerializedName("lastTimeDay")
    lateinit var lastTimeDay:String

    @SerializedName("lastTimeSat")
    lateinit var lastTimeSat:String

    @SerializedName("lastTimeSun")
    lateinit var lastTimeSun:String

    @SerializedName("stationName")
    lateinit var stationName:String

    @SerializedName("type")
    lateinit var type:String
}