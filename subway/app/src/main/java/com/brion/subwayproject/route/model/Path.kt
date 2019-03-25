package com.brion.subwayproject.route.model

import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.Nullable
import java.io.Serializable

/**
 * Created by jucherpark on 25/03/2019.
 */

class Path :Serializable{
    @SerializedName("endLatitude")
    lateinit var endLatitude:String

    @SerializedName("endLongitude")
    lateinit var endLongitude:String

    @SerializedName("endStationCode")
    lateinit var endStationCode:String

    @SerializedName("endStationName")
    lateinit var endStationName:String

    @SerializedName("endTime")
    @Nullable
    lateinit var endTime:String

    @SerializedName("line")
    lateinit var line:String

    @SerializedName("pathType")
    lateinit var pathType:String

    @SerializedName("runTime")
    lateinit var runTime:String

    @SerializedName("startLatitude")
    lateinit var startLatitude:String

    @SerializedName("startLongitude")
    lateinit var startLongitude:String

    @SerializedName("startStationCode")
    lateinit var startStationCode:String

    @SerializedName("startStationName")
    lateinit var startStationName:String

    @SerializedName("startTime")
    lateinit var startTime:String

    @SerializedName("upDown")
    lateinit var upDown:String

    @SerializedName("viewTime")
    lateinit var viewTime:String

    @SerializedName("XEndStationnumber")
    lateinit var XEndStationnumber:String

    @SerializedName("XStartStationnumber")
    lateinit var XStartStationnumber:String
}