package com.freecreator.whiteforest.ui.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

/**
 * Created by niko on 2018/3/11.
 */

public class UIUtils {

    public static int px2dip(int pxValue)
    {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    public static int dip2px(float dipValue)
    {
        int w = Resources.getSystem().getDisplayMetrics().widthPixels;
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        int val =  (int)(dipValue * scale + 0.5f);
        return val;
    }

    public static int heightToPx(float dipValue){
        int h = Resources.getSystem().getDisplayMetrics().heightPixels;
        Size refSize = new Size(768, 1184);
        float val = dipValue * h / refSize.height;
        return (int)val;
    }

    public static Size getScreenSize(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        Size val = new Size(outMetrics.widthPixels, outMetrics.heightPixels);
        return val;
    }

    public static void setMargins (View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }
}
