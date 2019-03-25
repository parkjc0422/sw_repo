package com.brion.subwayproject.route.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Route: Serializable {
    @SerializedName("sLang")
    lateinit var sLang:String

    @SerializedName("dayKind")
    lateinit var dayKind:String

    @SerializedName("startStationCode")
    lateinit var startStationCode:String

    @SerializedName("startStationName")
    lateinit var startStationName:String

    @SerializedName("endStationCode")
    lateinit var endStationCode:String

    @SerializedName("endStationName")
    lateinit var endStationName:String

    @SerializedName("transferNum")
    lateinit var transferNum:String

    @SerializedName("totalTime")
    lateinit var totalTime:String

    @SerializedName("writeDate")
    lateinit var writeDate:String

    @SerializedName("price")
    lateinit var price:String // card

    @SerializedName("price2")
    lateinit var price2:String // money

    @SerializedName("div")
    lateinit var div:String

    @SerializedName("regular")
    lateinit var regular:String

    @SerializedName("regularStep")
    lateinit var regularStep:String

    @SerializedName("distance")
    lateinit var distance:String

    @SerializedName("XStartStationnumber")
    lateinit var XStartStationnumber:String

    @SerializedName("XEndStationnumber")
    lateinit var XEndStationnumber:String

    @SerializedName("teensPrice")
    lateinit var teensPrice:String  // card

    @SerializedName("teensPrice2")
    lateinit var teensPrice2:String  // money

    @SerializedName("childPrice")
    lateinit var childPrice:String  // card

    @SerializedName("childPrice2")
    lateinit var childPrice2:String // money

    @SerializedName("startLatitude")
    lateinit var startLatitude:String

    @SerializedName("startLongitude")
    lateinit var startLongitude:String

    @SerializedName("endLatitude")
    lateinit var endLatitude:String

    @SerializedName("endLongitude")
    lateinit var endLongitude:String

    @SerializedName("sPath")
    lateinit var sPath:PathInfo

    @SerializedName("lastTimeList")
    lateinit var lastTimeList:TimeStempInfo

    @SerializedName("sTransfer")
    lateinit var sTransfer:TransferInfo
}
