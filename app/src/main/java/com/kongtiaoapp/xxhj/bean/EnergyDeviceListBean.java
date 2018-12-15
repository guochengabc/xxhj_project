package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by G_XXHJ on 2018/7/30.
 */

public class EnergyDeviceListBean extends RBResponse {
    private ResobjBean resobj;

    public ResobjBean getResobj() {
        return resobj;
    }

    public void setResobj(ResobjBean resobj) {
        this.resobj = resobj;
    }

    public static class ResobjBean {
        private List<OneDataBean> OneData;

        public List<OneDataBean> getOneData() {
            return OneData;
        }

        public void setOneData(List<OneDataBean> OneData) {
            this.OneData = OneData;
        }

        public static class OneDataBean {


            private String OneCount;
            private String OneName;
            private String OneSid;
            private String OneStatus;
            private List<TwoDataBean> TwoData;

            public String getOneStatus() {
                return OneStatus;
            }

            public void setOneStatus(String oneStatus) {
                OneStatus = oneStatus;
            }

            public String getOneCount() {
                return OneCount;
            }

            public void setOneCount(String OneCount) {
                this.OneCount = OneCount;
            }

            public String getOneName() {
                return OneName;
            }

            public void setOneName(String OneName) {
                this.OneName = OneName;
            }

            public String getOneSid() {
                return OneSid;
            }

            public void setOneSid(String OneSid) {
                this.OneSid = OneSid;
            }

            public List<TwoDataBean> getTwoData() {
                return TwoData;
            }

            public void setTwoData(List<TwoDataBean> TwoData) {
                this.TwoData = TwoData;
            }

            public static class TwoDataBean {

                private String TwoCount;
                private String TwoName;
                private String TwoSid;
                private String TwoStatus;

                public String getTwoStatus() {
                    return TwoStatus;
                }

                public void setTwoStatus(String twoStatus) {
                    TwoStatus = twoStatus;
                }

                private List<ThreeDataBean> ThreeData;

                public String getTwoCount() {
                    return TwoCount;
                }

                public void setTwoCount(String TwoCount) {
                    this.TwoCount = TwoCount;
                }

                public String getTwoName() {
                    return TwoName;
                }

                public void setTwoName(String TwoName) {
                    this.TwoName = TwoName;
                }

                public String getTwoSid() {
                    return TwoSid;
                }

                public void setTwoSid(String TwoSid) {
                    this.TwoSid = TwoSid;
                }

                public List<ThreeDataBean> getThreeData() {
                    return ThreeData;
                }

                public void setThreeData(List<ThreeDataBean> ThreeData) {
                    this.ThreeData = ThreeData;
                }

                public static class ThreeDataBean {
                    /**
                     * ThreeName : A座地上1-7用电（插接箱）
                     * ThreeSid : b100cfbd02bc4e03a562f86e8e942adf
                     */

                    private String ThreeName;
                    private String ThreeSid;
                    private String ThreeStatus;

                    public String getThreeStatus() {
                        return ThreeStatus;
                    }

                    public void setThreeStatus(String threeStatus) {
                        ThreeStatus = threeStatus;
                    }

                    public String getThreeName() {
                        return ThreeName;
                    }

                    public void setThreeName(String ThreeName) {
                        this.ThreeName = ThreeName;
                    }

                    public String getThreeSid() {
                        return ThreeSid;
                    }

                    public void setThreeSid(String ThreeSid) {
                        this.ThreeSid = ThreeSid;
                    }
                }
            }
        }
    }
}
