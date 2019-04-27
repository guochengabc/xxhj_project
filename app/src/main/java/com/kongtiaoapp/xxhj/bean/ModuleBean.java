package com.kongtiaoapp.xxhj.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by G_XXHJ on 2018/10/13.  首页模块
 */

public class ModuleBean extends RBResponse {

    /**
     * code : 40000
     * resobj : {"MainArray":[{"MainName":"首页","MainFragment":"com.kongtiaoapp.xxhj.base.MainFragment","ModuleArray":[{"ModuleName":"领导专栏","IconName":"ldzl","IntentName":"com.kongtiaoapp.xxhj.leader.LeaderTalkActivity"},{"ModuleName":"交流专栏","IconName":"jlzl","IntentName":"com.kongtiaoapp.xxhj.activites.More_CommentsActivity"},{"ModuleName":"暖通空调","IconName":"ntkt","IntentName":"com.kongtiaoapp.xxhj.hvac.HVAC_NewActivity"},{"ModuleName":"能源管理","IconName":"nygl","IntentName":"com.kongtiaoapp.xxhj.energymetering.activity.EnergyRecordListActivity"},{"ModuleName":"变配电","IconName":"bpd","IntentName":"com.kongtiaoapp.xxhj.bpd.BPDActivity"},{"ModuleName":"工单管理","IconName":"gdgl","IntentName":"com.zchg.woa.view.display.main.MainActivity"},{"ModuleName":"消息中心","IconName":"xxzx","IntentName":"com.kongtiaoapp.xxhj.activites.InformationActivity"},{"ModuleName":"安防监控","IconName":"afjk","IntentName":"com.kongtiaoapp.xxhj.securitymonitoring.SecurityMonitoringActivity"}]},{"MainName":"工单管理","MainFragment":"com.kongtiaoapp.xxhj.workorder.fragment.WorkOrderFragment","ModuleArray":[]},{"MainName":"通讯录","MainFragment":"com.kongtiaoapp.xxhj.fragment.AddressListFragment","ModuleArray":[]},{"MainName":"我的","MainFragment":"com.kongtiaoapp.xxhj.fragment.MeFragment","ModuleArray":[]}]}
     */

    private ResobjBean resobj;

    public ResobjBean getResobj() {
        return resobj;
    }

    public void setResobj(ResobjBean resobj) {
        this.resobj = resobj;
    }

    public static class ResobjBean {
        private List<MainArrayBean> MainArray;

        public List<MainArrayBean> getMainArray() {
            return MainArray;
        }

        public void setMainArray(List<MainArrayBean> MainArray) {
            this.MainArray = MainArray;
        }

        public static class MainArrayBean implements Serializable{
            /**
             * MainName : 首页
             * MainFragment : com.kongtiaoapp.xxhj.base.MainFragment
             * ModuleArray : [{"ModuleName":"领导专栏","IconName":"ldzl","IntentName":"com.kongtiaoapp.xxhj.leader.LeaderTalkActivity"},{"ModuleName":"交流专栏","IconName":"jlzl","IntentName":"com.kongtiaoapp.xxhj.activites.More_CommentsActivity"},{"ModuleName":"暖通空调","IconName":"ntkt","IntentName":"com.kongtiaoapp.xxhj.hvac.HVAC_NewActivity"},{"ModuleName":"能源管理","IconName":"nygl","IntentName":"com.kongtiaoapp.xxhj.energymetering.activity.EnergyRecordListActivity"},{"ModuleName":"变配电","IconName":"bpd","IntentName":"com.kongtiaoapp.xxhj.bpd.BPDActivity"},{"ModuleName":"工单管理","IconName":"gdgl","IntentName":"com.zchg.woa.view.display.main.MainActivity"},{"ModuleName":"消息中心","IconName":"xxzx","IntentName":"com.kongtiaoapp.xxhj.activites.InformationActivity"},{"ModuleName":"安防监控","IconName":"afjk","IntentName":"com.kongtiaoapp.xxhj.securitymonitoring.SecurityMonitoringActivity"}]
             */

            private String MainName;
            private String MainFragment;
            private List<ModuleArrayBean> ModuleArray;

            public String getMainName() {
                return MainName;
            }

            public void setMainName(String MainName) {
                this.MainName = MainName;
            }

            public String getMainFragment() {
                return MainFragment;
            }

            public void setMainFragment(String MainFragment) {
                this.MainFragment = MainFragment;
            }

            public List<ModuleArrayBean> getModuleArray() {
                return ModuleArray;
            }

            public void setModuleArray(List<ModuleArrayBean> ModuleArray) {
                this.ModuleArray = ModuleArray;
            }

            public static class ModuleArrayBean {
                /**
                 * ModuleName : 领导专栏
                 * IconName : ldzl
                 * IntentName : com.kongtiaoapp.xxhj.leader.LeaderTalkActivity
                 */

                private String ModuleName;
                private String IconName;
                private String IntentName;
                private String PackageName;
                private String ProjectType;
                private String ProjectId;

                public String getProjectType() {
                    return ProjectType;
                }

                public void setProjectType(String projectType) {
                    ProjectType = projectType;
                }

                public String getProjectId() {
                    return ProjectId;
                }

                public void setProjectId(String projectId) {
                    ProjectId = projectId;
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

                public String getIntentName() {
                    return IntentName;
                }

                public void setIntentName(String IntentName) {
                    this.IntentName = IntentName;
                }

                public String getPackageName() {
                    return PackageName;
                }

                public void setPackageName(String packageName) {
                    PackageName = packageName;
                }
            }
        }
    }
}
