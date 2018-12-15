package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by Luoye on 2016-8-8.
 * 说明:字典信息
 */
public class Dict extends RBResponse {

    /**
     * Value : 冷机
     * Code : unit
     */

    private List<DictData> resobj;

    public List<DictData> getResobj() {
        return resobj;
    }

    public void setResobj(List<DictData> resobj) {
        this.resobj = resobj;
    }

    public static class DictData {
        private String Value;
        private String Code;

        public String getValue() {
            return Value;
        }

        public void setValue(String Value) {
            this.Value = Value;
        }

        public String getCode() {
            return Code;
        }

        public void setCode(String Code) {
            this.Code = Code;
        }
    }

}
