package com.stu.sample.user.PJC.study_retrofit

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface CustomService{
    @POST("/testing")
    fun getParam(@Body request:RequestBody): Call<ResponseBody>
}