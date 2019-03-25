package com.brion.subwayproject

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.brion.subwayproject.config.SubWayApiKey
import com.brion.subwayproject.route.RouteManager
import com.brion.subwayproject.route.parser.RouteXmlParser
import com.brion.subwayproject.route.provider.ConfigProvider
import com.brion.subwayproject.ui.SubwayMapActivity
import kr.go.seoul.trafficsubway.TrafficSubwayDetailTypeA

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, SubwayMapActivity::class.java)
        startActivity(intent)
    }
}
