package com.freecreator.whiteforest.ui.views;

/**
 * Created by niko on 2018/3/11.
 */
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.widget.TextView;

import com.freecreator.whiteforest.R;
import com.freecreator.whiteforest.ui.utils.FontCustom;

/**
 * Created by niko on 2018/3/11.
 */


@SuppressLint("AppCompatCustomView")
public class FontTextView_Midline  extends TextView {

    public FontTextView_Midline(Context context, AttributeSet attrs) {
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

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // add a mid line on the text
        getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG);
        getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);
        getPaint().setAntiAlias(true);
    }
}
