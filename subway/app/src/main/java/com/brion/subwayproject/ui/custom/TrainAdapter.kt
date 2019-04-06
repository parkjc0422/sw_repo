package com.brion.subwayproject.ui.custom

import android.content.Context
import android.content.res.ColorStateList
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.brion.subwayproject.R


class TrainAdapter(val context:Context) :RecyclerView.Adapter<TrainAdapter.TrainVh>(){
    lateinit var item: TrainDistributionAdapter.TrainDistributionItem

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TrainVh {
        var layoutInflater = LayoutInflater.from(p0.context)
        return TrainVh(layoutInflater.inflate(R.layout.cell_train, p0, false))
    }

    override fun getItemCount(): Int = item.array.size

    override fun onBindViewHolder(p0: TrainVh, p1: Int) {
        item.array[p1].let {
            p0.info.text = "${p1+1}"
            p0.status.progress = it.value
            when (it) {
                TrainDistributionAdapter.TrainType.Lite -> {
//                    p0.status.progressTintList = ColorStateList.valueOf(Color.BLUE)
                    p0.status.progressTintList = ColorStateList.valueOf(context.resources.getColor(R.color.trafficLight))
                }
                TrainDistributionAdapter.TrainType.Congest -> {
//                    p0.status.progressTintList = ColorStateList.valueOf(Color.RED)
                    p0.status.progressTintList = ColorStateList.valueOf(context.resources.getColor(R.color.trafficCongest))
                }
                TrainDistributionAdapter.TrainType.Normal -> {
//                    p0.status.progressTintList = ColorStateList.valueOf(Color.YELLOW)
                    p0.status.progressTintList = ColorStateList.valueOf(context.resources.getColor(R.color.trafficNormal))
                }
            }
            p0.content.text = it.getString()
        }
    }

    class TrainVh: RecyclerView.ViewHolder{
        var status:VProgress
        var info:TextView
        var content:TextView
        constructor(view:View):super(view) {
            status = view.findViewById(R.id.trainTypeVisual)
            status.max = 5
            info = view.findViewById(R.id.trainNo)
            content = view.findViewById(R.id.trainState)
        }
    }
}