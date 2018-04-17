package com.freecreator.whiteforest.ui.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.freecreator.whiteforest.ui.utils.FontCustom;

/**
 * Created by niko on 2018/3/11.
 */


@SuppressLint("AppCompatCustomView")
public class FontTextView_Bold  extends TextView {

    public FontTextView_Bold(Context context, AttributeSet attrs, int defStyleAttr) {
        super( context,  attrs,  defStyleAttr);
    }

    @SuppressLint("NewApi")
    public FontTextView_Bold(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super( context,  attrs,  defStyleAttr,  defStyleRes);
    }

    public FontTextView_Bold(Context context){
        super(context);
    }

    public FontTextView_Bold(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    /**
     * 初始化字体
     * @param context
     */
    private void init(Context context) {
        //设置字体样式
        setTypeface(FontCustom.setFont(context, "NotoSansHans-Bold.otf"));
    }
}
