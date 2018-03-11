package com.freecreator.whiteforest.common;

/**
 * Created by niko on 2017/8/27.
 */

// 开发时，代码中为了方便调试 常常会夹杂一些调试所用的代码，而在上传正式代码前，总要去删除调试所用的代码
    // 会非常麻烦，浪费时间，所以在这里设置一个调试总开关
    // 调试所用的代码可以写死在程序中，然后启用这些调试的代码的开关就是下面的  Debug._Debug == true;
    // 实例用法
    // if (Debug._Debug) {
//              Toast.makeText(this, "调试信息", Toast.LENGTH_LONG).show();
//      }

    // 发布前 记得将下面的总开关 设置为 public final boolean _Debug = false;


public class Debug {
    public final static boolean _Debug = false;
    public final static boolean _debug2 = false;
}
