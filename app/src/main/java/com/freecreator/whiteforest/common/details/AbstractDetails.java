package com.freecreator.whiteforest.common.details;

import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by JackYanx on 2018/3/15.
 * 详细信息抽象基类
 */

public abstract class AbstractDetails {
    protected static final String UNDEF = "UndefinedText";

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
    /**
     * 优化过的JSONObject put方法，防止前面的异常导致后面的键值对丢失,本类toString方法所使用
     * @param jsonObject
     * @param name
     * @param value
     */
    protected static void jsonPut(JSONObject jsonObject, String name, String value) {
        try {
            jsonObject.put(name, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }





    public abstract boolean isInvalid();
}
