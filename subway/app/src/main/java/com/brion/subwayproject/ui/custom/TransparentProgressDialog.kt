package com.brion.subwayproject.ui.custom

import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.LinearLayout
import android.view.Gravity
import android.app.Dialog
import android.content.Context
import android.widget.ImageView
import com.brion.subwayproject.utils.dpTOPx
import com.brion.subwayproject.utils.pxToDp


/**
 * Created by jucherpark on 31/03/2019.
 */
class TransparentProgressDialog(context: Context , resourceIdOfImage: Int, style: Int):
        Dialog(context , style) {

    private val iv: ImageView

    init {
        val wlmp = getWindow().getAttributes()
        wlmp.gravity = Gravity.CENTER_HORIZONTAL
        getWindow().setAttributes(wlmp)
        setTitle(null)
        setCancelable(false)
        setOnCancelListener(null)
        val layout = LinearLayout(context)
        layout.orientation = LinearLayout.VERTICAL
        val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT)

        val w= dpTOPx(context, 100.toFloat()).toInt()
            //pxToDp(context, 100.toFloat()).toInt()

        val h= dpTOPx(context, 100.toFloat()).toInt()
            //pxToDp(context, 100.toFloat()).toInt()
        val ivParams = LinearLayout.LayoutParams(w, h)


        iv = ImageView(context)
        iv.setImageResource(resourceIdOfImage)

//        iv.layoutParams = ivParams
//        iv.layoutParams.let {
//            it.width = w
//            it.height = h
//        }

        layout.addView(iv , ivParams)
        addContentView(layout , params)
    }

    override fun show() {
        super.show()
        val anim = RotateAnimation(0.0f , 360.0f , Animation.RELATIVE_TO_SELF , .5f , Animation.RELATIVE_TO_SELF , .5f)
        anim.interpolator = LinearInterpolator()
        anim.repeatCount = Animation.INFINITE
        anim.duration = 3000
        iv.setAnimation(anim)
        iv.startAnimation(anim)
    }
}