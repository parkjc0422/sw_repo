package com.brion.subwayproject.ui

import android.app.Activity
import android.content.Intent
import android.webkit.JavascriptInterface
import android.webkit.WebView
import kr.go.seoul.trafficsubway.TrafficSubwayInfoTypeA

class SubwayInterface {
    enum class InfoType {
        Route, Info
    }
    var mAppView: WebView
    var mContext: Activity
    var openAPIKey:String
    var type:InfoType
    var listener:SubwayMapListener? = null


    constructor(activity:Activity , view:WebView , openAPIKey:String ) {
        this.mAppView = view
        this.mContext = activity
        this.openAPIKey = openAPIKey
        type = InfoType.Route
    }


    @JavascriptInterface
    public fun showSubwayInfo (station :String){
        when (type) {
            InfoType.Info -> {
                val intent = Intent(this.mContext, TrafficSubwayInfoTypeA::class.java)
                intent.putExtra("OpenAPIKey", this.openAPIKey)
                intent.putExtra("StationNM", station)
                this.mContext.startActivity(intent)
            }
            InfoType.Route-> {
                listener?.subway(station)
            }
        }
    }

    interface SubwayMapListener {
        fun subway(name:String)
    }

}