package com.kongtiaoapp.xxhj.bean;

/**
 * Created by Luoye on 2016-10-8.
 * 说明:
 */
public class EnergyShowDetail extends RBResponse {

    /**
     * ExhibitionInfo : 爱因斯坦：我想艾萨克爵士是错的！
     马斯克：我们不应该世世代代居住在地球上！
     马丁·路德·金：我们不该活在一个以肤色评判人的世界！
     乔布斯：这不是我想要的手机！
     * CoHost :
     * FaceUrl : images/exhibition/2016/09/09/eea530de4c654e7aa2ed0c850d4d0304.jpg
     * Time : 1473411973
     * Host : 天使湾创投
     * ExhibitionId : 25efd8561e7e4a3cabddd1ddb13ea3f5
     * IsCollection : 0
     * Sponsor : SegmentFault
     * Abstract : 除非世界如我所愿：聚变计划
     */

    private DetailBean resobj;

    public DetailBean getResobj() {
        return resobj;
    }

    public void setResobj(DetailBean resobj) {
        this.resobj = resobj;
    }

    public static class DetailBean {
        private String ExhibitionInfo;
        private String CoHost;
        private String FaceUrl;
        private String Time;
        private String Host;
        private String ExhibitionId;
        private String IsCollection;
        private String Sponsor;
        private String Abstract;
        private String Place;

        public String getPlace() {
            return Place;
        }

        public void setPlace(String place) {
            Place = place;
        }

        public String getExhibitionInfo() {
            return ExhibitionInfo;
        }

        public void setExhibitionInfo(String ExhibitionInfo) {
            this.ExhibitionInfo = ExhibitionInfo;
        }

        public String getCoHost() {
            return CoHost;
        }

        public void setCoHost(String CoHost) {
            this.CoHost = CoHost;
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

        public String getHost() {
            return Host;
        }

        public void setHost(String Host) {
            this.Host = Host;
        }

        public String getExhibitionId() {
            return ExhibitionId;
        }

        public void setExhibitionId(String ExhibitionId) {
            this.ExhibitionId = ExhibitionId;
        }

        public String getIsCollection() {
            return IsCollection;
        }

        public void setIsCollection(String IsCollection) {
            this.IsCollection = IsCollection;
        }

        public String getSponsor() {
            return Sponsor;
        }

        public void setSponsor(String Sponsor) {
            this.Sponsor = Sponsor;
        }

        public String getAbstract() {
            return Abstract;
        }

        public void setAbstract(String Abstract) {
            this.Abstract = Abstract;
        }
    }
}
