package com.brion.subwayproject.ui


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.brion.subwayproject.R
import com.brion.subwayproject.ui.custom.TrainDistributionAdapter
import kotlinx.android.synthetic.main.fragment_route_info.*

class RouteInfoFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_route_info, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        initData()
    }


    fun initData () {
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


    companion object {
        var instance = RouteInfoFragment()
    }
}
