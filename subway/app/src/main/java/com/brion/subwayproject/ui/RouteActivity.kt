package com.brion.subwayproject.ui

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import com.brion.subwayproject.R

class RouteActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_route)

        initFragment()
    }

    fun initFragment () {
        var fragment = RouteInfoFragment.instance
        var transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.routeContent, fragment)
        transaction.commit()
    }
}
