package com.brion.subwayproject.ui

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebViewClient
import com.brion.subwayproject.R
import com.brion.subwayproject.config.SubWayApiKey
import kotlinx.android.synthetic.main.activity_subway_map.*


class SubwayMapActivity : FragmentActivity() , SubwayInterface.SubwayMapListener{
    companion object {
        val TAG = "SubwayMapActivity"
    }
    private lateinit var mWebViewInterface:SubwayInterface
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subway_map)

        initSubwayMap()
    }

    private fun initSubwayMap () {
        subwayMap.webViewClient = WebViewClient()
        subwayMap.settings.let {
            Log.d(TAG, "initialize subway Map settings!!!")
            @JavascriptInterface // TODO: check is available
            it.javaScriptEnabled = true
            it.builtInZoomControls = true
            it.setSupportZoom(true)
            it.displayZoomControls = false
            it.defaultTextEncodingName = "UTF-8"
        }

        mWebViewInterface = SubwayInterface(this, subwayMap, SubWayApiKey)
        subwayMap.addJavascriptInterface(mWebViewInterface, "Android")
        subwayMap.loadUrl("file:///android_asset/mSeoul_Subway.html")
        mWebViewInterface.listener = this
    }


    override fun subwayEnd(station: String) {
        subwayTo.text = station
        checkAndRunRoute()
    }

    override fun subwayStart(station: String) {
        subwayFrom.text = station
        checkAndRunRoute()
    }

    private fun checkAndRunRoute(){
        if(subwayFrom.text.isNotEmpty() && subwayTo.text.isNotEmpty()) {
            startActivity(Intent(this, RouteActivity::class.java))
        }
    }
}
