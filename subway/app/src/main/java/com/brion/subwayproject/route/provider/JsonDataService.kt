package com.brion.subwayproject.route.provider

import com.brion.subwayproject.route.model.Items
import com.brion.subwayproject.route.model.JsonInfoModel
import com.brion.subwayproject.ui.custom.TrainDistributionAdapter
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JsonDataService {

    @GET("v0/b/swcongest.appspot.com/o/")
    //@Headers
    fun getJsonItems(): Call<Items>

    @GET("v0/b/swcongest.appspot.com/o/{target}")
    fun getJsonInfo(@Path("target") targetJson : String) : Call<JsonInfoModel>


    @GET("v0/b/swcongest.appspot.com/o/{target}?")
    fun getJsonDataMap(@Path("target") targetJson : String,
                    @Query ("alt") alt : String,
                    @Query ("token") token : String) : Call<Map<String, Array<Int>>>


}