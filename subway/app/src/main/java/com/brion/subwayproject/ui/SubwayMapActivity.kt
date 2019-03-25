package com.brion.subwayproject.ui

import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.View
import android.webkit.WebViewClient
import android.widget.Toast
import com.brion.subwayproject.R
import com.brion.subwayproject.config.SubWayApiKey
import com.brion.subwayproject.config.SubWayApiKey_debug
import com.brion.subwayproject.route.RouteManager
import com.brion.subwayproject.route.provider.ConfigProvider
import kotlinx.android.synthetic.main.activity_subway_map.*


class SubwayMapActivity : FragmentActivity() , SubwayInterface.SubwayMapListener{
    val TAG = "SubwayMapActivity"
    lateinit var mWebViewInterface:SubwayInterface
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subway_map)

        initSubwayMap()

        mapTypeGroup.setOnCheckedChangeListener { group, checkedId ->
            if(checkedId == R.id.mapTypeInfo) {
                mWebViewInterface.type = SubwayInterface.InfoType.Info
                searchRoute.visibility = View.GONE
            } else if(checkedId == R.id.mapTypeRoute) {
                mWebViewInterface.type = SubwayInterface.InfoType.Route
                searchRoute.visibility = View.VISIBLE
            }
        }

        searchRoute.setOnClickListener {
            if(subwayFrom.text.isNotEmpty() && subwayTo.text.isNotEmpty()) {
                startActivity(Intent(this, RouteActivity::class.java))
//                val config = ConfigProvider.getInstance(applicationContext)
//                val route = RouteManager()
//                route.getRoute(
//                    config.getIdFromName("${subwayFrom.text}") as String,
//                    config.getIdFromName("${subwayTo.text}") as String,
//                    complete =  { it ->
//                        Toast.makeText(applicationContext, "result : $it",Toast.LENGTH_SHORT).show()
//                        startActivity(Intent(this, RouteActivity::class.java))
//                })
            }
        }
    }

    fun initSubwayMap () {
        subwayMap.setWebViewClient(WebViewClient())
        subwayMap.getSettings().setJavaScriptEnabled(true)
        subwayMap.getSettings().setBuiltInZoomControls(true)
        subwayMap.getSettings().setSupportZoom(true)
        subwayMap.getSettings().setDisplayZoomControls(false)
        subwayMap.getSettings().setDefaultTextEncodingName("UTF-8")
        mWebViewInterface = SubwayInterface(this, subwayMap, SubWayApiKey)
        subwayMap.addJavascriptInterface(mWebViewInterface, "Android")
        subwayMap.loadUrl("file:///android_asset/mSeoul_Subway.html")
        mWebViewInterface.listener = this
    }


    /**
     * Route only
     */
    override fun subway(name: String) {
        var alert = AlertDialog.Builder(this)
        alert.let {
            it.setTitle("$name")
            it.setNegativeButton("출발", object: DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int) {
//                    subwayFrom.text = "$name"
                    setTextFrom(name)
                }
            })
            it.setPositiveButton("도착", object : DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int) {
//                    subwayTo.text = "$name"
                    setTextTo(name)
                }
            }).show()
        }
    }

    fun setTextFrom (from:String) {
        runOnUiThread {
            subwayFrom.text = from
        }
    }

    fun setTextTo(to:String) {
        runOnUiThread {
            subwayTo.text = to
        }
    }
}
