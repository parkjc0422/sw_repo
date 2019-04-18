package com.stu.sample.user.PJC.study_retrofit

import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface CustomService{
    @POST("/testing")
    fun getParam(@Body request:RequestBody)
}