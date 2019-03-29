package com.brion.subwayproject.route.parser

import android.util.Log
import com.brion.subwayproject.route.model.*
import com.brion.subwayproject.route.model.TransferRoute
import com.google.gson.Gson
import fr.arnaudguyon.xmltojsonlib.XmlToJson
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Exception


class RouteXmlParser (val originMessage:String) {

    fun parseModel() :RouteMapper{
        var routeMapper = RouteMapper()
        var xmlToJson = XmlToJson.Builder(originMessage).build()
        var jsonObject = xmlToJson.toJson()


        var obj = jsonObject?.getJSONObject("route")
        var output = TransferRoute()
        var result = Route()
        var gson = Gson()
        try {
            result = gson.fromJson(obj.toString(), Route::class.java)

            obj?.let {
                /**
                 * @SerializedName("sPath")
                 * @SerializedName("sTransfer")
                 */
                var pathList = (it["sPath"] as JSONObject)["pathList"]
                var transfer = it["sTransfer"]

                Log.d("asd","kmkmk")
                if(transfer is String) {
                    if(pathList is JSONArray) {
                        /**
                         * Path info get
                         */
                        var list = it["sPath"] as JSONObject
                        var cList = gson.fromJson(list.toString(), PathInfo::class.java)
                        output.sPath = cList
                    } else {
                        /**
                         * make path info
                         */
                        var cPath= gson.fromJson(pathList.toString(), Path::class.java)
                        var pathInfo = PathInfo()
                        pathInfo.pathList = listOf(cPath)
                        output.sPath = pathInfo
                    }

                    output.sTransfer = TransferInfo()
                    output.sTransfer.transferList = emptyArray()
                } else {
                    output = TransferRoute()
                    var temp = (transfer as JSONObject)["transferList"]
                    if(temp is JSONArray) {
                        var list = gson.fromJson(transfer.toString(), TransferInfo::class.java)
                        output.sTransfer = list
                    } else {
                        var item = gson.fromJson(temp.toString(), Transfer::class.java)
                        var transferInfo = TransferInfo()
                        transferInfo.transferList = arrayOf(item)
                        output.sTransfer = transferInfo

                    }
                    if(pathList is JSONArray) {
                        /**
                         * Path info get
                         */
                        var list = it["sPath"] as JSONObject
                        var cList = gson.fromJson(list.toString(), PathInfo::class.java)
                        output.sPath = cList
                    } else {
                        /**
                         * make path info
                         */
                        var cPath= gson.fromJson(pathList.toString(), Path::class.java)
                        var pathInfo = PathInfo()
                        pathInfo.pathList = listOf(cPath)
                        output.sPath = pathInfo
                    }
                }

            }
        } catch (exp:Exception) {
            exp.printStackTrace()
        }

        routeMapper.trasferRoute = output
        routeMapper.route = result
        return routeMapper
    }
}

