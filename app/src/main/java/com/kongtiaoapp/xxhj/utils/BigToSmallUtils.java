package com.kongtiaoapp.xxhj.utils;

/**
 * Created by xxhj_g on 2017/9/29.
 */

public class BigToSmallUtils {
    /**
     * 命名转换--帕斯卡→下划线   大写转换_和小写
     *
     * @param param
     * @return
     */
    private static String  UNDERLINE="_";
    public static String pascalToUnderline(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                if (i > 0) {
                    sb.append(UNDERLINE);
                }
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
