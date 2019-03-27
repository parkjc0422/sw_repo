package com.brion.subwayproject.route.provider

import android.content.Context
import android.util.Log
import com.brion.subwayproject.R
import com.google.gson.Gson
import com.google.gson.internal.LinkedTreeMap
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class ConfigProvider {
    val STATION_NAME = "station-nm"
    val DATA_UID = "data-uid"
    var mContext:Context
    var dataMap = hashMapOf<String, String> ()

    private constructor(context: Context) {
        this.mContext = context
        loadInfo()
    }
    companion object {
        private var instance :ConfigProvider? = null
        fun getInstance(context: Context) : ConfigProvider{
            if(instance == null) instance = ConfigProvider(context)
            return instance as ConfigProvider
        }
    }

    fun loadInfo() {
        Thread(Runnable {
            val rd = BufferedReader(InputStreamReader(mContext.resources.openRawResource(R.raw.config)))
            val gson = Gson()
            dataMap = gson.fromJson(rd, HashMap::class.java) as HashMap<String, String>
        }).start()
    }

    fun getIdFromName(name:String):String? {
        if(dataMap.containsKey(name)) return dataMap[name]
        return ""
    }
}