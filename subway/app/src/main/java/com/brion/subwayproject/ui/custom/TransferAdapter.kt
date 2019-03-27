package com.brion.subwayproject.ui.custom

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.brion.subwayproject.R
import com.brion.subwayproject.route.model.RouteMapper

class TransferAdapter(val context:Context) :RecyclerView.Adapter<TransferAdapter.TViewHolder>(){
    lateinit var model:RouteMapper
    var transferInfo:Array<TransferLine>
    init {
        transferInfo = emptyArray()
    }

    val TRAIN_INFO= 1
    val TRANSFER_INFO= 2

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TViewHolder {
        var layoutInflater = LayoutInflater.from(context)

        return TViewHolder(layoutInflater.inflate(R.layout.cell_train_view,p0, false))
    }

    override fun getItemCount(): Int {
        when (model.routeType) {
            RouteMapper.RouteType.NextNode -> {
                return 1
            }
            RouteMapper.RouteType.NoTransferNode -> {
                return 1
            }
            RouteMapper.RouteType.TransferNode-> {
                return model.route.transferNum.toInt()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if(model.routeType != RouteMapper.RouteType.TransferNode) return TRAIN_INFO
        else {
            if(position%2 ==0)
                return TRAIN_INFO
            else
                return TRANSFER_INFO
        }
    }

    override fun onBindViewHolder(p0: TViewHolder, p1: Int) {

    }

    open class TViewHolder(view:View):RecyclerView.ViewHolder (view)

    class TransferInfoVH(val view:View):TViewHolder (view){
        var fastTransferInfo:TextView
        var runTime:TextView
        init {
            fastTransferInfo = view.findViewById(R.id.fastTransferNum)
            runTime = view.findViewById(R.id.runTime)
        }
    }

    class TrainInfoVH(val view:View):TViewHolder(view) {
        
    }


    inner class TransferLine {

    }
}