package com.brion.subwayproject.ui.custom

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.widget.ProgressBar
import java.util.Collections.rotate



class VProgress :ProgressBar{
    constructor(context: Context, attrs: AttributeSet, defStyle: Int):super(context, attrs, defStyle){}

    constructor(context: Context, attrs: AttributeSet): super(context, attrs){}

    constructor(context: Context): super(context){}

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(h, w, oldh, oldw)
    }

    @Synchronized
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    @Synchronized
    override fun onDraw(canvas: Canvas) {
        canvas.rotate(-90.toFloat())
        canvas.translate(-height.toFloat(), 0.toFloat())
        super.onDraw(canvas)
    }

}