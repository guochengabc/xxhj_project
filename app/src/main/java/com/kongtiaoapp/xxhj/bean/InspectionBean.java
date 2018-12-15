package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by G_XXHJ on 2018/4/29.
 */

public class InspectionBean extends RBResponse {

    /**
     * code : 40000
     * resobj : {"Insp":[{"Value":"电流（A）三相平衡小于±5%","FieldType":"boolean","Code":""},{"Value":"电压在380V（±5%）","FieldType":"boolean","Code":""},{"Value":"水泵温度是否过热","FieldType":"boolean","Code":""},{"Value":"水泵是否震动","FieldType":"boolean","Code":""},{"Value":"水泵声音是否正常","FieldType":"boolean","Code":""},{"Value":"变频器是否正常","FieldType":"boolean","Code":""}]}
     */

    private ResobjBean resobj;

    public ResobjBean getResobj() {
        return resobj;
    }

    public void setResobj(ResobjBean resobj) {
        this.resobj = resobj;
    }

    public static class ResobjBean {
        private List<InspBean> Insp;

        public List<InspBean> getInsp() {
            return Insp;
        }

        public void setInsp(List<InspBean> Insp) {
            this.Insp = Insp;
        }

        public static class InspBean {
            /**
             * Value : 电流（A）三相平衡小于±5%
             * FieldType : boolean
             * Code :
             */

            private String Value;
            private String FieldType;
            private String Code;

            public String getValue() {
                return Value;
            }

            public void setValue(String Value) {
                this.Value = Value;
            }

            public String getFieldType() {
                return FieldType;
            }

            public void setFieldType(String FieldType) {
                this.FieldType = FieldType;
            }

            public String getCode() {
                return Code;
            }

            public void setCode(String Code) {
                this.Code = Code;
            }
        }
    }
}
