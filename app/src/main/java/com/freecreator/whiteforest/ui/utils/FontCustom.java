package com.freecreator.whiteforest.ui.utils;

import android.content.Context;
import android.graphics.Typeface;

import java.util.HashMap;

/**
 * Created by niko on 2018/3/10.
 */

public class FontCustom {

    private static HashMap<String, Typeface> fontCache = new HashMap<>();

    /**
     * 设置字体
     */
    public static Typeface setFont(Context context, String fontname)
    {
        Typeface typeface = fontCache.get(fontname);
        if (typeface == null) {
            try {
                typeface = Typeface.createFromAsset(context.getAssets(), fontname);
            } catch (Exception e) {
                return null;
            }
            fontCache.put(fontname, typeface);
        }
        return typeface;
    }

}