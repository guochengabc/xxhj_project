package com.kongtiaoapp.xxhj.bean;

/**
 * Created by Luoye on 2016-9-14.
 * 说明:
 */
public class Result extends RBResponse {
    public String getResobj() {
        return resobj.toString();
    }

    public void setResobj(Object resobj) {
        this.resobj = resobj;
    }

    private Object resobj;


}
