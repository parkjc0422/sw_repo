package com.brion.subwayproject.ui

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import com.brion.subwayproject.R
import com.brion.subwayproject.route.RouteManager
import com.brion.subwayproject.route.model.RouteMapper
import com.brion.subwayproject.route.provider.ConfigProvider
import kotlinx.android.synthetic.main.activity_route.*

class RouteActivity : FragmentActivity() {
    companion object {
        val START = "START"
        val END = "END"
    }
    var routeModel: RouteMapper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_route)

        initFragment()
        getRouteIntfo()
    }
    lateinit var fragment:RouteInfoFragment
    private fun initFragment () {
         fragment = RouteInfoFragment.instance
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.routeContent, fragment)
        transaction.commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishActivity(1)
    }

    lateinit var from : String
    lateinit var to:String
    private fun getRouteIntfo () {
        from = intent.getStringExtra(START)
        to = intent.getStringExtra(END)

        val configure = ConfigProvider.getInstance(this)

        val fromId = configure.getIdFromName(from)
        val toId = configure.getIdFromName(to)
        RouteManager().getRoute(fromId as String, toId as String, {
            it ->
            routeModel = it
            bindViewInfo()
        })
    }

    private fun bindViewInfo () {
        runOnUiThread {
            routeModel?.let {
                fromSubway.text = "${it.route.startStationName}"
                toSubway.text = "${it.route.endStationName}"
                fragment.setData(it)
            }
        }
    }
}
