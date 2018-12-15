package com.kongtiaoapp.xxhj.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xxhj_g on 2017/11/6.
 */

public class TestBean extends RBResponse implements Serializable {

    /**
     * code : 40000
     * resobj : [{"type":"1","formType":"派","repairContent":"电梯里面的灯坏了","place":"豪柏大厦C1座中间的电梯","finishTime":"2017-7-29","engineer":"张力","repairStatus":"待处理","formTime":"2017-7-28","reason":""},{"type":"2","formType":"派","repairContent":"门禁卡坏了","place":"豪柏大厦C1座1205室","finishTime":"2017-7-29","engineer":"李嘉龙","repairStatus":"进行中","formTime":"2017-7-29","reason":""},{"type":"3","formType":"报","repairContent":"门禁卡坏了","place":"豪柏大厦C1座1205室","finishTime":"2017-7-28","engineer":"张力","repairStatus":"未派单","formTime":"2017-7-28","reason":""},{"type":"4","formType":"派","repairContent":"停电","place":"整个豪柏大厦","finishTime":"2017-7-28","engineer":"楚风","repairStatus":"进行中","formTime":"2017-7-28","reason":""},{"type":"5","formType":"返","repairContent":"停电","place":"整个豪柏大厦","finishTime":"2017-7-28","engineer":"楚风","repairStatus":"进行中","formTime":"2017-7-28","reason":"还有部分业主没有电"},{"type":"6","formType":"报","repairContent":"电梯坏了","place":"豪柏大厦C2座","finishTime":"2017-7-28","engineer":"张力","repairStatus":"已完成","formTime":"2017-7-28","reason":""},{"type":"7","formType":"派","repairContent":"门禁卡坏了","place":"豪柏大厦C1座1205室","finishTime":"2017-7-29","engineer":"刘子铭","repairStatus":"已结束","formTime":"2017-7-29","reason":""},{"type":"8","formType":"派","repairContent":"门禁卡坏了","place":"豪柏大厦C1座1205室","finishTime":"2017-7-29","engineer":"刘子铭","repairStatus":"已结束","formTime":"2017-7-29","reason":""}]
     */

    private List<ResobjBean> resobj;

    public List<ResobjBean> getResobj() {
        return resobj;
    }

    public void setResobj(List<ResobjBean> resobj) {
        this.resobj = resobj;
    }

    public static class ResobjBean {
        /**
         * type : 1
         * formType : 派
         * repairContent : 电梯里面的灯坏了
         * place : 豪柏大厦C1座中间的电梯
         * finishTime : 2017-7-29
         * engineer : 张力
         * repairStatus : 待处理
         * formTime : 2017-7-28
         * reason :
         */

        private String type;
        private String formType;
        private String repairContent;
        private String place;
        private String finishTime;
        private String engineer;
        private String repairStatus;
        private String formTime;
        private String reason;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getFormType() {
            return formType;
        }

        public void setFormType(String formType) {
            this.formType = formType;
        }

        public String getRepairContent() {
            return repairContent;
        }

        public void setRepairContent(String repairContent) {
            this.repairContent = repairContent;
        }

        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
        }

        public String getFinishTime() {
            return finishTime;
        }

        public void setFinishTime(String finishTime) {
            this.finishTime = finishTime;
        }

        public String getEngineer() {
            return engineer;
        }

        public void setEngineer(String engineer) {
            this.engineer = engineer;
        }

        public String getRepairStatus() {
            return repairStatus;
        }

        public void setRepairStatus(String repairStatus) {
            this.repairStatus = repairStatus;
        }

        public String getFormTime() {
            return formTime;
        }

        public void setFormTime(String formTime) {
            this.formTime = formTime;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }
    }
}
