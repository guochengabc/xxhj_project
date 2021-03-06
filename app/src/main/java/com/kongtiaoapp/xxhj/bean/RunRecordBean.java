package com.kongtiaoapp.xxhj.bean;

import java.util.List;

public class RunRecordBean extends RBResponse {


    /**
     * code : 40000
     * resobj : {"BRunRecord":["1号大屏2019-04-22 23:59:59关机","1号大屏2019-04-22 23:37:07开机","1号大屏2019-04-22 23:28:35关机","1号大屏2019-04-22 23:27:05开机","1号大屏2019-04-22 23:22:34关机","1号大屏2019-04-22 23:17:03开机","1号大屏2019-04-22 23:00:30关机","1号大屏2019-04-22 22:59:30开机","1号大屏2019-04-22 22:52:59关机","1号大屏2019-04-22 22:32:32开机","1号大屏2019-04-22 22:29:02关机","1号大屏2019-04-22 22:28:31开机","1号大屏2019-04-22 22:26:31关机","1号大屏2019-04-22 22:18:29开机","1号大屏2019-04-22 22:13:58关机","1号大屏2019-04-22 22:12:58开机","1号大屏2019-04-22 22:05:28开机","1号大屏2019-04-22 22:05:28关机","1号大屏2019-04-22 22:01:26关机","1号大屏2019-04-22 21:44:22开机","1号大屏2019-04-22 21:43:52关机","1号大屏2019-04-22 21:43:22开机","1号大屏2019-04-22 21:37:51关机","1号大屏2019-04-22 21:35:51开机","1号大屏2019-04-22 21:26:49关机","1号大屏2019-04-22 21:25:49开机","1号大屏2019-04-22 20:25:39关机","1号大屏2019-04-22 19:46:01开机","1号大屏2019-04-22 19:45:31关机","1号大屏2019-04-22 19:41:31开机","1号大屏2019-04-22 19:41:01关机","1号大屏2019-04-22 19:33:59开机","1号大屏2019-04-22 19:26:58关机","1号大屏2019-04-22 19:26:28开机","1号大屏2019-04-22 19:25:28关机","1号大屏2019-04-22 19:24:30开机","1号大屏2019-04-22 19:15:26关机","1号大屏2019-04-22 19:04:54开机","1号大屏2019-04-22 14:26:25关机","1号大屏2019-04-22 14:17:23开机","1号大屏2019-04-22 14:09:26开机","1号大屏2019-04-22 14:09:26关机"],"Time":"2019-04-22"}
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
         * BRunRecord : ["1号大屏2019-04-22 23:59:59关机","1号大屏2019-04-22 23:37:07开机","1号大屏2019-04-22 23:28:35关机","1号大屏2019-04-22 23:27:05开机","1号大屏2019-04-22 23:22:34关机","1号大屏2019-04-22 23:17:03开机","1号大屏2019-04-22 23:00:30关机","1号大屏2019-04-22 22:59:30开机","1号大屏2019-04-22 22:52:59关机","1号大屏2019-04-22 22:32:32开机","1号大屏2019-04-22 22:29:02关机","1号大屏2019-04-22 22:28:31开机","1号大屏2019-04-22 22:26:31关机","1号大屏2019-04-22 22:18:29开机","1号大屏2019-04-22 22:13:58关机","1号大屏2019-04-22 22:12:58开机","1号大屏2019-04-22 22:05:28开机","1号大屏2019-04-22 22:05:28关机","1号大屏2019-04-22 22:01:26关机","1号大屏2019-04-22 21:44:22开机","1号大屏2019-04-22 21:43:52关机","1号大屏2019-04-22 21:43:22开机","1号大屏2019-04-22 21:37:51关机","1号大屏2019-04-22 21:35:51开机","1号大屏2019-04-22 21:26:49关机","1号大屏2019-04-22 21:25:49开机","1号大屏2019-04-22 20:25:39关机","1号大屏2019-04-22 19:46:01开机","1号大屏2019-04-22 19:45:31关机","1号大屏2019-04-22 19:41:31开机","1号大屏2019-04-22 19:41:01关机","1号大屏2019-04-22 19:33:59开机","1号大屏2019-04-22 19:26:58关机","1号大屏2019-04-22 19:26:28开机","1号大屏2019-04-22 19:25:28关机","1号大屏2019-04-22 19:24:30开机","1号大屏2019-04-22 19:15:26关机","1号大屏2019-04-22 19:04:54开机","1号大屏2019-04-22 14:26:25关机","1号大屏2019-04-22 14:17:23开机","1号大屏2019-04-22 14:09:26开机","1号大屏2019-04-22 14:09:26关机"]
         * Time : 2019-04-22
         */

        private String Time;
        private List<String> BRunRecord;

        public String getTime() {
            return Time;
        }

        public void setTime(String Time) {
            this.Time = Time;
        }

        public List<String> getBRunRecord() {
            return BRunRecord;
        }

        public void setBRunRecord(List<String> BRunRecord) {
            this.BRunRecord = BRunRecord;
        }
    }
}
