package com.freecreator.whiteforest.ui.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by niko on 2018/3/10.
 */

public class AdjustSize {


    /**
     * Remember this function must be called after the activity call the method"onWindowFocusChanged"
     * Or the size this function return will not be correct
     * @param view
     * @return return the size(in px instead of dp) of the view
     */
    public static Size getViewSize(View view){
/*
        Size size = new Size();
        size.width = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        size.height = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        view.measure(size.width, size.height);

        size.width = view.getMeasuredWidth(); // 获取宽度
        size.height = view.getMeasuredHeight(); // 获取高度
*/
        // 注意 这种获取 宽高的方式 只有当activity执行了 onWindowFocusChanged 之后才能准确获得.

        Size size = new Size();
        size.width = view.getWidth(); // 获取宽度
        size.height = view.getHeight(); // 获取高度

        return size;
    }

    public static Size getScreenSize(Context context){
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();

        Size size  = new Size();
        size.width = dm.widthPixels;
        size.height = dm.heightPixels;

        return size;
    }

    public static Size getMeasuredSize(View view){

        Size size = new Size();
        size.width = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        size.height = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        view.measure(size.width, size.height);

        size.width = view.getMeasuredWidth(); // 获取宽度
        size.height = view.getMeasuredHeight(); // 获取高度

        return size;
    }

    public static Size getImageWidthHeight(Context context, int resId){
        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resId, options); // 此时返回的bitmap为null

        Size size = new Size();
        size.width = options.outWidth;
        size.height = options.outHeight;
        return size;
    }

    /**
     *
     * @param v the view need to be adjusted size
     * @param refSize a reference size for adjusting the view size
     */
    public static void adjustHeight(View v, Size refSize){
        Size currentSize = getViewSize(v);

        ViewGroup.LayoutParams lp;
        lp = v.getLayoutParams();
        float h  = (float)currentSize.width * (float)refSize.height / (float)refSize.width;
        lp.height = (int)h;
        v.setLayoutParams(lp);
    }

    /**
     *
     * @param v the view need to be adjusted size
     * @param refSize a reference size for adjusting the view size
     */
    public static void adjustWidth(View v, Size refSize){
        Size currentSize = getViewSize(v);

        ViewGroup.LayoutParams lp;
        lp = v.getLayoutParams();
        float w = (float)currentSize.height * (float)refSize.width / (float)refSize.height;
        lp.width = (int)w;
        v.setLayoutParams(lp);
    }


    public static void adjustFontSize(Context context, TextView v){
        DisplayMetrics dm = new DisplayMetrics();
        dm = context.getResources().getDisplayMetrics();

        float density  = dm.density;        // 屏幕密度（像素比例：0.75/1.0/1.5/2.0）
        int densityDPI = dm.densityDpi;     // 屏幕密度（每寸像素：120/160/240/320）
        float xdpi = dm.xdpi;
        float ydpi = dm.ydpi;
    }

}
