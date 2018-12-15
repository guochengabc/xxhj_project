package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by G_XXHJ on 2018/6/8.   参数设置
 */

public class ParamSettingBean extends RBResponse {

    /**
     * code : 40000
     * resobj : {"ColData":[{"name":"参数设置","setValue":"设定值","alarmValue":"报警值","paraId":"设备Id"}],"Count":1,"IsAlarm":0,"TypeName":"设备参数"}
     */

    private ResobjBean resobj;

    public ResobjBean getResobj() {
        return resobj;
    }

    public void setResobj(ResobjBean resobj) {
        this.resobj = resobj;
    }

    public static class ResobjBean {
        /**
         * ColData : [{"name":"参数设置","setValue":"设定值","alarmValue":"报警值","paraId":"设备Id"}]
         * Count : 1
         * IsAlarm : 0
         * TypeName : 设备参数
         */

        private int Count;
        private int IsAlarm;
        private String TypeName;
        private List<ColDataBean> ColData;

        public int getCount() {
            return Count;
        }

        public void setCount(int Count) {
            this.Count = Count;
        }

        public int getIsAlarm() {
            return IsAlarm;
        }

        public void setIsAlarm(int IsAlarm) {
            this.IsAlarm = IsAlarm;
        }

        public String getTypeName() {
            return TypeName;
        }

        public void setTypeName(String TypeName) {
            this.TypeName = TypeName;
        }

        public List<ColDataBean> getColData() {
            return ColData;
        }

        public void setColData(List<ColDataBean> ColData) {
            this.ColData = ColData;
        }

        public static class ColDataBean {
            /**
             * name : 参数设置
             * setValue : 设定值
             * alarmValue : 报警值
             * paraId : 设备Id
             * fieidType:定义是不是文本类型  时间框还是其他
             */

            private String name;
            private String setValue;
            private String alarmValue;
            private String paraId;
            private String fieidType;//
            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSetValue() {
                return setValue;
            }

            public void setSetValue(String setValue) {
                this.setValue = setValue;
            }

            public String getAlarmValue() {
                return alarmValue;
            }

            public void setAlarmValue(String alarmValue) {
                this.alarmValue = alarmValue;
            }

            public String getParaId() {
                return paraId;
            }

            public void setParaId(String paraId) {
                this.paraId = paraId;
            }

            public String getFieidType() {
                return fieidType;
            }

            public void setFieidType(String fieidType) {
                this.fieidType = fieidType;
            }
        }
    }
}
