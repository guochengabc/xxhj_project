package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by Luoye on 2016-10-17.
 * 说明:
 */
public class GetSpecialArticleList extends RBResponse {

    /**
     * FaceUrl : images/special/2016/09/13/b59b456.jpg
     * ArticleId : 7413543b7ab841d2ab5c4aeb376b0d17
     * CreateTime : 1473735604
     * SpecialInfoId : 8c1d97ab8ae545d78e27cf002ee9d4fb
     * Title : 节能之前，你一定要知道的20个原则
     * Abstract : 跑步越来越火，人们也总结了越来越多的法则。这些智慧的结晶，将指导人们更好的开展这项运动。下面就给大家介绍跑步的20条黄金原则。
     */

    private List<SpecialArticle> resobj;

    public List<SpecialArticle> getResobj() {
        return resobj;
    }

    public void setResobj(List<SpecialArticle> resobj) {
        this.resobj = resobj;
    }

    public static class SpecialArticle {
        private String FaceUrl;
        private String ArticleId;
        private String CreateTime;
        private String SpecialInfoId;
        private String Title;
        private String Abstract;

        public String getFaceUrl() {
            return FaceUrl;
        }

        public void setFaceUrl(String FaceUrl) {
            this.FaceUrl = FaceUrl;
        }

        public String getArticleId() {
            return ArticleId;
        }

        public void setArticleId(String ArticleId) {
            this.ArticleId = ArticleId;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public String getSpecialInfoId() {
            return SpecialInfoId;
        }

        public void setSpecialInfoId(String SpecialInfoId) {
            this.SpecialInfoId = SpecialInfoId;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }

        public String getAbstract() {
            return Abstract;
        }

        public void setAbstract(String Abstract) {
            this.Abstract = Abstract;
        }
    }
}
