package com.kongtiaoapp.xxhj.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by G_XXHJ on 2018/9/29.  变配电数据录入列表
 */

public class BPD_DataEntryBean extends RBResponse {

    /**
     * code : 40000
     * resobj : {"Power":[{"DeviceId":"037493b034004df78b09e870cc60c670","IsEntry":"未录入","Name":"202"},{"DeviceId":"3defb9667d8b4f9c8536fe853483ba3b","IsEntry":"未录入","Name":"1号变压器"},{"DeviceId":"734df7f3b47a418fb43228f802210598","IsEntry":"未录入","Name":"201"},{"DeviceId":"7a35e507bbcd40fc8153a65c8369138c","IsEntry":"未录入","Name":"2号变压器"},{"DeviceId":"9a0aaa84df7241f8830de7f819d4ea15","IsEntry":"未录入","Name":"3号变压器"},{"DeviceId":"9c5cce738ed342f79fea298b51a9ee2c","IsEntry":"未录入","Name":"4号变压器"},{"DeviceId":"fba73a393efe4b9f9a27a3862744832b","IsEntry":"未录入","Name":"低质侧重点监测"}]}
     */

    private ResobjBean resobj;

    public ResobjBean getResobj() {
        return resobj;
    }

    public void setResobj(ResobjBean resobj) {
        this.resobj = resobj;
    }

    public static class ResobjBean {
        private List<PowerBean> Power;

        public List<PowerBean> getPower() {
            return Power;
        }

        public void setPower(List<PowerBean> Power) {
            this.Power = Power;
        }

        public static class PowerBean implements Serializable {
            /**
             * DeviceId : 037493b034004df78b09e870cc60c670
             * IsEntry : 未录入
             * Name : 202
             */

            private String DeviceId;
            private String IsEntry;
            private String Name;
            private String Type;
            private String Flag;
            public String getDeviceId() {
                return DeviceId;
            }

            public void setDeviceId(String DeviceId) {
                this.DeviceId = DeviceId;
            }

            public String getIsEntry() {
                return IsEntry;
            }

            public void setIsEntry(String IsEntry) {
                this.IsEntry = IsEntry;
            }

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public String getType() {
                return Type;
            }

            public void setType(String type) {
                Type = type;
            }

            public String getFlag() {
                return Flag;
            }

            public void setFlag(String flag) {
                this.Flag = flag;
            }
        }
    }
}
