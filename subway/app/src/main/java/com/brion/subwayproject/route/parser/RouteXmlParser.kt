package com.brion.subwayproject.route.parser

import android.util.Log
import com.brion.subwayproject.route.model.Route
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParseException
import fr.arnaudguyon.xmltojsonlib.JsonToXml
import fr.arnaudguyon.xmltojsonlib.XmlToJson
import org.json.JSONObject


class RouteXmlParser (val originMessage:String) {
    fun test () {
        var xmlToJson = XmlToJson.Builder(originMessage).build()
        var jsonObject = xmlToJson.toJson()

        Log.d("RouteXmlParse", "info : ${jsonObject.toString()}")
    }

    fun parseModel() :Route?{
        var xmlToJson = XmlToJson.Builder(originMessage).build()
        var jsonObject = xmlToJson.toJson()

        var jsonString = jsonObject.toString()

        var gson = Gson()
        var result = gson.fromJson(jsonString, Route::class.java)

        return result
    }
}