package com.freecreator.whiteforest.utils;

import java.util.regex.Pattern;

/**
 * Created by ASUS on 2018/3/27.
 */

public class RegexUtils {
    /**
     * 正则表达式：匹配用户名
     * 第一位必须是英文字母,可由英文字母数字下划线构成,长度5~21位
     */
    public static final String REGEX_USERNAME = "^[a-zA-Z]\\w{4,20}$";

    /**
     * 正则表达式：匹配有无特殊字符
     */
    //public static final String REGEX_NOSPECCHAR="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
    public static final String REGEX_NOSPECCHAR="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~@#%……&*——+|{}]";

    /**
     * 正则表达式：匹配密码
     */
    public static final String REGEX_PASSWORD = "^[a-zA-Z0-9+]{6,20}$";

    /**
     * 正则表达式：匹配手机号
     */
    public static final String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";

    /**
     * 正则表达式：匹配邮箱
     */
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    /**
     * 正则表达式：匹配汉字
     */
    public static final String REGEX_CHINESE = "^[\u4e00-\u9fa5],{0,}$";

    /**
     * 正则表达式：匹配身份证
     */
    public static final String REGEX_ID_CARD = "(^\\d{18}$)|(^\\d{15}$)";

    /**
     * 正则表达式：匹配IP地址
     */
    public static final String REGEX_IP_ADDR = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";


    /**
     * 校验用户名
     *
     * @param username
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isLegalUsername(String username) {
        return Pattern.matches(REGEX_USERNAME, username);
    }

    /**
     * 校验有无特殊字符
     *
     * @param str
     * @return 无特殊字符返回true，否则返回false
     */
    public static boolean isNoSpecChar(String str) {
        return !Pattern.matches(REGEX_NOSPECCHAR, str);
    }

    /**
     * 校验密码
     *
     * @param password
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isLegalPassword(String password) {
        return Pattern.matches(REGEX_PASSWORD, password);
    }

    /**
     * 校验手机号
     *
     * @param mobile
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isLegalPhoneNumber(String mobile) {
        return Pattern.matches(REGEX_MOBILE, mobile);
    }

    /**
     * 校验邮箱
     *
     * @param email
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isLegalEmailAdderss(String email) {
        return Pattern.matches(REGEX_EMAIL, email);
    }

    /**
     * 校验汉字
     *
     * @param chinese
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isChinese(String chinese) {
        return Pattern.matches(REGEX_CHINESE, chinese);
    }

    /**
     * 校验身份证
     *
     * @param idCard
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isLegalIDCardNumber(String idCard) {
        return Pattern.matches(REGEX_ID_CARD, idCard);
    }


    /**
     * 校验IP地址
     *
     * @param ipAddr
     * @return
     */
    public static boolean isLegalIPAddr(String ipAddr) {
        return Pattern.matches(REGEX_IP_ADDR, ipAddr);
    }
}
