package com.freecreator.whiteforest.common.cache;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by JackYanx on 2018/3/18.
 * 缓存基类
 */

public abstract class AbstractCache {

    /*
     * 应用内部全局SharedPreferences变量
     * 以保证存取Cache线程安全
     */
    protected static SharedPreferences sharedPreferences;

    /*
    * 应用包名
    */
    protected static final String appPackageName = "com.freecreator.whiteforest";
    protected Context context;

    public AbstractCache(Context context) {

        if (sharedPreferences == null) {
            /*为AbstractCache类加锁*/
            synchronized (AbstractCache.class) {
                if (sharedPreferences == null) {
                    sharedPreferences = context.getSharedPreferences(appPackageName, Context.MODE_PRIVATE);
                }
            }
        }
        if (this.context == null) this.context = context;

    }

    protected Context getContext() {
        return this.context;
    }

    /*
     * 清除子类模块缓存的内容，此处注意不应直接调用 editor.clear()方法将全局缓存删除
     */
    public abstract void clear();


}