package com.kongtiaoapp.xxhj.utils;

/**
 * Created by xxhj_g on 2017/4/5.
 */
public class PatternUtils {
    //验证全是数字并且开头可以有+-，以及带小数点的正则表达式
    public static  String getDigital() {
        return "^[-+]?[0-9]+(\\.[0-9]+)?$";
    } //验证第一位是英文的正则表达式
    public static  String first_english() {
        return"^[a-zA-Z]{1}.+";
    }

}
