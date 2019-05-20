package com.stu.sample.user.PJC.study_rx

import android.util.LogPrinter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject


class SubjectExample {
    fun publishTest () {
        
        /**
         * disposable은 Observer가 Observable에서 구독할 때 생성되는 객체. 
         * Observable에서 만드는 이벤트 스트림과 이에 필요한 리소스를 관리.
         * 예) 구독해제: disposable을 통해 구독 해제한 경우 Observable에서 이를 감지하고 유지하던 리소스를 해제한다.
         * 
         */
        
        /* disposable 객체들을 관리하는 객체 */
        var disposable = CompositeDisposable()  


        var publishTest = PublishSubject.create<String>()

        /* Observer가 작업을 수행할 스레드를 지정한다. 아래에선 메인 스레드 */
        val subscribe = publishTest.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())    /* Observable이 작업을 수행 할 스레드를 지정한다.  */
                .subscribe({    /* onNext */
                    println(it)
                }, 
                {               /* onError */
                    it.printStackTrace()
                })              /* onComplete */ 
                {                    
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