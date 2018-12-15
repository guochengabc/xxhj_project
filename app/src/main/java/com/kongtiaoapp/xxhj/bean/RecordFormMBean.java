package com.kongtiaoapp.xxhj.bean;

import java.util.List;

/**
 * Created by G_XXHJ on 2018/11/22.  记录表单模块
 */

public class RecordFormMBean extends RBResponse {

    /**
     * code : 40000
     * resobj : {"RecordModule":[{"IntentName":"com.kongtiaoapp.xxhj.recordform.activity.SMReadingActivity","PackageName":"com.kongtiaoapp.xxhj","ModuleName":"扫码抄表","IconName":""},{"IntentName":"com.kongtiaoapp.xxhj.recordform.activity.MReadingActivity","PackageName":"com.kongtiaoapp.xxhj","ModuleName":"模块抄表","IconName":""},{"IntentName":"com.kongtiaoapp.xxhj.recordform.activity.AdditionalReadingActivity","PackageName":"com.kongtiaoapp.xxhj","ModuleName":"补录","IconName":""},{"IntentName":"com.kongtiaoapp.xxhj.recordform.activity.ReadingSearchActivity","PackageName":"com.kongtiaoapp.xxhj","ModuleName":"录表查询","IconName":""},{"IntentName":"com.kongtiaoapp.xxhj.recordform.activity.RechargeSearchActivity","PackageName":"com.kongtiaoapp.xxhj","ModuleName":"充值查询","IconName":""},{"IntentName":"com.kongtiaoapp.xxhj.recordform.activity.StatisticSearchActivity","PackageName":"com.zchg.woa","ModuleName":"统计查询","IconName":""},{"IntentName":"com.kongtiaoapp.xxhj.recordform.activity.GasRechargeActivity","PackageName":"com.kongtiaoapp.xxhj","ModuleName":"燃气充值","IconName":""},{"IntentName":"com.kongtiaoapp.xxhj.recordform.activity.ExportFormActivity","PackageName":"com.kongtiaoapp.xxhj","ModuleName":"报表导出","IconName":""}]}
     */

    private ResobjBean resobj;

    public ResobjBean getResobj() {
        return resobj;
    }

    public void setResobj(ResobjBean resobj) {
        this.resobj = resobj;
    }

    public static class ResobjBean {
        private List<RecordModuleBean> RecordModule;

        public List<RecordModuleBean> getRecordModule() {
            return RecordModule;
        }

        public void setRecordModule(List<RecordModuleBean> RecordModule) {
            this.RecordModule = RecordModule;
        }

        public static class RecordModuleBean {
            /**
             * IntentName : com.kongtiaoapp.xxhj.recordform.activity.SMReadingActivity
             * PackageName : com.kongtiaoapp.xxhj
             * ModuleName : 扫码抄表
             * IconName :
             */

            private String IntentName;
            private String PackageName;
            private String ModuleName;
            private String IconName;

            public String getIntentName() {
                return IntentName;
            }

            public void setIntentName(String IntentName) {
                this.IntentName = IntentName;
            }

            public String getPackageName() {
                return PackageName;
            }

            public void setPackageName(String PackageName) {
                this.PackageName = PackageName;
            }

            public String getModuleName() {
                return ModuleName;
            }

            public void setModuleName(String ModuleName) {
                this.ModuleName = ModuleName;
            }

            public String getIconName() {
                return IconName;
            }

            public void setIconName(String IconName) {
                this.IconName = IconName;
            }
        }
    }
}
