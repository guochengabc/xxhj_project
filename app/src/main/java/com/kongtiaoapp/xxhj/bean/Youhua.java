package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by Luoye on 2016-9-29.
 * 说明:
 */
public class Youhua extends RBResponse {

    /**
     * Content : 建议严格制定管理制度，做到系统按需开关机。
     * Title : 优化建议一
     */

    private List<YouHuaList> resobj;

    public List<YouHuaList> getResobj() {
        return resobj;
    }

    public void setResobj(List<YouHuaList> resobj) {
        this.resobj = resobj;
    }

    public static class YouHuaList {
        private String Content;
        private String Title;

        public String getContent() {
            return Content;
        }

        public void setContent(String Content) {
            this.Content = Content;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }
    }
}
