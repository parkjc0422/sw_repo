package com.brion.subwayproject.ui.custom

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet
import com.brion.subwayproject.R

/**
 * Created by jucherpark on 26/03/2019.
 */


class CircleTextView : AppCompatTextView {
    /**
    <declare-styleable name="CircleTextViewCorner">
    <attr name="bgColor" format="color"/>
    <attr name="startBgCorner" format="color"/>
    <attr name="endBgCorner" format="color"/>
    <attr name="strokeWidth" format="integer"/>
    </declare-styleable>
     */
    var tvBgColor: Int = Color.TRANSPARENT
    var tvStartBgCorner: Int = Color.TRANSPARENT
    var tvEndBgCorner: Int = Color.TRANSPARENT
    var tvStrokeWidth: Int = 0

    constructor(context:Context):super(context)

    constructor(context:Context, attr:AttributeSet) :super(context, attr) {
        extractAttribute(context, attr)
        setViewBackground()
    }

    private fun extractAttribute(context: Context, attrs: AttributeSet) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.CircleTextViewCorner, 0, 0)
        try {
            tvBgColor = ta.getColor(R.styleable.CircleTextViewCorner_bgColor, Color.TRANSPARENT)
            tvStartBgCorner = ta.getColor(R.styleable.CircleTextViewCorner_startBgCorner, Color.TRANSPARENT)
            tvEndBgCorner = ta.getColor(R.styleable.CircleTextViewCorner_startBgCorner, Color.TRANSPARENT)
            tvStrokeWidth = ta.getInteger(R.styleable.CircleTextViewCorner_strokeWidth, 0)
        } finally {
            ta.recycle()
        }
    }


    private fun setViewBackground() {
        val drawable: Drawable = DrawableHelper.getCirleDrawable(tvStartBgCorner, tvBgColor, tvStrokeWidth)
        DrawableHelper.setRoundBackground(this, drawable)
    }
}