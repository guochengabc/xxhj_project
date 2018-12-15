package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by xxhj_g on 2017/7/17.
 */

public class OverAllEvalueBean extends RBResponse {

    /**
     * code : 40000
     * resobj : ["1.总耗电811.31Kwh， 比昨天高537.3Kwh，今日温度28.0℃，比昨天低2.0℃，今日开机运行时长10.02小时，昨日开机运行时长23.93小时;","2.制冷系统SCOP为4.03（国标为：4.4），冷机COP为4.95（国标为：4.2），运行冷机的负载率太小,制冷系统运行状况差，冷机运行状况中;","3.制冷系统耗电输冷比为0.02，输送单位冷量冷冻水泵耗电中，其中冷冻水泵效率偏小，冷却水泵效率偏小；","4.冷却水供回水温差为4.11℃，冷却水量适中，其中冷却供水温度为34.39℃，冷却水温控制合理但仍有潜力可挖。冷冻水供回水温差为1.55℃ ，冷冻水量偏多，其中冷冻水供水温度为6.97℃，冷冻水供水温度偏低，适当提高水温以提高冷机效率。"]
     */

    private List<String> resobj;

    public List<String> getResobj() {
        return resobj;
    }

    public void setResobj(List<String> resobj) {
        this.resobj = resobj;
    }
}
