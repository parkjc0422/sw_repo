package com.stu.sample.user.PJC.study_retrofit

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by jucherpark on 19/04/2019.
 */



class TestRetrofit {
    fun test () {
        var apiService = CustomServiceImpl().getService() as Call<ResponseBody>
        apiService.enqueue( object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }

}