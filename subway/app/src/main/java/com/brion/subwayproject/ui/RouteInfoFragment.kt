package com.brion.subwayproject.ui


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.brion.subwayproject.R
import com.brion.subwayproject.route.RouteManager
import com.brion.subwayproject.route.model.RouteMapper
import com.brion.subwayproject.route.provider.ConfigProvider
import com.brion.subwayproject.ui.custom.TrainDistributionAdapter
import com.brion.subwayproject.utils.krCurrency
import kotlinx.android.synthetic.main.fragment_route_info.*

class RouteInfoFragment : Fragment() {
    enum class RouteApiType {
        Fast, Convenience
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_route_info, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        initData()
        getRouteIntfo(RouteApiType.Fast)
    }

    private fun initComponent () {
        // Total time 29m
        totalTime.text = ""
        // 00:00~00:00
        startEndTime.text = ""
        // 2개
        passCount.text = "${routeModel.route}개"
        // 1회
        transCount.text = "${routeModel.route.transferNum}회"
        // 1,250원
        cardPrice.text = krCurrency(routeModel.route.price.toInt())


    }

    /**
     * TODO : remake function
     */
    private fun initData () {

        val adapter = TrainDistributionAdapter()

        var item = TrainDistributionAdapter.TrainDistributionItem()
        item.array = arrayOf(TrainDistributionAdapter.TrainType.Congest,
            TrainDistributionAdapter.TrainType.Lite, TrainDistributionAdapter.TrainType.Normal,TrainDistributionAdapter.TrainType.Congest,
            TrainDistributionAdapter.TrainType.Lite, TrainDistributionAdapter.TrainType.Normal)
        item.linenNumber = "5호선 10분후 도착"
        adapter.items.add(item)

        var item4 = TrainDistributionAdapter.TrainDistributionItem()
        item4.array = arrayOf(TrainDistributionAdapter.TrainType.Congest,
            TrainDistributionAdapter.TrainType.Lite, TrainDistributionAdapter.TrainType.Normal,TrainDistributionAdapter.TrainType.Congest,
            TrainDistributionAdapter.TrainType.Lite, TrainDistributionAdapter.TrainType.Normal)
        item4.linenNumber = "5호선 20분후 도착"
        adapter.items.add(item4)

        var item3 = TrainDistributionAdapter.TrainDistributionItem()
        item3.array = arrayOf(TrainDistributionAdapter.TrainType.Congest,
            TrainDistributionAdapter.TrainType.Lite, TrainDistributionAdapter.TrainType.Normal,TrainDistributionAdapter.TrainType.Congest,
            TrainDistributionAdapter.TrainType.Lite, TrainDistributionAdapter.TrainType.Normal)
        item3.linenNumber = "5호선 30분후 도착"
        adapter.items.add(item3)

        var item2 = TrainDistributionAdapter.TrainDistributionItem()
        item2.array = arrayOf(TrainDistributionAdapter.TrainType.Congest,
            TrainDistributionAdapter.TrainType.Lite, TrainDistributionAdapter.TrainType.Normal,TrainDistributionAdapter.TrainType.Congest,
            TrainDistributionAdapter.TrainType.Lite, TrainDistributionAdapter.TrainType.Normal)
        item2.linenNumber = "5호선 40분후 도착"
        adapter.items.add(item2)


        trainDistribution.adapter = adapter
    }

    lateinit var routeModel:RouteMapper
    public fun getRouteIntfo (type:RouteApiType) {
        val parent = activity as RouteActivity
        val configure = ConfigProvider.getInstance(context as Context)

        val fromId = configure.getIdFromName(parent.from)
        val toId = configure.getIdFromName(parent.to)

        parent.showProgress(true)
        RouteManager().getRoute(fromId as String, toId as String, type, complete = {
            routeModel = it as RouteMapper
            parent.showProgress(false)
            initComponent()
        })
    }


    companion object {
        var instance = RouteInfoFragment()
    }
}
