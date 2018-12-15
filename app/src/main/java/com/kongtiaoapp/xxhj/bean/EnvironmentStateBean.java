package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by G_XXHJ on 2018/7/6.  环境监测
 */

public class EnvironmentStateBean extends RBResponse {

    /**
     * code : 40000
     * resobj : {"Data":[{"ReginMData":[{"CPatt":"2","RName":"高管办公室 三层","RTemp":"18","STemp":"20","Sta":"ON","WSS":"1"}],"TypeName":"3层"},{"ReginMData":[{"CPatt":"2","RName":"会议室 5-020","RTemp":"18","STemp":"20","Sta":"ON","WSS":"1"},{"CPatt":"2","RName":"高管办公室 5-021","RTemp":"18","STemp":"20","Sta":"ON","WSS":"1"},{"CPatt":"2","RName":"高管办公室 5-022","RTemp":"18","STemp":"20","Sta":"ON","WSS":"1"},{"CPatt":"2","RName":"仲慧峰 5-023","RTemp":"18","STemp":"20","Sta":"ON","WSS":"1"}],"TypeName":"5层"},{"ReginMData":[{"CPatt":"2","RName":"音频会议室 左","RTemp":"18","STemp":"20","Sta":"ON","WSS":"1"},{"CPatt":"2","RName":"音频会议室 右","RTemp":"18","STemp":"20","Sta":"ON","WSS":"1"},{"CPatt":"2","RName":"陈总办公室 左","RTemp":"18","STemp":"20","Sta":"ON","WSS":"1"},{"CPatt":"2","RName":"陈总办公室 右","RTemp":"18","STemp":"20","Sta":"ON","WSS":"1"},{"CPatt":"2","RName":"陈总办公室 里屋","RTemp":"18","STemp":"20","Sta":"ON","WSS":"1"},{"CPatt":"2","RName":"会议室 6-1","RTemp":"18","STemp":"20","Sta":"ON","WSS":"1"},{"CPatt":"2","RName":"董事长 左","RTemp":"18","STemp":"20","Sta":"ON","WSS":"1"},{"CPatt":"2","RName":"董事长 右","RTemp":"18","STemp":"20","Sta":"ON","WSS":"1"},{"CPatt":"2","RName":"董事长 里","RTemp":"18","STemp":"20","Sta":"ON","WSS":"1"},{"CPatt":"2","RName":"刘总办公室 里","RTemp":"18","STemp":"20","Sta":"ON","WSS":"1"},{"CPatt":"2","RName":"刘洪峰","RTemp":"18","STemp":"20","Sta":"ON","WSS":"1"},{"CPatt":"2","RName":"刘总办公室 左","RTemp":"18","STemp":"20","Sta":"ON","WSS":"1"},{"CPatt":"2","RName":"刘总办公室 右","RTemp":"18","STemp":"20","Sta":"ON","WSS":"1"}],"TypeName":"6层"},{"ReginMData":[{"CPatt":"2","RName":"徐宇博 7-131","RTemp":"18","STemp":"20","Sta":"ON","WSS":"1"},{"CPatt":"2","RName":"米鹏 3-029","RTemp":"18","STemp":"20","Sta":"ON","WSS":"1"},{"CPatt":"2","RName":"王家恒 7-130","RTemp":"18","STemp":"20","Sta":"ON","WSS":"1"},{"CPatt":"2","RName":"VIP餐厅 左","RTemp":"18","STemp":"20","Sta":"ON","WSS":"1"},{"CPatt":"2","RName":"VIP餐厅 右","RTemp":"18","STemp":"20","Sta":"ON","WSS":"1"}],"TypeName":"7层"}],"Remark":"模式控制: 1(制冷),2(供暖),3(通风)  风速设定:0(高),1(中),2(低),3(自动)","Title":{"CPatt":"控制模式","RName":"房间名称","RTemp":"房间温度","STemp":"设置温度","Sta":"状态","WSS":"风机设置"}}
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
         * Data : [{"ReginMData":[{"CPatt":"2","RName":"高管办公室 三层","RTemp":"18","STemp":"20","Sta":"ON","WSS":"1"}],"TypeName":"3层"},{"ReginMData":[{"CPatt":"2","RName":"会议室 5-020","RTemp":"18","STemp":"20","Sta":"ON","WSS":"1"},{"CPatt":"2","RName":"高管办公室 5-021","RTemp":"18","STemp":"20","Sta":"ON","WSS":"1"},{"CPatt":"2","RName":"高管办公室 5-022","RTemp":"18","STemp":"20","Sta":"ON","WSS":"1"},{"CPatt":"2","RName":"仲慧峰 5-023","RTemp":"18","STemp":"20","Sta":"ON","WSS":"1"}],"TypeName":"5层"},{"ReginMData":[{"CPatt":"2","RName":"音频会议室 左","RTemp":"18","STemp":"20","Sta":"ON","WSS":"1"},{"CPatt":"2","RName":"音频会议室 右","RTemp":"18","STemp":"20","Sta":"ON","WSS":"1"},{"CPatt":"2","RName":"陈总办公室 左","RTemp":"18","STemp":"20","Sta":"ON","WSS":"1"},{"CPatt":"2","RName":"陈总办公室 右","RTemp":"18","STemp":"20","Sta":"ON","WSS":"1"},{"CPatt":"2","RName":"陈总办公室 里屋","RTemp":"18","STemp":"20","Sta":"ON","WSS":"1"},{"CPatt":"2","RName":"会议室 6-1","RTemp":"18","STemp":"20","Sta":"ON","WSS":"1"},{"CPatt":"2","RName":"董事长 左","RTemp":"18","STemp":"20","Sta":"ON","WSS":"1"},{"CPatt":"2","RName":"董事长 右","RTemp":"18","STemp":"20","Sta":"ON","WSS":"1"},{"CPatt":"2","RName":"董事长 里","RTemp":"18","STemp":"20","Sta":"ON","WSS":"1"},{"CPatt":"2","RName":"刘总办公室 里","RTemp":"18","STemp":"20","Sta":"ON","WSS":"1"},{"CPatt":"2","RName":"刘洪峰","RTemp":"18","STemp":"20","Sta":"ON","WSS":"1"},{"CPatt":"2","RName":"刘总办公室 左","RTemp":"18","STemp":"20","Sta":"ON","WSS":"1"},{"CPatt":"2","RName":"刘总办公室 右","RTemp":"18","STemp":"20","Sta":"ON","WSS":"1"}],"TypeName":"6层"},{"ReginMData":[{"CPatt":"2","RName":"徐宇博 7-131","RTemp":"18","STemp":"20","Sta":"ON","WSS":"1"},{"CPatt":"2","RName":"米鹏 3-029","RTemp":"18","STemp":"20","Sta":"ON","WSS":"1"},{"CPatt":"2","RName":"王家恒 7-130","RTemp":"18","STemp":"20","Sta":"ON","WSS":"1"},{"CPatt":"2","RName":"VIP餐厅 左","RTemp":"18","STemp":"20","Sta":"ON","WSS":"1"},{"CPatt":"2","RName":"VIP餐厅 右","RTemp":"18","STemp":"20","Sta":"ON","WSS":"1"}],"TypeName":"7层"}]
         * Remark : 模式控制: 1(制冷),2(供暖),3(通风)  风速设定:0(高),1(中),2(低),3(自动)
         * Title : {"CPatt":"控制模式","RName":"房间名称","RTemp":"房间温度","STemp":"设置温度","Sta":"状态","WSS":"风机设置"}
         */

