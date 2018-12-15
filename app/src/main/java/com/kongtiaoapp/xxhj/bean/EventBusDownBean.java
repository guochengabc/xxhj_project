package com.kongtiaoapp.xxhj.bean;

/**
 * Created by G_XXHJ on 2017/12/2.
 */

public class EventBusDownBean {
    private String position;//点击的位置
    private String isMonth;//是否是月份
    private double paintCount;
    private String Code;
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getIsMonth() {
        return isMonth;
    }

    public void setIsMonth(String isMonth) {
        this.isMonth = isMonth;
    }

    public double getPaintCount() {
        return paintCount;
    }

    public void setPaintCount(double paintCount) {
        this.paintCount = paintCount;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }
}
