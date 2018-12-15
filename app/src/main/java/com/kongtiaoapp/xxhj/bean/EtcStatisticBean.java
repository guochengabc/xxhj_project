package com.kongtiaoapp.xxhj.bean;

import java.util.List;

public class EtcStatisticBean extends RBResponse {

    /**
     * code : 40000
     * resobj : {"Data":[{"StatisticType":"本日","EtcData":[{"StatisticEtc":"","EtcValueO":"201有功","EtcValueT":"202有功"},{"StatisticEtc":"尖电量","EtcValueO":"11110","EtcValueT":"11111"},{"StatisticEtc":"峰电量","EtcValueO":"11112","EtcValueT":"11113"},{"StatisticEtc":"平电量","EtcValueO":"11114","EtcValueT":"11115"},{"StatisticEtc":"谷电量","EtcValueO":"11116","EtcValueT":"11117"}]},{"StatisticType":"本月","EtcData":[{"StatisticEtc":"","EtcValueO":"201有功","EtcValueT":"202有功"},{"StatisticEtc":"尖电量","EtcValueO":"11118","EtcValueT":"11119"},{"StatisticEtc":"峰电量","EtcValueO":"11120","EtcValueT":"11121"},{"StatisticEtc":"平电量","EtcValueO":"11122","EtcValueT":"11123"},{"StatisticEtc":"谷电量","EtcValueO":"11124","EtcValueT":"11125"}]}]}
     */

    private ResobjBean resobj;

    public ResobjBean getResobj() {
        return resobj;
    }

    public void setResobj(ResobjBean resobj) {
        this.resobj = resobj;
    }

    public static class ResobjBean {
        private List<DataBean> Data;

        public List<DataBean> getData() {
            return Data;
        }

        public void setData(List<DataBean> Data) {
            this.Data = Data;
        }

        public static class DataBean {
            /**
             * StatisticType : 本日
             * EtcData : [{"StatisticEtc":"","EtcValueO":"201有功","EtcValueT":"202有功"},{"StatisticEtc":"尖电量","EtcValueO":"11110","EtcValueT":"11111"},{"StatisticEtc":"峰电量","EtcValueO":"11112","EtcValueT":"11113"},{"StatisticEtc":"平电量","EtcValueO":"11114","EtcValueT":"11115"},{"StatisticEtc":"谷电量","EtcValueO":"11116","EtcValueT":"11117"}]
             */

            private String StatisticType;
            private List<EtcDataBean> EtcData;

            public String getStatisticType() {
                return StatisticType;
            }

            public void setStatisticType(String StatisticType) {
                this.StatisticType = StatisticType;
            }

            public List<EtcDataBean> getEtcData() {
                return EtcData;
            }

            public void setEtcData(List<EtcDataBean> EtcData) {
                this.EtcData = EtcData;
            }

            public static class EtcDataBean {
                /**
                 * StatisticEtc :
                 * EtcValueO : 201有功
                 * EtcValueT : 202有功
                 */

                private String StatisticEtc;
                private String EtcValueO;
                private String EtcValueT;

                public String getStatisticEtc() {
                    return StatisticEtc;
                }

                public void setStatisticEtc(String StatisticEtc) {
                    this.StatisticEtc = StatisticEtc;
                }

                public String getEtcValueO() {
                    return EtcValueO;
                }

                public void setEtcValueO(String EtcValueO) {
                    this.EtcValueO = EtcValueO;
                }

                public String getEtcValueT() {
                    return EtcValueT;
                }

                public void setEtcValueT(String EtcValueT) {
                    this.EtcValueT = EtcValueT;
                }
            }
        }
    }
}
