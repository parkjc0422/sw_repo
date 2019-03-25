package com.brion.subwayproject.ui.common

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AlertDialog

/**
 * Created by jucherpark on 25/03/2019.
 */

open abstract class CommonDialog : Dialog{
    constructor(context:Context) :super(context){
        loadConstructor()
    }

    abstract fun layout():Int

    open fun loadConstructor() {}
    open fun bindView(){}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout())
        bindView()
    }
}