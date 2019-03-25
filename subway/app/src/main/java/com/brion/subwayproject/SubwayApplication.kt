package com.brion.subwayproject

import android.app.Application
import com.brion.subwayproject.route.provider.ConfigProvider

class SubwayApplication : Application (){
    override fun onCreate() {
        super.onCreate()
        ConfigProvider.getInstance(applicationContext)
    }
}