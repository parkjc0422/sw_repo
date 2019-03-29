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
            return routeMapper.trasferRoute.sTransfer.transferList.size*2 - 1
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
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class NoTransferInfoVh(val view: View):RecyclerView.ViewHolder(view) {
        var lineColorImage:ImageView
        var from:TextView
        var to:TextView
        init {
            lineColorImage = view.findViewById(R.id.lineColor)
            from = view.findViewById(R.id.fromRoute)
            to = view.findViewById(R.id.toRoute)
        }
    }

    class TransferInfoVh(val view:View) :RecyclerView.ViewHolder(view){
        var lineColorImage:ImageView
        var from:TextView
        var to:TextView
        init {
            lineColorImage = view.findViewById(R.id.lineColor)
            from = view.findViewById(R.id.fromRoute)
            to = view.findViewById(R.id.toRoute)
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