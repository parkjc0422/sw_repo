package com.stu.sample.user.PJC.study_retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CustomServiceImpl {
    fun getService(): CustomService {
        val apiService = Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(GsonConverterFactory.create())
                .build()


        return apiService.create(CustomService::class.java)

    }
}