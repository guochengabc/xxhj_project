package com.kongtiaoapp.xxhj.bean;

import java.util.List;

public class EnergyTopBean extends RBResponse {

    /**
     * code : 40000
     * resobj : {"TopList":[{"deviceId":"1764480f0ac24f8aa65344b8993fdd3a","name":"10#站","value":"1282.1"},{"deviceId":"23df503936f14ab09b80beccdd6f2e6a","name":"15#站","value":"10607.6"},{"deviceId":"4972e4c4a20e43f691e917b10a68766b","name":"8#站","value":"15088"},{"deviceId":"56de733dbb6849fdbb09973fdeac086b","name":"19#站","value":"0"},{"deviceId":"5ff85887a2554fa5a0776dda801d6148","name":"17#站","value":"463.92"},{"deviceId":"69fc22530e514ca09542c38e388a73f7","name":"13#站","value":"2907"},{"deviceId":"74f84383627f4cf5b7435e7a307fd5b6","name":"4#站","value":"5705.2"},{"deviceId":"d3f0e34aad234b2890594b56907684c4","name":"7#站","value":"4988"},{"deviceId":"d474d13b42684004ae38300cbfc9574f","name":"20#站","value":"9068"},{"deviceId":"d720e501b7ad4b6086b777ce33d35944","name":"21#站","value":"4599"},{"deviceId":"e9a0e637fee24876b0f3296bd09b016c","name":"2#站","value":"10944.6"},{"deviceId":"fac87ef408604c6f8659ce4d74220cd9","name":"5#站","value":"3328"}]}
     */

    private ResobjBean resobj;

    public ResobjBean getResobj() {
        return resobj;
    }

    public void setResobj(ResobjBean resobj) {
        this.resobj = resobj;
    }

    public static class ResobjBean {
        private List<TopListBean> TopList;

        public List<TopListBean> getTopList() {
            return TopList;
        }

        public void setTopList(List<TopListBean> TopList) {
            this.TopList = TopList;
        }

        public static class TopListBean {
            /**
             * deviceId : 1764480f0ac24f8aa65344b8993fdd3a
             * name : 10#站
             * value : 1282.1
             */

            private String deviceId;
            private String name;
            private String value;

            public String getDeviceId() {
                return deviceId;
            }

            public void setDeviceId(String deviceId) {
                this.deviceId = deviceId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }
    }
}
