package com.brion.subwayproject.route

import android.content.Context
import android.util.Log
import com.brion.subwayproject.http.getRequestBody
import com.brion.subwayproject.route.parser.RouteXmlParser
import com.brion.subwayproject.route.provider.ConfigProvider
import com.brion.subwayproject.route.provider.RouteProvider
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RouteManager {
    val TAG = "RouteManager"

    fun getRoute(fromId:String, toId:String, complete: (String?)->Unit) {
        var service = RouteProvider().getRouteService()

        val strRequestBody = getRequestBody(fromId, toId)
//            "departureId=$fromId&stopId=&arrivalId=$toId&sKind=1&sTime=&weekTag=&__encrypted="
        val requestBody = RequestBody.create(MediaType.parse("text/plain"), strRequestBody)

        service.getRoute(requestBody).enqueue(object :Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.d(TAG, "message : ${response.body()}")
//                var parser = RouteXmlParser("${response.body()}")
//                parser.test()
                complete(response.body())
            }
        })
    }


    /**
     * @brief api testing
     */
    fun hitTest () {

        var service = RouteProvider().getRouteService()

        val strRequestBody = "departureId=0425&stopId=&arrivalId=0321&sKind=1&sTime=&weekTag=&__encrypted="
        val requestBody = RequestBody.create(MediaType.parse("text/plain"), strRequestBody)

        service.getRoute(requestBody).enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d(TAG, "${t.message}")
                t.printStackTrace()
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.d(TAG, "message : ${response.body()}")
                var parser = RouteXmlParser("${response.body()}")
//                parser.test()
                Log.d(TAG , "Message : ${parser.parseModel().toString()}")
            }
        })



    }


}