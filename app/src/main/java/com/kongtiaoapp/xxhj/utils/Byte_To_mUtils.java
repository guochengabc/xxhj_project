package com.kongtiaoapp.xxhj.utils;

/**
 * Created by xxhj_g on 2016/12/8.
 */
public class Byte_To_mUtils {
    public static String Byte2M(long size) {
        String s = "";
        if (size > 1024 * 1024) {
            s = String.format("%.2f", (double) size / 1000000) + "M";
        } else
            s = String.format("%.2f", (double) size / (1024)) + "KB";
        return s;
    }

    public static int Byte2s(long size) {
        int s=0;
        if (size > 1024 * 1024) {
            s = Integer.parseInt(String.format("%.2f", (double) size / 1000000));
        } else
            s =  Integer.parseInt(String.format("%.2f", (double) size / (1024)));
        return s;
    }
}
