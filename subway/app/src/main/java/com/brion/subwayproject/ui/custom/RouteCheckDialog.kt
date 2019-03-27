package com.brion.subwayproject.ui.custom

import android.content.Context
import com.brion.subwayproject.R
import com.brion.subwayproject.ui.common.CommonDialog
import kotlinx.android.synthetic.main.dialog_route_check.*

/**
 * Created by jucherpark on 25/03/2019.
 */


class RouteCheckDialog(context:Context, val subwayName:String,
                       val clickStart:(String)->Unit,
                       val clickInfo:(String)->Unit,
                       val clickEnd:(String)->Unit) :CommonDialog(context){

    override fun layout(): Int = R.layout.dialog_route_check

    override fun bindView() {
        alertSubwayName.text = subwayName
        alertSubwayStart.setOnClickListener { clickStart(subwayName) }
        alertSubwayEnd.setOnClickListener { clickEnd(subwayName) }
        alertSubwayInfo.setOnClickListener { clickInfo(subwayName) }
    }
}