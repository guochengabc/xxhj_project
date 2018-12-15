package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by xxhj_g on 2017/3/30.
 */
public class GetGroupProjectListBean extends RBResponse {

    /**
     * code : 40000
     * resobj : {"ProjectType":"B","Param":[{"Text":"供回水温差","Code":"TempD"},{"Text":"系统COP","Code":"SCOP"},{"Text":"总能耗","Code":"TEC"},{"Text":"单位能耗","Code":"UEC"},{"Text":"运行时长","Code":"DUR"},{"Text":"温度满意度","Code":"TempR"},{"Text":"空气新鲜度","Code":"AirCR"}],"Data":[{"Arr":["--","--","12.29","12.29","--","12.29","--"],"ProjectId":"afd559c56eb84e198d1d5d950029d557","OrgName":"郑州二七万达"},{"Arr":["49.58","49.58","12.29","12.29","12.29","12.29","49.58"],"ProjectId":"9eda3e8bd526582e4a9470fea3578703","OrgName":"新时代大厦"},{"Arr":["49.58","49.58","12.29","12.29","12.29","12.29","49.58"],"ProjectId":"70ea6a3abe69f4e7967d9b90ef05c3f1","OrgName":"天津矿业大厦"},{"Arr":["49.58","49.58","12.29","12.29","12.29","12.29","49.58"],"ProjectId":"73b157821d95647dc56ad595218c2742","OrgName":"数码大厦"},{"Arr":["49.58","49.58","12.29","12.29","12.29","12.29","49.58"],"ProjectId":"dbfb3ba5874ccb7b6732464a68142fa9","OrgName":"盛福大厦"},{"Arr":["49.58","49.58","12.29","12.29","12.29","12.29","49.58"],"ProjectId":"a1eaf1c169649d795cfed61ac78d03fb","OrgName":"朗琴国际"},{"Arr":["49.58","49.58","12.29","12.29","12.29","12.29","49.58"],"ProjectId":"1c6c50a262a7f56093e8436d47fe3dba","OrgName":"建达大厦"},{"Arr":["--","--","--","--","--","--","--"],"ProjectId":"f25398f352d581fe6b95d0f59fd5d0ac","OrgName":"轨道交通大厦"},{"Arr":["49.58","49.58","12.29","12.29","12.29","12.29","49.58"],"ProjectId":"b38f78c7d518b56546457f2d9c2c6e1b","OrgName":"大唐国际大厦"},{"Arr":["--","--","--","--","--","--","--"],"ProjectId":"273fc1fd4faef8cc1af758e3cb7f0d86","OrgName":"财富中心"},{"Arr":["49.58","49.58","12.29","12.29","12.29","12.29","49.58"],"ProjectId":"670a5988899b47cd94e792e33f82cfca","OrgName":"北京市望京万达"}]}
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
         * ProjectType : B
         * Param : [{"Text":"供回水温差","Code":"TempD"},{"Text":"系统COP","Code":"SCOP"},{"Text":"总能耗","Code":"TEC"},{"Text":"单位能耗","Code":"UEC"},{"Text":"运行时长","Code":"DUR"},{"Text":"温度满意度","Code":"TempR"},{"Text":"空气新鲜度","Code":"AirCR"}]
         * Data : [{"Arr":["--","--","12.29","12.29","--","12.29","--"],"ProjectId":"afd559c56eb84e198d1d5d950029d557","OrgName":"郑州二七万达"},{"Arr":["49.58","49.58","12.29","12.29","12.29","12.29","49.58"],"ProjectId":"9eda3e8bd526582e4a9470fea3578703","OrgName":"新时代大厦"},{"Arr":["49.58","49.58","12.29","12.29","12.29","12.29","49.58"],"ProjectId":"70ea6a3abe69f4e7967d9b90ef05c3f1","OrgName":"天津矿业大厦"},{"Arr":["49.58","49.58","12.29","12.29","12.29","12.29","49.58"],"ProjectId":"73b157821d95647dc56ad595218c2742","OrgName":"数码大厦"},{"Arr":["49.58","49.58","12.29","12.29","12.29","12.29","49.58"],"ProjectId":"dbfb3ba5874ccb7b6732464a68142fa9","OrgName":"盛福大厦"},{"Arr":["49.58","49.58","12.29","12.29","12.29","12.29","49.58"],"ProjectId":"a1eaf1c169649d795cfed61ac78d03fb","OrgName":"朗琴国际"},{"Arr":["49.58","49.58","12.29","12.29","12.29","12.29","49.58"],"ProjectId":"1c6c50a262a7f56093e8436d47fe3dba","OrgName":"建达大厦"},{"Arr":["--","--","--","--","--","--","--"],"ProjectId":"f25398f352d581fe6b95d0f59fd5d0ac","OrgName":"轨道交通大厦"},{"Arr":["49.58","49.58","12.29","12.29","12.29","12.29","49.58"],"ProjectId":"b38f78c7d518b56546457f2d9c2c6e1b","OrgName":"大唐国际大厦"},{"Arr":["--","--","--","--","--","--","--"],"ProjectId":"273fc1fd4faef8cc1af758e3cb7f0d86","OrgName":"财富中心"},{"Arr":["49.58","49.58","12.29","12.29","12.29","12.29","49.58"],"ProjectId":"670a5988899b47cd94e792e33f82cfca","OrgName":"北京市望京万达"}]
         */

        private String ProjectType;
        private List<ParamBean> Param;
        private List<DataBean> Data;

        public String getProjectType() {
            return ProjectType;
        }

        public void setProjectType(String ProjectType) {
            this.ProjectType = ProjectType;
        }

        public List<ParamBean> getParam() {
            return Param;
        }

        public void setParam(List<ParamBean> Param) {
            this.Param = Param;
        }

        public List<DataBean> getData() {
            return Data;
        }

        public void setData(List<DataBean> Data) {
            this.Data = Data;
        }

        public static class ParamBean {
            /**
             * Text : 供回水温差
             * Code : TempD
             */

            private String Text;
            private String Code;

            public String getText() {
                return Text;
            }

            public void setText(String Text) {
                this.Text = Text;
            }

            public String getCode() {
                return Code;
            }

            public void setCode(String Code) {
                this.Code = Code;
            }
        }

        public static class DataBean {
            /**
             * Arr : ["--","--","12.29","12.29","--","12.29","--"]
             * ProjectId : afd559c56eb84e198d1d5d950029d557
             * OrgName : 郑州二七万达
             */

            private String ProjectId;
            private String OrgName;
            private List<String> Arr;

            public String getProjectId() {
                return ProjectId;
            }

            public void setProjectId(String ProjectId) {
                this.ProjectId = ProjectId;
            }

            public String getOrgName() {
                return OrgName;
            }

            public void setOrgName(String OrgName) {
                this.OrgName = OrgName;
            }

            public List<String> getArr() {
                return Arr;
            }

            public void setArr(List<String> Arr) {
                this.Arr = Arr;
            }
        }
    }
}
