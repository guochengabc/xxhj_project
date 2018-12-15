package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by Luoye on 2016-10-8.
 * 说明:
 */
public class EnergyShow extends RBResponse {

    /**
     * FaceUrl : images/exhibition/2016/09/09/eea530de4c654e7aa2ed0c850d4d0304.jpg
     * Time : 1473411973
     * ExhibitionId : 25efd8561e7e4a3cabddd1ddb13ea3f5
     * Abstract : 除非世界如我所愿：聚变计划
     */

    private List<EnergyShowList> resobj;

    public List<EnergyShowList> getResobj() {
        return resobj;
    }

    public void setResobj(List<EnergyShowList> resobj) {
        this.resobj = resobj;
    }

    public static class EnergyShowList {
        private String FaceUrl;
        private String Time;
        private String ExhibitionId;
        private String Abstract;
        private String Host;
        private String Place;

        public String getHost() {
            return Host;
        }

        public void setHost(String host) {
            Host = host;
        }

        public String getPlace() {
            return Place;
        }

        public void setPlace(String place) {
            Place = place;
        }

        public String getFaceUrl() {
            return FaceUrl;
        }

        public void setFaceUrl(String FaceUrl) {
            this.FaceUrl = FaceUrl;
        }

        public String getTime() {
            return Time;
        }

        public void setTime(String Time) {
            this.Time = Time;
        }

        public String getExhibitionId() {
            return ExhibitionId;
        }

        public void setExhibitionId(String ExhibitionId) {
            this.ExhibitionId = ExhibitionId;
        }

        public String getAbstract() {
            return Abstract;
        }

        public void setAbstract(String Abstract) {
            this.Abstract = Abstract;
        }
    }
}
