package com.example.rxPractice

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.example.fragmentpractice.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import org.reactivestreams.Subscriber
import java.util.*
import java.util.concurrent.TimeUnit


class RxActivity : AppCompatActivity(){

    private val disposables = CompositeDisposable()

    private lateinit var numText : EditText
    private lateinit var toastText : EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file)
        var timer: Timer? = Timer()

        numText = findViewById<EditText>(R.id.numText)
        toastText = findViewById<EditText>(R.id.toastText)



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
            })*/


        /*I
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
        })
        */
    }

    override fun onStop() {
        super.onStop()
        disposables.clear()
    }


}