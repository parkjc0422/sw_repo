package com.example.fragmentpractice

import android.os.Bundle
import android.os.StrictMode
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import khttp.post

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val openButton = findViewById<Button>(R.id.button)
        openButton.setOnClickListener{ openFragment() }

    }

    private fun openFragment() {
        val getTopFragment = supportFragmentManager.findFragmentById(R.id.fragment)
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        try {
            val payload = mapOf("departureId" to "0425", "stopId" to "", "arrivalId" to "0321", "sKind" to "4", "sTime" to "", "weekTag" to "", "__encrypted" to "")
            val r = post(
                url = "http://www.seoulmetro.co.kr/kr/getRouteSearchResult.do", data = (payload)
            )
            println(r.text)
        }
        catch(ex: Exception){
            ex.printStackTrace()
        }

        if (getTopFragment == null) {
            supportFragmentManager.beginTransaction().add(R.id.fragment, FragmentA()).commit()
        } else {
            supportFragmentManager.popBackStack()
            supportFragmentManager.beginTransaction().replace(R.id.fragment, FragmentA())

        }
    }
}
