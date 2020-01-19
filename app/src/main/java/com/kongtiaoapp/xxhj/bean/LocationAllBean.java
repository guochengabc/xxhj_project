package com.kongtiaoapp.xxhj.bean;

import java.util.List;

public class LocationAllBean extends RBResponse {

    /**
     * code : 40000
     * resobj : {"location":{"locationArr":[{"SqlParam":"Building","Name":"A栋"},{"SqlParam":"Building","Name":"B栋"},{"SqlParam":"Building","Name":"C栋"},{"SqlParam":"Building","Name":"D栋"},{"SqlParam":"Building","Name":"E栋"},{"SqlParam":"Building","Name":"F栋"}]}}
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
         * location : {"locationArr":[{"SqlParam":"Building","Name":"A栋"},{"SqlParam":"Building","Name":"B栋"},{"SqlParam":"Building","Name":"C栋"},{"SqlParam":"Building","Name":"D栋"},{"SqlParam":"Building","Name":"E栋"},{"SqlParam":"Building","Name":"F栋"}]}
         */

        private LocationBean location;

        public LocationBean getLocation() {
            return location;
        }

        public void setLocation(LocationBean location) {
            this.location = location;
        }

        public static class LocationBean {
            private List<LocationArrBean> locationArr;

            public List<LocationArrBean> getLocationArr() {
                return locationArr;
            }

            public void setLocationArr(List<LocationArrBean> locationArr) {
                this.locationArr = locationArr;
            }

            public static class LocationArrBean {
                /**
                 * SqlParam : Building
                 * Name : A栋
                 */

                private String SqlParam;
                private String Name;

                public String getSqlParam() {
                    return SqlParam;
                }

                public void setSqlParam(String SqlParam) {
                    this.SqlParam = SqlParam;
                }

                public String getName() {
                    return Name;
                }

                public void setName(String Name) {
                    this.Name = Name;
                }
            }
        }
    }
}
