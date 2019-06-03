package com.stu.sample.user.PJC.study_retrofit

/**
 * Created by jucherpark on 19/04/2019.
 */



class TestRetrofit {
    fun test () {
        var apiService = CustomServiceImpl().getService()
        apiService.enqueue(onSuccess = {

        }, onError=  {

        })
    }
}