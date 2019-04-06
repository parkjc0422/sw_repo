package com.brion.subwayproject.ui


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.brion.subwayproject.R
import com.brion.subwayproject.route.RouteManager
import com.brion.subwayproject.route.model.RouteMapper
import com.brion.subwayproject.route.model.RouteMatching
import com.brion.subwayproject.route.provider.ConfigProvider
import com.brion.subwayproject.ui.custom.TrainDistributionAdapter
import com.brion.subwayproject.ui.custom.TransferAdapter
import com.brion.subwayproject.utils.krCurrency
import kotlinx.android.synthetic.main.fragment_route_info.*

class RouteInfoFragment : Fragment() {
    enum class RouteApiType {
        Fast("1"), Convenience("4");
        var value:String
        constructor(value:String) {
            this.value = value
        }

    }
    var type = RouteApiType.Fast
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_route_info, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getRouteIntfo()
    }


    private fun initComponent () {
        var mapper =RouteMatching(context as Context)
        mapper.loadInfo(routeModel)
        // Total time 29m
        totalTime.text = "${mapper.totalTime} 분"
        // 00:00~00:00
        startEndTime.text = mapper.startEndTime
        // 2개
//        passCount.text = "${routeModel.route}개"
        passCount.text = "${mapper.steps} 개"
        // 1회
        transCount.text = "${mapper.transfer}회"
        // 1,250원
        cardPrice.text = krCurrency("${mapper.price}".toInt())


        initRouteInfo()
        initData()
    }

    fun initRouteInfo () {
        trainRouteInfo.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        var adapter = TransferAdapter(context as Context)
        adapter.routeMapper = routeModel

        trainRouteInfo.adapter = adapter
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
    public fun getRouteIntfo () {
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

    fun loadInfo(type:RouteApiType) {
        val parent = activity as RouteActivity
        if(type != this.type) {
            this.type = type
            getRouteIntfo()
            parent.showProgress(true)
        }
    }

    companion object {
        var instance = RouteInfoFragment()
    }
}
