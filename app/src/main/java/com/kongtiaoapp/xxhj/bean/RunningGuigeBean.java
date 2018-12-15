package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by G_XXHJ on 2017/12/14.
 */

public class RunningGuigeBean extends RBResponse {

    /**
     * code : 40000
     * resobj : [{"Desc":"支路热水供回水温差偏小，热水流量偏大，建议关小支路阀门或调低支路水泵转速,系统水力平衡良好"}]
     */

    private List<ResobjBean> resobj;

    public List<ResobjBean> getResobj() {
        return resobj;
    }

    public void setResobj(List<ResobjBean> resobj) {
        this.resobj = resobj;
    }

    public static class ResobjBean {
        /**
         * Desc : 支路热水供回水温差偏小，热水流量偏大，建议关小支路阀门或调低支路水泵转速,系统水力平衡良好
         */

        private String Desc;

        public String getDesc() {
            return Desc;
        }

        public void setDesc(String Desc) {
            this.Desc = Desc;
        }
    }
}
