package com.brion.subwayproject.ui.custom;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.util.Log;
import android.view.View;

public class DrawableHelper {

    public static void setRoundBackground(View view, Drawable drawable) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    public static Drawable getCornerDrawable(float topLeft,
                                             float topRight,
                                             float bottomLeft,
                                             float bottomRight,
                                             @ColorInt int color) {

        float[] outerR = new float[8];
        outerR[0] = topLeft;
        outerR[1] = topLeft;
        outerR[2] = topRight;
        outerR[3] = topRight;
        outerR[4] = bottomRight;
        outerR[5] = bottomRight;
        outerR[6] = bottomLeft;
        outerR[7] = bottomLeft;

        ShapeDrawable drawable = new ShapeDrawable();
        drawable.setShape(new RoundRectShape(outerR, null, null));
        drawable.getPaint().setColor(color);

        return drawable;
    }

    public static Drawable getCirleDrawable(@ColorInt int outColor,
                                            @ColorInt int innerColor,
                                            int width) {
        GradientDrawable gD = new GradientDrawable();
        gD.setColor(innerColor);
        gD.setShape(GradientDrawable.OVAL);
        gD.setStroke(outColor, width);

        return new LayerDrawable(new Drawable[]{gD});
    }

    public static Drawable getCircleDrawableOutLine(@ColorInt int[] outerColor,
                                                    @ColorInt int innerColor,
                                                    int padding) {
        return getCircleDrawalbe(outerColor, innerColor, padding,padding,padding,padding);
    }

//    public static Drawable getCircleDrawer (@ColorInt int color,
//                                            int width) {
//    }

    public static Drawable getCircleDrawalbe(@ColorInt int[] outerColor,
                                             @ColorInt int innerColor,
                                             int l, int t, int r, int b) {
        /**
         * Gradient outer
         */
        GradientDrawable outerGd = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, outerColor);
        outerGd.setShape(GradientDrawable.OVAL);


        /**
         * inner field
         */
        GradientDrawable innerGd = new GradientDrawable();
        innerGd.setShape(GradientDrawable.OVAL);
        innerGd.setColor(innerColor);
        Rect padding = new Rect(l, t, r, b);
        if(!innerGd.getPadding(padding)) {
            Log.d("DrawableHelper","__  padding Setting exception");
        }

        return new LayerDrawable(new Drawable[]{outerGd, innerGd});
    }
}
