package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by xxhj_g on 2017/3/17.
 */
public class DeviceInfoBean extends RBResponse {

    private List<ResobjBean> resobj;

    public List<ResobjBean> getResobj() {
        return resobj;
    }

    public void setResobj(List<ResobjBean> resobj) {
        this.resobj = resobj;
    }

    public static class ResobjBean {
        /**
         * TypeName : 热水循环泵
         * Data : [{"RatedPower":"75.00","Model":"无"},{"RatedPower":"75.00","Model":"无"}]
         * Count : 8
         */

        private String TypeName;
        private String Count;
        private List<DataBean> Data;

        public String getTypeName() {
            return TypeName;
        }

        public void setTypeName(String TypeName) {
            this.TypeName = TypeName;
        }

        public String getCount() {
            return Count;
        }

        public void setCount(String Count) {
            this.Count = Count;
        }

        public List<DataBean> getData() {
            return Data;
        }

        public void setData(List<DataBean> Data) {
            this.Data = Data;
        }

        public static class DataBean {
            /**
             * RatedPower : 75.00
             * Model : 无
             */

            private String RatedPower;
            private String Model;

            public String getRatedPower() {
                return RatedPower;
            }

            public void setRatedPower(String RatedPower) {
                this.RatedPower = RatedPower;
            }

            public String getModel() {
                return Model;
            }

            public void setModel(String Model) {
                this.Model = Model;
            }
        }
    }
}
