package com.stu.sample.cp1

import android.app.Application
import android.content.Context
import io.realm.Realm


class SApplication: Application (){

    companion object {
        fun getContext():Context {
            return instance
        }

        @JvmStatic
        lateinit var instance: SApplication
    }
    override fun onCreate() {
        super.onCreate()

        instance = this

        Realm.init(applicationContext)
    }
}