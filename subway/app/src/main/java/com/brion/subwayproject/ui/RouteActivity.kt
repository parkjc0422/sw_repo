package com.brion.subwayproject.ui

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.widget.ProgressBar
import com.brion.subwayproject.R
import com.brion.subwayproject.route.RouteManager
import com.brion.subwayproject.route.model.RouteMapper
import com.brion.subwayproject.route.provider.ConfigProvider
import com.brion.subwayproject.ui.custom.TransparentProgressDialog
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
        alert = TransparentProgressDialog(this,
                R.drawable.progress,
                R.style.TransparentProgressDialog)

        from = intent.getStringExtra(START)
        to = intent.getStringExtra(END)

        fromSubway.text = from
        toSubway.text = to

        minTimeLayout.setOnClickListener {
            minTime.setTextColor(resources.getColor(R.color.colorAccent))
            minTimeLine.setBackgroundColor(resources.getColor(R.color.colorAccent))
            minTrans.setTextColor(resources.getColor(R.color.colorSeparator))
            minTransLine.setBackgroundColor(resources.getColor(R.color.colorSeparator))
            fragment.loadInfo(RouteInfoFragment.RouteApiType.Fast)
        }

        minTransLayout.setOnClickListener {
            minTrans.setTextColor(resources.getColor(R.color.colorAccent))
            minTransLine.setBackgroundColor(resources.getColor(R.color.colorAccent))
            minTime.setTextColor(resources.getColor(R.color.colorSeparator))
            minTimeLine.setBackgroundColor(resources.getColor(R.color.colorSeparator))
            fragment.loadInfo(RouteInfoFragment.RouteApiType.Convenience)
        }
    }

    var from : String = ""
    var to:String = ""

    lateinit var alert:TransparentProgressDialog
    fun showProgress(boolean: Boolean) {
        if(boolean) alert.show()
        else alert.dismiss()
    }

}
