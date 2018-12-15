package com.kongtiaoapp.xxhj.bean;

/**
 * Created by xxhj_g on 2016/12/9.
 */
public class Report_Running_RecordBean extends RBResponse {

    /**
     * resobj : {"FileUrl":"c88731c582c44acbae72db2281f2b3f6"}
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
         * FileUrl : c88731c582c44acbae72db2281f2b3f6
         */

        private String FileUrl;
        private String CreateTime;
        public String getFileUrl() {
            return FileUrl;
        }

        public void setFileUrl(String FileUrl) {
            this.FileUrl = FileUrl;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String createTime) {
            CreateTime = createTime;
        }
    }
}
