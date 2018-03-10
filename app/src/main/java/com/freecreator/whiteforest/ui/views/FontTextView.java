package com.freecreator.whiteforest.ui.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.freecreator.whiteforest.ui.utils.FontCustom;

/**
 * Created by niko on 2018/3/10.
 */

@SuppressLint("AppCompatCustomView")
public class FontTextView extends TextView {
    public FontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    /**
     * 初始化字体
     * @param context
     */
    private void init(Context context) {
        //设置字体样式
        setTypeface(FontCustom.setFont(context, "NotoSansHans-Black.otf"));
    }
}
