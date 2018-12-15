package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by xxhj_g on 2017/1/6.  运行信息
 */
public class RunDevice_StateBean extends RBResponse {

    /**
     * code : 40000
     * resobj : [{"AlarmArr":[0,0],"ColData":[{"k0":"设备名称","k1":"运行状态","k2":"耗电量(kWh)"},{"k0":"1号约克冷机","k1":"N/A","k2":"0"},{"k0":"2号约克冷机","k1":"N/A","k2":"0"}],"Count":2,"Index":0,"ParamSize":3,"TypeName":"冷机"},{"AlarmArr":[1,0,0,0],"ColData":[{"k0":"设备名称","k1":"运行状态","k2":"压差(KPa)"},{"k0":" 1号冷冻水泵","k1":"N/A","k2":"0"},{"k0":" 2号冷冻水泵","k1":"N/A","k2":"0"},{"k0":" 3号冷冻水泵","k1":"N/A","k2":"0"},{"k0":" 4号冷冻水泵","k1":"N/A","k2":"0"}],"Count":4,"Index":1,"ParamSize":3,"TypeName":"冷冻水泵"},{"AlarmArr":[0,1,0,0],"ColData":[{"k0":"设备名称","k1":"运行状态","k2":"压差(KPa)"},{"k0":"1号冷机冷却水泵","k1":"N/A","k2":"0"},{"k0":"2号冷机冷却水泵","k1":"N/A","k2":"0"},{"k0":"蓝标直燃机冷却水泵","k1":"N/A","k2":"0"},{"k0":"民生直燃机冷却水泵","k1":"N/A","k2":"0"}],"Count":4,"Index":2,"ParamSize":3,"TypeName":"冷却水泵"},{"AlarmArr":[0,0,0,0,0,0],"ColData":[{"k0":"设备名称","k1":"运行状态"},{"k0":" 1号冷却塔","k1":"N/A"},{"k0":" 2号冷却塔","k1":"N/A"},{"k0":" 3号冷却塔","k1":"N/A"},{"k0":" 4号冷却塔","k1":"N/A"},{"k0":" 5号冷却塔","k1":"N/A"},{"k0":" 6号冷却塔","k1":"N/A"}],"Count":6,"Index":3,"ParamSize":2,"TypeName":"冷却塔"},{"AlarmArr":[0,0,0,0],"ColData":[{"k0":"设备名称","k1":"运行状态"},{"k0":"1号冷冻支路","k1":"N/A"},{"k0":"2号冷冻支路","k1":"N/A"},{"k0":"3号冷冻支路","k1":"N/A"},{"k0":"4号冷冻支路","k1":"N/A"}],"Count":4,"Index":4,"ParamSize":2,"TypeName":"冷冻支路"},{"AlarmArr":[0],"ColData":[{"k0":"设备名称","k1":"运行状态"},{"k0":"补水泵","k1":"N/A"}],"Count":1,"Index":5,"ParamSize":2,"TypeName":"补水泵"},{"AlarmArr":[0,0],"ColData":[{"k0":"设备名称","k1":"运行状态","k2":"耗气量(Nm3)"},{"k0":"蓝标直燃机","k1":"N/A","k2":"0"},{"k0":"民生直燃机","k1":"N/A","k2":"0"}],"Count":2,"Index":6,"ParamSize":3,"TypeName":"直燃机"},{"AlarmArr":[0,0],"ColData":[{"k0":"设备名称","k1":"运行状态"},{"k0":"1#软水装置","k1":"N/A"},{"k0":"2#软水装置","k1":"N/A"}],"Count":2,"Index":7,"ParamSize":2,"TypeName":"软化水装置"},{"AlarmArr":[0],"ColData":[{"k0":"设备名称","k1":"运行状态"},{"k0":"1号集水器","k1":"N/A"}],"Count":1,"Index":8,"ParamSize":2,"TypeName":"分集水器"}]
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
         * AlarmArr : [0,0]
         * ColData : [{"k0":"设备名称","k1":"运行状态","k2":"耗电量(kWh)"},{"k0":"1号约克冷机","k1":"N/A","k2":"0"},{"k0":"2号约克冷机","k1":"N/A","k2":"0"}]
         * Count : 2
         * Index : 0
         * ParamSize : 3
         * TypeName : 冷机
         */

        private int Count;
        private int ParamSize;
        private int IsAlarm;
        private String TypeName;
        private List<Integer> AlarmArr;
        private List<ColDataBean> ColData;
        public int getCount() {
            return Count;
        }

        public void setCount(int Count) {
            this.Count = Count;
        }

        public int getParamSize() {
            return ParamSize;
        }

        public void setParamSize(int ParamSize) {
            this.ParamSize = ParamSize;
        }

        public int getIsAlarm() {
            return IsAlarm;
        }

        public void setIsAlarm(int isAlarm) {
            IsAlarm = isAlarm;
        }

        public String getTypeName() {
            return TypeName;
        }

        public void setTypeName(String TypeName) {
            this.TypeName = TypeName;
        }

        public List<Integer> getAlarmArr() {
            return AlarmArr;
        }

        public void setAlarmArr(List<Integer> AlarmArr) {
            this.AlarmArr = AlarmArr;
        }

        public List<ColDataBean> getColData() {
            return ColData;
        }

        public void setColData(List<ColDataBean> ColData) {
            this.ColData = ColData;
        }

        public static class ColDataBean {
            /**
             * k0 : 设备名称
             * k1 : 运行状态
             * k2 : 耗电量(kWh)
             */

            private String k0;
            private String k1;
            private String k2;
            private String k3;
            private String k4;
            private String DeviceId;
            private String Type;
            public String getK0() {
                return k0;
            }

            public void setK0(String k0) {
                this.k0 = k0;
            }

            public String getK1() {
                return k1;
            }

            public void setK1(String k1) {
                this.k1 = k1;
            }

            public String getK2() {
                return k2;
            }

            public void setK2(String k2) {
                this.k2 = k2;
            }

            public String getK3() {
                return k3;
            }

            public void setK3(String k3) {
                this.k3 = k3;
            }

            public String getK4() {
                return k4;
            }

            public void setK4(String k4) {
                this.k4 = k4;
            }

            public String getDeviceId() {
                return DeviceId;
            }

            public void setDeviceId(String deviceId) {
                DeviceId = deviceId;
            }

            public String getType() {
                return Type;
            }

            public void setType(String type) {
                this.Type = type;
            }
        }
    }
}
