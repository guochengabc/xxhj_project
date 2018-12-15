package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by xxhj_g on 2017/9/16.  运行状态标准
 */

public class RunStatusBean {
    private List<StatusBean> list;//里面存有分数和状态的标准
    private List<CopBean> list_cop;
    private float relaScore;//真实分数
    private String paintContent;//图例

    public String getPaintContent() {
        return paintContent;
    }

    public void setPaintContent(String paintContent) {
        this.paintContent = paintContent;
    }

    public List<StatusBean> getList() {
        return list;
    }

    public void setList(List<StatusBean> list) {
        this.list = list;
    }

    public float getRelaScore() {
        return relaScore;
    }

    public void setRelaScore(float relaScore) {
        this.relaScore = relaScore;
    }

    public List<CopBean> getList_cop() {
        return list_cop;
    }

    public void setList_cop(List<CopBean> list_cop) {
        this.list_cop = list_cop;
    }

    /*运行标准*/
    public static class StatusBean {
        private float scoreB;//分数
        private String statusB;//状态标准  优良中差

        public float getScoreB() {
            return scoreB;
        }

        public void setScoreB(float scoreB) {
            this.scoreB = scoreB;
        }

        public String getStatusB() {
            return statusB;
        }

        public void setStatusB(String statusB) {
            this.statusB = statusB;
        }
    }

    public static class CopBean {
        private float copRealScore;//cop的实际分数
        private String copRealContent;//cop的实际内容
        private float copStandardScore;//cop的国标分数
        private String copStandardContent;//cop的国标内容
        private float scopRealScore;//scop的实际分数
        private String scopRealContent;//scop的实际内容
        private float scopStandardScore;//scop的国标分数
        private String scopStandardContent;//scop的国标内容

        public float getCopRealScore() {
            return copRealScore;
        }

        public void setCopRealScore(float copRealScore) {
            this.copRealScore = copRealScore;
        }

        public String getCopRealContent() {
            return copRealContent;
        }

        public void setCopRealContent(String copRealContent) {
            this.copRealContent = copRealContent;
        }

        public float getCopStandardScore() {
            return copStandardScore;
        }

        public void setCopStandardScore(float copStandardScore) {
            this.copStandardScore = copStandardScore;
        }

        public String getCopStandardContent() {
            return copStandardContent;
        }

        public void setCopStandardContent(String copStandardContent) {
            this.copStandardContent = copStandardContent;
        }

        public float getScopRealScore() {
            return scopRealScore;
        }

        public void setScopRealScore(float scopRealScore) {
            this.scopRealScore = scopRealScore;
        }

        public String getScopRealContent() {
            return scopRealContent;
        }

        public void setScopRealContent(String scopRealContent) {
            this.scopRealContent = scopRealContent;
        }

        public float getScopStandardScore() {
            return scopStandardScore;
        }

        public void setScopStandardScore(float scopStandardScore) {
            this.scopStandardScore = scopStandardScore;
        }

        public String getScopStandardContent() {
            return scopStandardContent;
        }

        public void setScopStandardContent(String scopStandardContent) {
            this.scopStandardContent = scopStandardContent;
        }
    }
}
