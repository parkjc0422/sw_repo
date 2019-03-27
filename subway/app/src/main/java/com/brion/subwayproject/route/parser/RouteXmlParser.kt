package com.brion.subwayproject.route.parser

import android.util.Log
import com.brion.subwayproject.route.model.*
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParseException
import com.google.gson.JsonSyntaxException
import fr.arnaudguyon.xmltojsonlib.JsonToXml
import fr.arnaudguyon.xmltojsonlib.XmlToJson
import org.json.JSONObject


class RouteXmlParser (val originMessage:String) {
    fun test () {
        var xmlToJson = XmlToJson.Builder(originMessage).build()
        var jsonObject = xmlToJson.toJson()
    }

    fun parseModel() :RouteMapper{
        var routeMapper : RouteMapper = RouteMapper()
        var xmlToJson = XmlToJson.Builder(originMessage).build()
        var jsonObject = xmlToJson.toJson()


        var obj = jsonObject?.getJSONObject("route")

        var result:Route = Route()
        var gson = Gson()
        try {
            /**
             * Transfer Node
             */
            result = gson.fromJson(obj.toString(), TransferRoute::class.java)
            routeMapper.routeType = RouteMapper.RouteType.TransferNode
        }catch (err: JsonSyntaxException) {

            try {
                /**
                 * there is no transfer
                 */
                result = gson.fromJson(obj.toString() , NoTranferRoute::class.java)
                routeMapper.routeType = RouteMapper.RouteType.NoTransferNode
            }catch (execpt: JsonSyntaxException ){
                /**
                 * Next Node
                 */
                routeMapper.routeType = RouteMapper.RouteType.NextNode
                result = gson.fromJson(obj.toString() , NextRoute::class.java)
            }
        }

        routeMapper.route = result
        return routeMapper
    }
}