package com.stu.sample.user.KHG

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.widget.Button
import com.stu.sample.R
import khttp.post

class NetworkTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_network_test)


        val openButton = findViewById<Button>(R.id.button)
        openButton.setOnClickListener{ openFragment() }

        // todo : refactoring
//        button.setOnClickListener { openFragment() }
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
            supportFragmentManager.beginTransaction().add(R.id.fragment, NetworkTestBlankFragment()).commit()
        } else {
            supportFragmentManager.popBackStack()
            supportFragmentManager.beginTransaction().replace(R.id.fragment, NetworkTestBlankFragment())

        }
    }

}
