package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by G_XXHJ on 2018/7/5.
 */

public class Environment_statesBean extends RBResponse {

    /**
     * code : 40000
     * resobj : [{"CPatt":"控制模式","RName":"房间名称","RTemp":"房间温度","STemp":"设定温度","Sta":"运行状态","WSS":"风速设定"},{"CPatt":"2","RName":"徐宇博 7-131","RTemp":"18","STemp":"20","Sta":"ON","WSS":"3"},{"CPatt":"2","RName":"米鹏 3-029","RTemp":"18","STemp":"20","Sta":"ON","WSS":"3"},{"CPatt":"2","RName":"王家恒 7-130","RTemp":"18","STemp":"20","Sta":"ON","WSS":"3"},{"CPatt":"2","RName":"VIP餐厅 左","RTemp":"18","STemp":"20","Sta":"ON","WSS":"3"},{"CPatt":"2","RName":"VIP餐厅 右","RTemp":"18","STemp":"20","Sta":"ON","WSS":"3"},{"CPatt":"2","RName":"音频会议室 左","RTemp":"18","STemp":"20","Sta":"ON","WSS":"3"},{"CPatt":"2","RName":"音频会议室 右","RTemp":"18","STemp":"20","Sta":"ON","WSS":"3"},{"CPatt":"2","RName":"陈总办公室 左","RTemp":"18","STemp":"20","Sta":"ON","WSS":"3"},{"CPatt":"2","RName":"陈总办公室 右","RTemp":"18","STemp":"20","Sta":"ON","WSS":"3"},{"CPatt":"2","RName":"陈总办公室 里屋","RTemp":"18","STemp":"20","Sta":"ON","WSS":"3"},{"CPatt":"2","RName":"会议室 6-1","RTemp":"18","STemp":"20","Sta":"ON","WSS":"3"},{"CPatt":"2","RName":"董事长 左","RTemp":"18","STemp":"20","Sta":"ON","WSS":"3"},{"CPatt":"2","RName":"董事长 右","RTemp":"18","STemp":"20","Sta":"ON","WSS":"3"},{"CPatt":"2","RName":"董事长 里","RTemp":"18","STemp":"20","Sta":"ON","WSS":"3"},{"CPatt":"2","RName":"刘总办公室 里","RTemp":"18","STemp":"20","Sta":"ON","WSS":"3"},{"CPatt":"2","RName":"刘洪峰","RTemp":"18","STemp":"20","Sta":"ON","WSS":"3"},{"CPatt":"2","RName":"会议室 5-020","RTemp":"18","STemp":"20","Sta":"ON","WSS":"3"},{"CPatt":"2","RName":"高管办公室 5-021","RTemp":"18","STemp":"20","Sta":"ON","WSS":"3"},{"CPatt":"2","RName":"高管办公室 5-022","RTemp":"18","STemp":"20","Sta":"ON","WSS":"3"},{"CPatt":"2","RName":"仲慧峰 5-023","RTemp":"18","STemp":"20","Sta":"ON","WSS":"3"},{"CPatt":"2","RName":"高管办公室 三层","RTemp":"18","STemp":"20","Sta":"ON","WSS":"3"},{"CPatt":"2","RName":"刘总办公室 左","RTemp":"18","STemp":"20","Sta":"ON","WSS":"3"},{"CPatt":"2","RName":"刘总办公室 右","RTemp":"18","STemp":"20","Sta":"ON","WSS":"3"}]
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
         * CPatt : 控制模式
         * RName : 房间名称
         * RTemp : 房间温度
         * STemp : 设定温度
         * Sta : 运行状态
         * WSS : 风速设定
         */

        private String CPatt;
        private String RName;
        private String RTemp;
        private String STemp;
        private String Sta;
        private String WSS;

        public String getCPatt() {
            return CPatt;
        }

        public void setCPatt(String CPatt) {
            this.CPatt = CPatt;
        }

        public String getRName() {
            return RName;
        }

        public void setRName(String RName) {
            this.RName = RName;
        }

        public String getRTemp() {
            return RTemp;
        }

        public void setRTemp(String RTemp) {
            this.RTemp = RTemp;
        }

        public String getSTemp() {
            return STemp;
        }

        public void setSTemp(String STemp) {
            this.STemp = STemp;
        }

        public String getSta() {
            return Sta;
        }

        public void setSta(String Sta) {
            this.Sta = Sta;
        }

        public String getWSS() {
            return WSS;
        }

        public void setWSS(String WSS) {
            this.WSS = WSS;
        }
    }
}
