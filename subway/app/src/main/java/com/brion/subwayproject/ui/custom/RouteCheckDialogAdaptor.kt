package com.brion.subwayproject.ui.custom

/**
 * Created by jucherpark on 25/03/2019.
 */

class RouteCheckDialogAdaptor {
    lateinit var subwayName:String
    lateinit var didClickLoad:(String)->Unit
    lateinit var didClickCancel:()->Unit
    lateinit var didClickInfo:(String)->Unit
}