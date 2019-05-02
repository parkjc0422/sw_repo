package com.stu.sample.user.PJC.study_retrofit

import com.google.gson.annotations.SerializedName
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface RxRetrofitService {
    @GET("test")
    fun getInfo(): Observable<Info>
}

class Info{
    @SerializedName("name")
    var name: String? = null
}

class RxRetrofit {
    val baseUrl = ""
    fun getServiceImpl(): RxRetrofitService {
        val retrofit = Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        return retrofit.create(RxRetrofitService::class.java)
    }
}


fun test () {
    val service = RxRetrofit().getServiceImpl()
    val disposable = CompositeDisposable()

    val observer = service.getInfo()
            .observeOn(Schedulers.newThread())
            .subscribeOn(Schedulers.newThread())
            .subscribe({

    }) {

    }

    disposable.add(observer)

}