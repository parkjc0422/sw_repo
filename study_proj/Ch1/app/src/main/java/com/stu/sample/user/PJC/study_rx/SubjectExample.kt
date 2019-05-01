package com.stu.sample.user.PJC.study_rx

import android.util.LogPrinter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject


class SubjectExample {
    fun publishTest () {
        var disposable = CompositeDisposable()

        var publishTest = PublishSubject.create<String>()
        val subscribe = publishTest.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe({
                    println(it)
                }, {
                    it.printStackTrace()
                }) {
                    println("complete")
                }

        subscribe.addTo(disposable)


        publishTest.onNext("1")
        publishTest.onNext("2")
        publishTest.onNext("3")
        publishTest.onNext("4")
        publishTest.onNext("5")
    }
}