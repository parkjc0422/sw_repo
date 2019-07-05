package com.brion.subwayproject.ui


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.brion.subwayproject.R
import com.brion.subwayproject.route.RouteManager
import com.brion.subwayproject.route.model.*
import com.brion.subwayproject.route.provider.ConfigProvider
import com.brion.subwayproject.route.provider.JsonDataAPI
import com.brion.subwayproject.ui.custom.TrainDistributionAdapter
import com.brion.subwayproject.ui.custom.TransferAdapter
import com.brion.subwayproject.utils.krCurrency
import kotlinx.android.synthetic.main.fragment_route_info.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

    lateinit var jsonName : String
    lateinit var strDownloadToken : String

    private fun initData () {
        val parent = activity as RouteActivity
        parent.showProgress(true)
        getItem("data.json", complete = {
            jsonName = it.name

            getJsonInfo(jsonName, complete = {
                strDownloadToken = it.downloadTokens

                getJsonData(jsonName, strDownloadToken, complete = {
                    val jsonDataMap = it
                    val adapter = TrainDistributionAdapter(context as Context)

                    jsonDataMap.forEach{
                        var item = TrainDistributionAdapter.TrainDistributionItem()
                        item.linenNumber = it.key

                        item.array = convertIntArrayToTrainTypeArray(it.value)

                        adapter.items.add(item)
                    }

                    trainDistribution.adapter = adapter
                    parent.showProgress(false)

                })
            })
        })


    }

    fun getItem (targetName : String, complete : (Item) -> Unit) {

        var service = JsonDataAPI().getJsonDataService()

        service.getJsonItems().enqueue(object : Callback<Items> {

            override fun onFailure(call: Call<Items>, t: Throwable) {
                Log.d("error", "${t.message}")
                t.printStackTrace()
            }

            override fun onResponse(call: Call<Items>, response: Response<Items>) {
                Log.d("info", "message : ${response.body()}")

                var itemList = response.body()?.items
                if (itemList != null) {
                    for(item in itemList){
                        if(item.name == targetName)
                            complete(item)
                    }
                }
                //var parser = RouteXmlParser("${response.body()}")
            }
        })
    }

    fun getJsonInfo(targetJson : String, complete : (JsonInfoModel) -> Unit){
        var service = JsonDataAPI().getJsonDataService()

        service.getJsonInfo(targetJson).enqueue(object : Callback<JsonInfoModel> {
            override fun onFailure(call: Call<JsonInfoModel>, t: Throwable) {
                Log.d("error", "${t.message}")
                t.printStackTrace()
            }

            override fun onResponse(call: Call<JsonInfoModel>, response: Response<JsonInfoModel>) {
                response.body()?.let { complete(it) }
            }

        })
    }

    fun getJsonData(targetJson: String, token : String, complete : (Map<String, Array<Int>>) -> Unit){
        var service = JsonDataAPI().getJsonDataService()

        service.getJsonDataMap(targetJson, "media", token).enqueue(object: Callback<Map<String, Array<Int>>> {
            override fun onFailure(call: Call<Map<String, Array<Int>>>, t: Throwable) {
                Log.d("error", "${t.message}")
                t.printStackTrace()
            }

            override fun onResponse(call: Call<Map<String, Array<Int>>>, response: Response<Map<String, Array<Int>>>) {
                if(response.isSuccessful) {
                    response.body()?.let {
                        complete(it)
                    }
                }
            }
        })
    }
    fun convertIntArrayToTrainTypeArray(intArray : Array<Int>) : Array<TrainDistributionAdapter.TrainType>{
        val trainTypeArray : MutableList<TrainDistributionAdapter.TrainType> = mutableListOf()
        intArray.forEach {
            when(it){
                0->{trainTypeArray.add(TrainDistributionAdapter.TrainType.Lite)}
                1->{trainTypeArray.add(TrainDistributionAdapter.TrainType.Normal)}
                else->{trainTypeArray.add(TrainDistributionAdapter.TrainType.Congest)}
            }

        }

        return trainTypeArray.toTypedArray()
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
