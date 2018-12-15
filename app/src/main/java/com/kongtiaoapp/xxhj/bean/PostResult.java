package com.kongtiaoapp.xxhj.bean;

/**
 * Created by Luoye on 2016-7-20.
 * 说明: 请求拿到的json串
 */
public class PostResult extends RBResponse {
    private String resobj;

    public String getResult() {
        return resobj;
    }

    public void setResult(String resobj) {
        this.resobj = resobj;
    }
}
