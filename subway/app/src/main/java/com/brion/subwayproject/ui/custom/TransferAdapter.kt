package com.brion.subwayproject.ui.custom

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.brion.subwayproject.R
import com.brion.subwayproject.route.model.RouteMapper
import com.brion.subwayproject.route.model.RouteMatching

class TransferAdapter(val context:Context) :RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    lateinit var routeMapper: RouteMapper
    val NoTransferInfo = 1
    val TransferInfo = 2
    val RouteInfo = 3

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        var inflater = LayoutInflater.from(p0.context)

        if(getItemViewType(p1) == NoTransferInfo) return NoTransferInfoVh(inflater.inflate(R.layout.cell_train_no_transfer,p0,false))
        else if(getItemViewType(p1) == RouteInfo) return RouteInfoVh(inflater.inflate(R.layout.cell_transfer_info,p0,false))
        else return TransferInfoVh(inflater.inflate(R.layout.cell_train_view,p0,false))
    }

    override fun getItemCount(): Int {

        if(routeMapper.trasferRoute.sTransfer.transferList.size == 0) {
            return 1
        } else {
            return routeMapper.trasferRoute.sTransfer.transferList.size*2 + 1
        }
    }

    override fun getItemViewType(position: Int): Int {
        if(routeMapper.trasferRoute.sTransfer.transferList.isEmpty()) {
            return NoTransferInfo
        } else {
            if(position %2 == 0) return TransferInfo
            else return RouteInfo
        }
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        var matcher = RouteMatching(context)
        matcher.loadInfo(routeMapper)
        if(getItemViewType(p1) == NoTransferInfo) {
            var view = p0 as NoTransferInfoVh
            view.from.text = matcher.paths[0].from
            view.to.text = matcher.paths[0].to
            view.lineColorImage.setBackgroundColor(matcher.paths[0].lineColor)
            view.from.strokeColor = matcher.paths[0].lineColor
            view.to.strokeColor = matcher.paths[0].lineColor
        } else if(getItemViewType(p1) == RouteInfo) {
            var view = p0 as RouteInfoVh
            var pos = (p1 - 1)/2
            view.runTime.text = matcher.transfers[pos].walkingTime
            view.fastTransferNum.text = matcher.transfers[pos].fastTransferLoc
        } else if(getItemViewType(p1) == TransferInfo) {
            var view = p0 as TransferInfoVh
            var pos = p1 / 2
            view.from.text = matcher.paths[pos].from
            view.to.text = matcher.paths[pos].to
            view.lineColorImage.setBackgroundColor(matcher.paths[pos].lineColor)
            view.from.strokeColor = matcher.paths[pos].lineColor
            view.to.strokeColor = matcher.paths[pos].lineColor
        }
    }

    class NoTransferInfoVh(val view: View):RecyclerView.ViewHolder(view) {
        var lineColorImage:ImageView
        var from:CircularTextView
        var to:CircularTextView
        init {
            lineColorImage = view.findViewById(R.id.lineColor)
            from = view.findViewById(R.id.fromRoute)
            to = view.findViewById(R.id.toRoute)

            from.setStrokeWidth(3)
            to.setStrokeWidth(3)
        }
    }

    class TransferInfoVh(val view:View) :RecyclerView.ViewHolder(view){
        var lineColorImage:ImageView
        var from:CircularTextView
        var to:CircularTextView
        init {
            lineColorImage = view.findViewById(R.id.lineColor)
            from = view.findViewById(R.id.fromRoute)
            to = view.findViewById(R.id.toRoute)

            from.setStrokeWidth(3)
            to.setStrokeWidth(3)
        }
    }


    class RouteInfoVh(val view: View) :RecyclerView.ViewHolder(view) {
        var fastTransferNum:TextView
        var runTime:TextView
        init {
            fastTransferNum = view.findViewById(R.id.fastTransferNum)
            runTime = view.findViewById(R.id.runTime)
        }
    }
}