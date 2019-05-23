package com.example.snsPractice

import android.app.Application
import android.os.Bundle
import com.kakao.auth.KakaoSDK

class GlobalApplication : Application() {

    companion object{
        private var instance: GlobalApplication ?= null
        fun getGlobalApplicationContext(): GlobalApplication {

            if (instance == null) {
                throw IllegalStateException("This Application does not inherit com.kakao.GlobalApplication")
            }
            return instance as GlobalApplication
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        // Kakao Sdk 초기화
        KakaoSDK.init(KaKaoSDKAdapter())
    }

    override fun onTerminate() {
        instance = null
        super.onTerminate()
    }
}