package com.kongtiaoapp.xxhj.afinal;

import com.alibaba.fastjson.JSON;
import com.kongtiaoapp.xxhj.App;

import java.util.List;

/**
 * Created by xxhj_g on 2017/8/1.
 * 用户管理权限表及职位   校验表
 * BB暖通空调管理员   BC暖通操作员  CB工单管理员 CC工单普通员工
 */

public class UserManegerList {

    /**
     * 暖通空调模块
     */
    public static boolean HVAC() {

        String role = App.sp.getRoles();
        List roles = JSON.parseArray(role, String.class);
        if (roles.contains("BB") || roles.contains("BC")) {
            return true;
        }
        return false;
    }

    /**
     * 暖通空调模块管理员
     */
    public static boolean HVAC_Manager() {
        String role = App.sp.getRoles();
        List roles = JSON.parseArray(role, String.class);
        if (roles.contains("BB")) {
            return true;
        }
        return false;
    }

    /**
     * 暖通空调模块操作员
     */
    public static boolean HVAC_Operate() {
        String role = App.sp.getRoles();
        List roles = JSON.parseArray(role, String.class);
        if (roles.contains("BC")) {
            return true;
        }
        return false;
    }

    /**
     * 工单维修模块
     */
    public static boolean WORKORDER() {
        String role = App.sp.getRoles();
        List roles = JSON.parseArray(role, String.class);
        if (roles.contains("CB") || roles.contains("CC")) {
            return true;
        }
        return false;
    }


    /**
     * 工单维修模块  管理员
     */
    public static boolean WORKORDER_MANAGER() {
        String role = App.sp.getRoles();
        List roles = JSON.parseArray(role, String.class);
        if (roles.contains("CB")) {
            return true;
        }
        return false;
    }

    /**
     * 工单维修模块  工单编辑员，可以随意修改工单相关操作
     */
    public static boolean WORKORDER_EDITOR() {
        String roleArray = App.sp.getRoles();
        List roles = JSON.parseArray(roleArray, String.class);
        for (int i = 0; i < roles.size(); i++) {
            Object roleObj = roles.get(i)==null?"":roles.get(i);
            String role = roleObj.toString();
            if (role.contains("CBB")){
                return true;
            }
        }
        return false;
    }

    /**
     * 工单维修模块  工程师
     */
    public static boolean WORKORDER_ENGI() {
        String roleArray = App.sp.getRoles();
        List roles = JSON.parseArray(roleArray, String.class);
        for (int i = 0; i < roles.size(); i++) {
            Object roleObj = roles.get(i)==null?"":roles.get(i);
            String role = roleObj.toString();
            if (role.contains("CCB")){
                return true;
            }
        }
        return false;
    }

    /**
     * 工单维修模块  调度员
     */
    public static boolean WORKORDER_DISP() {
        String roleArray = App.sp.getRoles();
        List roles = JSON.parseArray(roleArray, String.class);
        for (int i = 0; i < roles.size(); i++) {
            Object roleObj = roles.get(i)==null?"":roles.get(i);
            String role = roleObj.toString();
            if (role.contains("CCC")){
                return true;
            }
        }
        return false;
    }

    /**
     * 工单维修模块  质检员
     */
    public static boolean WORKORDER_INSP() {
        String roleArray = App.sp.getRoles();
        List roles = JSON.parseArray(roleArray, String.class);
        for (int i = 0; i < roles.size(); i++) {
            Object roleObj = roles.get(i)==null?"":roles.get(i);
            String role = roleObj.toString();
            if (role.contains("CCD")){
                return true;
            }
        }
        return false;
    }

    /**
     * 总厂长
     */
    public static boolean TOTALFACTORY() {
        String roles = App.sp.getRoles();
        if (roles.contains("FA")) {
            return true;
        }
        return false;
    }

    /**
     * 分厂长
     */
    public static boolean FENFACTORY() {
        String roles = App.sp.getRoles();
        if (roles.contains("FB")) {
            return true;
        }
        return false;
    }

    /**
     * 普通员工
     */
    public static boolean OPERATEFACTORY() {
        String roles = App.sp.getRoles();
        if (roles.contains("FC")) {
            return true;
        }
        return false;
    }
}
