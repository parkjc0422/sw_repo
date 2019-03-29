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
        subwayMap.setWebViewClient(WebViewClient())
        subwayMap.getSettings().setJavaScriptEnabled(true)
        subwayMap.getSettings().setBuiltInZoomControls(true)
        subwayMap.getSettings().setSupportZoom(true)
        subwayMap.getSettings().setDisplayZoomControls(false)
        subwayMap.getSettings().setDefaultTextEncodingName("UTF-8")


        mWebViewInterface = SubwayInterface(this, subwayMap, SubWayApiKey)
        subwayMap.addJavascriptInterface(mWebViewInterface, "Android")
        subwayMap.loadUrl("file:///android_asset/subway_map.html")
        mWebViewInterface.listener = this
    }

    override fun startActivityForResult(intent: Intent? , requestCode: Int) {
        super.startActivityForResult(intent , requestCode)
        Log.d(TAG, "info : $requestCode")
        /**
         * Clear text info
         */
        subwayStart("")
        subwayEnd("")
    }

    override fun subwayEnd(station: String) {
        runOnUiThread {
            subwayTo.text = station
            checkAndRunRoute()
        }
    }

    override fun subwayStart(station: String) {
        runOnUiThread {
            subwayFrom.text = station
            checkAndRunRoute()
        }
    }

    val ReqRoutePathInfo = 1
    private fun checkAndRunRoute(){
        if(subwayFrom.text.isNotEmpty() && subwayTo.text.isNotEmpty()) {
            var routeInfo = Intent(this, RouteActivity::class.java)
            routeInfo.putExtra(RouteActivity.START,subwayFrom.text.toString())
            routeInfo.putExtra(RouteActivity.END,subwayTo.text.toString())
            startActivityForResult(routeInfo, ReqRoutePathInfo)
        }
    }
}
