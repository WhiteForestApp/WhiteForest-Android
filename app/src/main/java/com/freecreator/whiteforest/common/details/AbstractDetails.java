package com.freecreator.whiteforest.common.details;

import android.text.TextUtils;

import org.json.JSONObject;

/**
 * Created by JackYanx on 2018/3/15.
 * 详细信息抽象基类
 */

public abstract class AbstractDetails {
    public static final String UNDEF = "";
    public static final int ERRSTATUS = 0;

    /*验证字符串是否为空或者NULL,Null,null*/
    protected boolean isNull(String str) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        if (str.equalsIgnoreCase("null")) {
            return true;
        }
        return false;
    }

    public abstract boolean isValid();

    public abstract void build(JSONObject obj);
}
