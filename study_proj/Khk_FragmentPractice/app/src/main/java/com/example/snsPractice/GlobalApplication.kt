package com.example.snsPractice

import android.app.Application
import com.kakao.auth.KakaoSDK

class GlobalApplication : Application() {

    /**
     * global Application 을 static하게 사용하기 위해 companion 사용
     * App 실행 시, App의 다른 구성 요소들 보다 먼저 인스턴스화 됨.
     * AndroidManifest.xml Application android:name에 선언.
     *
     * */
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