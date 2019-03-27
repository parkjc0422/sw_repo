package com.brion.subwayproject.ui

import android.app.Activity
import android.content.Intent
import android.webkit.JavascriptInterface
import android.webkit.WebView
import com.brion.subwayproject.ui.custom.RouteCheckDialog
import kr.go.seoul.trafficsubway.TrafficSubwayInfoTypeA

class SubwayInterface {
    enum class InfoType {
        Route, Info
    }
    private var mAppView: WebView
    private var mContext: Activity
    private var openAPIKey:String
    private var type:InfoType
    var listener:SubwayMapListener? = null


    constructor(activity:Activity , view:WebView , openAPIKey:String ) {
        this.mAppView = view
        this.mContext = activity
        this.openAPIKey = openAPIKey
        type = InfoType.Route
    }

    /**
     * from javascript map (subway map)
     */
    @JavascriptInterface
    public fun showSubwayInfo (station :String){
        RouteCheckDialog(this.mContext, station,
                { it -> listener?.subwayStart(it) }
                , this::showInfo
                , { it-> listener?.subwayEnd(it) })
                .show()
    }

    private fun showInfo(station:String) {
        val intent = Intent(this.mContext, TrafficSubwayInfoTypeA::class.java)
        intent.putExtra("OpenAPIKey", this.openAPIKey)
        intent.putExtra("StationNM", station)
        this.mContext.startActivity(intent)
    }

    interface SubwayMapListener {
        fun subwayEnd(station: String)
        fun subwayStart(station: String)
    }

}