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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_route)

        initComponent()
        initFragment()
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

    fun initComponent () {
//        from = intent.getStringExtra(START)
//        to = intent.getStringExtra(END)

        fromSubway.text = from
        toSubway.text = to
    }

    var from : String = ""
    var to:String = ""

    fun showProgress(boolean: Boolean) {

    }

}
