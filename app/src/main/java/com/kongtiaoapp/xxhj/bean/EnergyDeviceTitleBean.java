package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by G_XXHJ on 2018/7/30.
 */

public class EnergyDeviceTitleBean extends RBResponse {

    /**
     * code : 40000
     * resobj : {"ConsCateg":[{"Name":"电","Type":"Electricity"},{"Name":"水","Type":"Water"},{"Name":"气","Type":"Gas"}]}
     */

    private ResobjBean resobj;

    public ResobjBean getResobj() {
        return resobj;
    }

    public void setResobj(ResobjBean resobj) {
        this.resobj = resobj;
    }

    public static class ResobjBean {
        private List<ConsCategBean> ConsCateg;

        public List<ConsCategBean> getConsCateg() {
            return ConsCateg;
        }

        public void setConsCateg(List<ConsCategBean> ConsCateg) {
            this.ConsCateg = ConsCateg;
        }

        public static class ConsCategBean {
            /**
             * Name : 电
             * Type : Electricity
             */

            private String Name;
            private String Type;

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
