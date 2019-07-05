package com.brion.subwayproject.route.provider

import retrofit2.Retrofit
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class JsonDataAPI {
    private var okHttpClient
            = OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build()
    private var retrofit
            = Retrofit.Builder().baseUrl("https://firebasestorage.googleapis.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()

    fun getJsonDataService () : JsonDataService = retrofit.create(JsonDataService::class.java)

}