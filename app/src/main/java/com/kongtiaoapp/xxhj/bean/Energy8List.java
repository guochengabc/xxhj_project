package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by Luoye on 2016-10-10.
 * 说明:
 */
public class Energy8List extends RBResponse {

    /**
     * FaceUrl : images/special/2016/09/13/c08171a660454b018033a8560121a209.jpg
     * SpecialInfoId : 8c1d97ab8ae545d78e27cf002ee9d4fb
     * Title : 节能训练
     * Abstract : 节能之前，你一定要知道的20个原则
     */

    private List<Energy8> resobj;

    public List<Energy8> getResobj() {
        return resobj;
    }

    public void setResobj(List<Energy8> resobj) {
        this.resobj = resobj;
    }

    public static class Energy8 {
        private String FaceUrl;
        private String SpecialInfoId;
        private String Title;
        private String Abstract;
        private String IsCollection;
        private String SelectedInfoId;

        public String getIsCollection() {
            return IsCollection;
        }

        public void setIsCollection(String isCollection) {
            IsCollection = isCollection;
        }

        public String getFaceUrl() {
            return FaceUrl;
        }

        public void setFaceUrl(String FaceUrl) {
            this.FaceUrl = FaceUrl;
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

        public String getSelectedInfoId() {
            return SelectedInfoId;
        }

        public void setSelectedInfoId(String selectedInfoId) {
            SelectedInfoId = selectedInfoId;
        }
    }
}
