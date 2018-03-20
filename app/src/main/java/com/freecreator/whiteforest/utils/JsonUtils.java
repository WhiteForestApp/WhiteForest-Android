package com.freecreator.whiteforest.utils;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by JackYanx on 2018/3/19.
 */

public class JsonUtils {

    /**
     *不会抛异常的String构造JSONObject对象方法
     * @param str
     * @return new JSONObject(str)
     */
    public static JSONObject optStrToJsonObject(String str){
        if(TextUtils.isEmpty(str)){
            return null;
        }
        try{
            return new JSONObject(str);
        }catch (JSONException e) {
            return null;
        }
    }

    /**
     * 优化过的JSONObject put方法，防止前面的异常导致后面的键值对丢失
     * @param jsonObject
     * @param name
     * @param value
     */
    public static void jsonPut(JSONObject jsonObject, String name, String value) {
        try {
            jsonObject.put(name, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 优化过的JSONObject putArray方法，防止前面的异常导致后面的键值对丢失
     * @param jsonObject
     * @param name
     * @param jsonArrayObject
     */
    public static void jsonArrayPut(JSONObject jsonObject, String name, JSONArray jsonArrayObject) {
        try {
            jsonObject.put(name, jsonArrayObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
