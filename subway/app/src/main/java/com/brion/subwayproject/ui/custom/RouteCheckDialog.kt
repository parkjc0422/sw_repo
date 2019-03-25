package com.brion.subwayproject.ui.custom

import android.content.Context
import com.brion.subwayproject.R
import com.brion.subwayproject.ui.common.CommonDialog

/**
 * Created by jucherpark on 25/03/2019.
 */


class RouteCheckDialog(context:Context) :CommonDialog(context){
    var adapter:RouteCheckDialogAdaptor? = null

    override fun layout(): Int = R.layout.dialog_route_check

    override fun bindView() {
        adapter?.let {
            
        }

    }

}