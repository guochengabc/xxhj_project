package com.kongtiaoapp.xxhj.utils;

import com.kongtiaoapp.xxhj.App;

/**
 * 获取资源的工具类
 */
public class CommonUtil {


    /**
     * 获取字符串资源
     *
     * @param resId
     * @return
     */
    public static String getString(int resId) {
        return App.application.getResources().getString(resId);
    }

    public static String[] getStringArray(int resId) {
        return App.application.getResources().getStringArray(resId);
    }



    public static int getColor(int resId) {
        return App.application.getResources().getColor(resId);
    }

    /**
     * 从dimens.xml中获取dp资源，并且会自动将dp转为px
     *
     * @param resId
     * @return
     */

}
