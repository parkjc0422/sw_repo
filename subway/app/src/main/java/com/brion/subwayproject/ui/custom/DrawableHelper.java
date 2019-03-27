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

    /**
     *
     <layer-list xmlns:android="http://schemas.android.com/apk/res/android" >

     <item>
     <!-- create gradient you want to use with the angle you want to use -->
     <shape android:shape="rectangle" >
     <gradient
     android:angle="0"
     android:centerColor="@android:color/holo_blue_bright"
     android:endColor="@android:color/holo_red_light"
     android:startColor="@android:color/holo_green_light" />

     </shape>
     </item>
     <!-- create the stroke for top, left, bottom and right with the dp you want -->
     <item
     android:bottom="2dp"
     android:left="2dp"
     android:right="2dp"
     android:top="2dp">
     <shape android:shape="rectangle" >
     <!-- fill the inside in the color you want (could also be a gradient again if you want to, just change solid to gradient and enter angle, start, maybe center, and end color) -->
     <solid android:color="#fff" />
     </shape>
     </item>

     </layer-list>
     *
     *
     *
     * @param outerColor
     * @param innerColor
     * @param padding
     * @return
     */
    public static Drawable getCircleDrawableOutLine(@ColorInt int[] outerColor,
                                                    @ColorInt int innerColor,
                                                    int padding) {
        return getCircleDrawalbe(outerColor, innerColor, padding,padding,padding,padding);
    }

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