        private String Remark;
        private TitleBean Title;
        private List<DataBean> Data;

        public String getRemark() {
            return Remark;
        }

        public void setRemark(String Remark) {
            this.Remark = Remark;
        }

        public TitleBean getTitle() {
            return Title;
        }

        public void setTitle(TitleBean Title) {
            this.Title = Title;
        }

        public List<DataBean> getData() {
            return Data;
        }

        public void setData(List<DataBean> Data) {
            this.Data = Data;
        }

        public static class TitleBean {
            /**
             * CPatt : 控制模式
             * RName : 房间名称
             * RTemp : 房间温度
             * STemp : 设置温度
             * Sta : 状态
             * WSS : 风机设置
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

        public static class DataBean {
            /**
             * ReginMData : [{"CPatt":"2","RName":"高管办公室 三层","RTemp":"18","STemp":"20","Sta":"ON","WSS":"1"}]
             * TypeName : 3层
             */

            private String TypeName;
            private String RCount;
            private List<ReginMDataBean> ReginMData;

            public String getTypeName() {
                return TypeName;
            }

            public void setTypeName(String TypeName) {
                this.TypeName = TypeName;
            }

            public String getRCount() {
                return RCount;
            }

            public void setRCount(String RCount) {
                this.RCount = RCount;
            }

            public List<ReginMDataBean> getReginMData() {
                return ReginMData;
            }

            public void setReginMData(List<ReginMDataBean> ReginMData) {
                this.ReginMData = ReginMData;
            }

            public static class ReginMDataBean {
                /**
                 * CPatt : 2
                 * RName : 高管办公室 三层
                 * RTemp : 18
                 * STemp : 20
                 * Sta : ON
                 * WSS : 1
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
    }
}
