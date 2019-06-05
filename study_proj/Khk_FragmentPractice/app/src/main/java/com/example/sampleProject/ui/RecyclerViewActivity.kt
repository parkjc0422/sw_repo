package com.example.sampleProject.ui

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.example.sampleProject.R
import com.example.sampleProject.ui.adapter.CustomAdapter
import kotlinx.android.synthetic.main.activity_recycler_view.*

class RecyclerViewActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init(){

        setContentView(R.layout.activity_recycler_view)

        // Activity로 부터 데이터를 받음.
        var stringList: List<String> = intent.getStringArrayListExtra(R.string.VAL_INTENT_KEY_STRINGLIST.toString())

        val adapter = CustomAdapter(this, stringList)
        mRecyclerView.adapter =  adapter

        val linearLayoutManager = LinearLayoutManager(this)
        mRecyclerView.layoutManager = linearLayoutManager
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }
}