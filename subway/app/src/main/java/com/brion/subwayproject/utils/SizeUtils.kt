package com.brion.subwayproject.utils

import android.content.Context
import android.util.TypedValue
import android.util.DisplayMetrics







fun dpTOPx(context:Context, dip:Float) :Float{
    val r = context.resources
    val px = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dip,
        r.getDisplayMetrics()
    )

    return px
}

fun pxToDp (context:Context, px:Float) = px / (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)