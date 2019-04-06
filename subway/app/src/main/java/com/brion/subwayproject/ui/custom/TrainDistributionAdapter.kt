package com.brion.subwayproject.ui.custom

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.brion.subwayproject.R
import android.support.v7.widget.LinearLayoutManager
import android.widget.TextView


class TrainDistributionAdapter(val context:Context) : RecyclerView.Adapter<TrainDistributionAdapter.TrainDistributionVH>(){
    var items:MutableList<TrainDistributionItem> = mutableListOf()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TrainDistributionVH {
        var inflater = LayoutInflater.from(p0.context)
        var view = inflater.inflate(R.layout.cell_train_distibution,p0 , false)
        return TrainDistributionVH(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(p0: TrainDistributionVH, p1: Int) {
        items[p1].let {
            var adapter = TrainAdapter(context)
            adapter.item = it
            p0.recyclerView.adapter = adapter
            p0.lineNumber.text = "${it.linenNumber}"
        }
    }

    class TrainDistributionVH : RecyclerView.ViewHolder {
        var lineNumber:TextView
        var recyclerView:RecyclerView
        constructor(view:View) :super(view) {
            val layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
            recyclerView = view.findViewById(R.id.trainInfo)
            recyclerView.layoutManager = layoutManager

            lineNumber = view.findViewById(R.id.lineNumber)
        }
    }


    class TrainDistributionItem {
        var linenNumber:String = ""
        var array: Array<TrainType> = emptyArray()
    }

    enum class TrainType {
        Lite(1), Normal(2), Congest(3);

        var value:Int
        constructor(value:Int) {
            this.value = value
        }

        fun getString():String {
            if(value == 1) return "원활"
            else if(value == 2) return "보통"
            else return "혼잡"
        }
    }
}