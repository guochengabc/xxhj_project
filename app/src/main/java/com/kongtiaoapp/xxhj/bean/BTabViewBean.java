package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by G_XXHJ on 2018/10/9.  变配电头部图表的tab
 */

public class BTabViewBean extends RBResponse {

    /**
     * code : 40000
     * resobj : {"RunData":[{"DateSign":"C","Name":"201负荷率","Type":"AFH"},{"DateSign":"C","Name":"202负荷率","Type":"BFH"},{"DateSign":"C","Name":"峰谷电量","Type":"POWFG"}]}
     */

    private ResobjBean resobj;

    public ResobjBean getResobj() {
        return resobj;
    }

    public void setResobj(ResobjBean resobj) {
        this.resobj = resobj;
    }

    public static class ResobjBean {
        private List<RunDataBean> RunData;

        public List<RunDataBean> getRunData() {
            return RunData;
        }

        public void setRunData(List<RunDataBean> RunData) {
            this.RunData = RunData;
        }

        public static class RunDataBean {
            /**
             * DateSign : C
             * Name : 201负荷率
             * Type : AFH
             */

            private String DateSign;
            private String Name;
            private String Type;

            public String getDateSign() {
                return DateSign;
            }

            public void setDateSign(String DateSign) {
                this.DateSign = DateSign;
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

            public void setType(String Type) {
                this.Type = Type;
            }
        }
    }
}
