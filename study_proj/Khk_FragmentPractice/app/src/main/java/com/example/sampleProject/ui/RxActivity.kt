package com.example.sampleProject.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import com.example.fragmentpractice.R
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy

class RxActivity : AppCompatActivity(){

    private val disposables = CompositeDisposable()

    private lateinit var numText : EditText
    private lateinit var toastText : EditText

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file)

        numText  = findViewById(R.id.numText)

        val source = Observable.just("Alpha", "Beta", "Delta", "Epsilon")

        val lengthGroupObservable = source.groupBy { it.length }

        lengthGroupObservable.flatMapSingle { it.toList() }.subscribeBy( onNext = { println( it ) } )

/*
        Observable.create(Observable.OnSubscribe<String>{ subscriber : Subscriber<String> ->
            numText.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) = Unit

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int)
                        = subscriber.onNext(s.toString())
            })
        }).debounce(1000, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                    text :String ->
                toastText.text = "Output : " + text
            })


        numText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) = Unit

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                timer?.cancel()
            }

            override fun afterTextChanged(s: Editable) {
                timer = Timer()
                timer!!.schedule(object : TimerTask() {
                    override fun run() {
                        runOnUiThread {toastText .setText("Output : " + numText.text) }
                    }
                }, 1000)
            }
        })*/

    }

    override fun onStop() {
        super.onStop()
        disposables.clear()
    }
}