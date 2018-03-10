package com.freecreator.whiteforest.ui.utils;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by niko on 2018/3/10.
 */

public class FontCustom {

    //Typeface是字体，这里我们创建一个对象
    private static Typeface tf;

    /**
     * 设置字体
     */
    public static Typeface setFont(Context context, String font_name)
    {
        if (tf == null)
        {
            //给它设置你传入的自定义字体文件，再返回回来
            tf = Typeface.createFromAsset(context.getAssets(),font_name);
        }
        return tf;
    }
}