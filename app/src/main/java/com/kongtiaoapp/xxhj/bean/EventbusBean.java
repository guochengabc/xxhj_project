package com.kongtiaoapp.xxhj.bean;

/**
 * Created by xxhj_g on 2017/3/22.
 */
public class EventbusBean {
    private String position;//点击的位置
    private String isMonth;//是否是月份
    private double paintCount;
    private String Code;
    private String ChartSign;//0代表曲线  1代表柱状图
    private String dateStr;
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

    public String getChartSign() {
        return ChartSign;
    }

    public void setChartSign(String chartSign) {
        ChartSign = chartSign;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }
}
