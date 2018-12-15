package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by Luoye on 2016-9-15.
 * 说明:
 */
public class ZhenDuanList extends RBResponse {

    /**
     * DiagTime : 1471938600
     * DiagID : 57bc00286ca7fa0aa0b97c91
     */

    private List<ZhenduanTime> resobj;

    public List<ZhenduanTime> getResobj() {
        return resobj;
    }

    public void setResobj(List<ZhenduanTime> resobj) {
        this.resobj = resobj;
    }

    public static class ZhenduanTime {
        private String DiagTime;
        private String DiagID;

        public String getDiagTime() {
            return DiagTime;
        }

        public void setDiagTime(String DiagTime) {
            this.DiagTime = DiagTime;
        }

        public String getDiagID() {
            return DiagID;
        }

        public void setDiagID(String DiagID) {
            this.DiagID = DiagID;
        }
    }
}
