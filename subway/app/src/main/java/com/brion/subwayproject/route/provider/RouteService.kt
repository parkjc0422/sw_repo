package com.brion.subwayproject.route.provider

import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RouteService {

    @POST("getRouteSearchResult.do")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    fun getRoute(@Body msg:RequestBody): Call<String>

}