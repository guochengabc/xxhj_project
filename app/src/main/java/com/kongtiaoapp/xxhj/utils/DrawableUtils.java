package com.kongtiaoapp.xxhj.utils;

import com.kongtiaoapp.xxhj.R;

import java.lang.reflect.Field;

/**
 * Created by Luoye on 2016-9-21.
 * 说明:设备图片匹配
 */
public class DrawableUtils {

    public static int getLeveImage(String type) {
        if (type==null){
            type="";
            return R.mipmap.default_head;
        }
        if (type.isEmpty() && type.length() == 0 ) {
            return R.mipmap.default_head;
        }

        try {
            Field field = R.mipmap.class.getField(type);
            return field.getInt(type);
        } catch (Exception e) {
            return R.mipmap.default_head;
        }
    }

}
