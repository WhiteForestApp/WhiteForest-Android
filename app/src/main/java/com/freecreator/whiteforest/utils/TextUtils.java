package com.freecreator.whiteforest.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ASUS on 2018/3/27.
 */

public class TextUtils {
    public static boolean isPhoneNumber(String str){

        return true;
    }
    /*判断是否包含特殊字符*/
    public static boolean isLegalText(String str){
        String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p= Pattern.compile(regEx);
        Matcher m=p.matcher(str);
        return !m.find();
    }
    public static boolean isLegalPassword(String str){

        return true;
    }
    //public static boolean isLegal
}
