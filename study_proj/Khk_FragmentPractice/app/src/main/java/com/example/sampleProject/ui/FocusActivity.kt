package com.example.sampleProject.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.example.sampleProject.R
import com.example.sampleProject.ui.adapter.FocusAdapter
import com.example.sampleProject.ui.adapter.MultiTypeAdapter
import kotlinx.android.synthetic.main.activity_recycler_view.*

class FocusActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_recycler_view)

        // Activity로 부터 데이터를 받음.
        var stringList: List<String> = listOf("1","2","3","4","5","6","7","8","9","10")

        val adapter = MultiTypeAdapter(this, stringList)

        mRecyclerView.adapter =  adapter

        val linearLayoutManager = LinearLayoutManager(this)
        mRecyclerView.layoutManager = linearLayoutManager
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

    }

    override fun onDestroy() {
        super.onDestroy()
    }

}