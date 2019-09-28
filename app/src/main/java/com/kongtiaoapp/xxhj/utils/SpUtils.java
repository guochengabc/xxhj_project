package com.kongtiaoapp.xxhj.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SpUtils {

    private SharedPreferences sp;
    private Editor editors;

    public SpUtils(Context context) {
        sp = context.getSharedPreferences("air", Context.MODE_PRIVATE);
        if (editors == null) {
            editors = sp.edit();
        }
    }

    /**
     * 移除SharedPreferences
     *
     * @param pre 参数名
     */
    public void removeSp(String pre) {
        editors.remove(pre);
        editors.commit();
    }


    /**
     * 重置SharedPreferences
     */
    public void resetSp() {
        editors.remove("projectId");
        editors.remove("projectAuto");
        editors.remove("headpath");
        editors.remove("name");
        editors.remove("projectZD");
        editors.remove("diag_date");
        editors.remove("projectType");
        editors.remove("projectName");
        editors.remove("isChangeing");
        editors.commit();
    }

    /**
     * 设置是否为第一次登录
     *
     * @param isFirstLogoin
     */
    public void setFirstLogoin(boolean isFirstLogoin) {
        editors.putBoolean("isFirstLogoin", isFirstLogoin);
        editors.commit();
    }

    public boolean isFirstLogoin() {
        return sp.getBoolean("isFirstLogoin", true);
    }


    /**
     * 设置登录状态
     *
     * @param isLogin
     */
    public void setLoginState(boolean isLogin) {
        editors.putBoolean("isLogin", isLogin);
        editors.commit();
    }

    public boolean isLogin() {
        return sp.getBoolean("isLogin", false);
    }


    /**
     * 设置用户ID
     */
    public void setUid(String uid) {
        editors.putString("uid", uid);
        editors.commit();
    }

    public String getUid() {
        return sp.getString("uid", null);
    }


    /**
     * 设置用户头像
     *
     * @param path
     */
    public void setHeadPath(String path) {
        editors.putString("headpath", path);
        editors.commit();
    }

    public String getHeadPath() {
        return sp.getString("headpath", "0");
    }

    /**
     * 用户名
     *
     * @param name
     */
    public void setName(String name) {
        editors.putString("name", name);
        editors.commit();
    }

    public String getName() {
        return sp.getString("name", "");
    }

    /**
     * 设置用户手机号
     *
     * @param phone
     */
    public void setPhone(String phone) {
        editors.putString("phone", phone);
        editors.commit();
    }

    public String getPhone() {
        return sp.getString("phone", "");
    }
    /**
     * 设置用户密码
     *
     * @param pwd
     */
    public void setPwd(String pwd) {
        editors.putString("pwd", pwd);
        editors.commit();
    }

    public String getPwd() {
        return sp.getString("pwd", "");
    }
    /**
     * 设置是否为领导
     *
     *
     * @param isLeader
     */
    public void setLeader(String isLeader) {
        editors.putString("isLeader", isLeader);
        editors.commit();
    }

    public String isLeader() {
        return sp.getString("isLeader", "");
    }

    /**
     * 设置项目ID
     *
     * @param projectId
     */
    public void setProjectId(String projectId) {
        editors.putString("projectId", projectId);
        editors.commit();
    }

    public String getProjectId() {
        return sp.getString("projectId", "");
    }

    /**
     * 检查版本号
     *
     * @param version
     */
    public void setCheckVersion(String version) {
        editors.putString("version", version);
        editors.commit();
    }

    public String getCheckVersion() {
        return sp.getString("version", "");
    }

    /**
     * 保存更新url
     *
     * @param url
     */
    public void setUrl(String url) {
        editors.putString("url", url);
        editors.commit();
    }

    public String getUrl() {
        return sp.getString("url", "");
    }

    /**
     * 是否在更新
     *
     * @param isLoad
     */
    public void isLoad(boolean isLoad) {
        editors.putBoolean("isLoad", isLoad);
        editors.commit();
    }

    public boolean getLoad() {
        return sp.getBoolean("isLoad", false);
    }

    /**
     * 更新多少
     *
     * @param count
     */
    public void setCheckCount(String count) {
        editors.putString("checkCount", count);
        editors.commit();
    }

    public String getCheckCount() {
        return sp.getString("checkCount", "0");
    }

    /**
     * 设置主页的诊断
     *
     * @param projectZD
     */
    public void setProjectZD(String projectZD) {
        editors.putString("projectZD", projectZD);
        editors.commit();
    }

    public String getProjectZD() {
        return sp.getString("projectZD", "");
    }

    /**
     * 设备是否自动采集
     */
    public void setProjectAuto(String projectAuto) {
        editors.putString("projectAuto", projectAuto);
        editors.commit();
    }

    public boolean isAuto() {
        return "A".equals(sp.getString("projectAuto", "A")) ? false : true;
    }

    /**
     * 诊断图查询时间
     *
     * @param date
     */
    public void setDate(String date) {
        editors.putString("diag_date", date);
        editors.commit();
    }

    public String getDate() {
        return sp.getString("diag_date", "");
    }

    /**
     * 诊断图查询时间
     *
     * @param date
     */
    public void setDateEnergy(String date) {
        editors.putString("diag_date_down", date);
        editors.commit();
    }

    public String getDateEnergy() {
        return sp.getString("diag_date_down", "");
    }

    /**
     * 设置项目类型
     *
     * @param projectType
     */
    public void setProjectType(String projectType) {
        editors.putString("projectType", projectType);
        editors.commit();
    }

    public String getProjectType() {
        return sp.getString("projectType", "");
    }

    public void setProjectName(String projectName) {
        editors.putString("projectName", projectName);
        editors.commit();
    }

    public String getProjectName() {
        return sp.getString("projectName", "");
    }

    /**
     * 设置切换项目的状态
     *
     * @param isChangeing
     */
    public void setIsChanging(String isChangeing) {
        editors.putString("isChangeing", isChangeing);//setIsChanging==1代表切换成功   setIsChanging=0代表切换失败或者没有切换
        editors.commit();
    }

    public String getIsChanging() {
        return sp.getString("isChangeing", "");
    }

    /**
     * 设置保存token
     *
     * @param tokening
     */
    public void setToken(String tokening) {
        editors.putString("tokening", tokening);//setIsChanging==1代表切换成功   setIsChanging=0代表切换失败或者没有切换
        editors.commit();
    }

    public String getToken() {
        return sp.getString("tokening", "");
    }

    /**
     * 设置是否是第一次进入界面保存token
     *
     * @param IsFirstToken
     */
    public void setIsFirstToken(String IsFirstToken) {
        editors.putString("IsFirstToken", IsFirstToken);//setIsChanging==1代表切换成功   setIsChanging=0代表切换失败或者没有切换
        editors.commit();
    }

    public String getIsFirstToken() {
        return sp.getString("IsFirstToken", "no_no");
    }

    /**
     * 获取值班人员的手机号
     *
     * @param cPhone
     */
    public void setCphone(String cPhone) {
        editors.putString("cPhone", cPhone);//setIsChanging==1代表切换成功   setIsChanging=0代表切换失败或者没有切换
        editors.commit();
    }

    public String getCphone() {
        return sp.getString("cPhone", "暂无");
    }

    /**
     * 获取值班人员姓名
     *
     * @param uName
     */
    public void setUname(String uName) {
        editors.putString("uName", uName);//setIsChanging==1代表切换成功   setIsChanging=0代表切换失败或者没有切换
        editors.commit();
    }

    public String getUname() {
        return sp.getString("uName", "暂无");
    }

    /**
     * 获取项目类别
     *
     * @param pType
     */
    public void setpType(String pType) {
        editors.putString("pType", pType);//setIsChanging==1代表切换成功   setIsChanging=0代表切换失败或者没有切换
        editors.commit();
    }

    public String getpType() {
        return sp.getString("pType", "C");
    }

    /**
     * 是否刷新  yes代表刷新  no代表不刷新
     *
     * @param refresh
     */
    public void setRefresh(String refresh) {
        editors.putString("refresh", refresh);//setIsChanging==1代表切换成功   setIsChanging=0代表切换失败或者没有切换
        editors.commit();
    }

    public String getRefresh() {
        return sp.getString("refresh", "no");
    }

    /**
     * 设置更新条目
     *
     * @param rStatus
     */
    public void setRStatus(String rStatus) {
        editors.putString("rStatus", rStatus);//setIsChanging==1代表切换成功   setIsChanging=0代表切换失败或者没有切换
        editors.commit();
    }

    public String getRStatus() {
        return sp.getString("rStatus", "1");
    }

    /**
     * 表的类型
     *
     * @param paintType
     */
    public void setPaintType(String paintType) {
        editors.putString("paintType", paintType);//setIsChanging==1代表切换成功   setIsChanging=0代表切换失败或者没有切换
        editors.commit();
    }

    public String getPaintType() {
        return sp.getString("paintType", "no");
    }

    /**
     * 回到主页进入运行界面
     *
     * @param enterRunning
     */
    public void setEnterRunning(String enterRunning) {
        editors.putString("enterRunning", enterRunning);
        editors.commit();
    }

    public String getEnterRunning() {
        return sp.getString("enterRunning", "no");
    }

    /**
     * 修改设备列表的字体大小
     *
     * @param fontSize
     */
    public void setFontSize(String fontSize) {
        editors.putString("fontSize", fontSize);//setIsChanging==1代表切换成功   setIsChanging=0代表切换失败或者没有切换
        editors.commit();
    }

    public String getFontSize() {
        return sp.getString("fontSize", "0");//0代表正常字体  1代表中号字体  2代表大号字体
    }


    /**
     * 保存用户权限和用户职位
     *
     * @param roles
     */
    public void setRole(String roles) {
        editors.putString("roles", roles);//setIsChanging==1代表切换成功   setIsChanging=0代表切换失败或者没有切换
        editors.commit();
    }

    public String getRoles() {
        return sp.getString("roles", "BB");//0代表正常字体  1代表中号字体  2代表大号字体
    }

    /**
     * 保存用户是否在mainActivity界面
     *
     * @param isMain
     */
    public void setIsMain(String isMain) {
        editors.putString("isMain", isMain);//setIsChanging==1代表切换成功   setIsChanging=0代表切换失败或者没有切换
        editors.commit();
    }

    public String getIsMain() {
        return sp.getString("isMain", "true");//true代表在activity界面  false代表不在activity界面
    }

    /**
     * 保存用户是否在mainActivity界面的第二个fragment
     *
     * @param isMainSecond
     */
    public void setIsMainSecond(String isMainSecond) {
        editors.putString("isMain", isMainSecond);//setIsChanging==1代表切换成功   setIsChanging=0代表切换失败或者没有切换
        editors.commit();
    }

    public String getIsMainSecond() {
        return sp.getString("isMainSecond", "true");//true代表在activity界面  false代表不在activity界面
    }

    /**
     * 保存用户在主页的mainFragment时候走onPause
     *
     * @param isMainPause
     */
    public void setIsMainPause(String isMainPause) {
        editors.putString("isMainPause", isMainPause);//setIsChanging==1代表切换成功   setIsChanging=0代表切换失败或者没有切换
        editors.commit();
    }

    public String getIsMainPause() {
        return sp.getString("isMainPause", "false");//true代表在activity界面  false代表不在activity界面
    }

    /**
     * 保存项目类型
     *
     * @param types
     */
    public void setProjectTypeFinish(String types) {
        editors.putString("types", types);//setIsChanging==1代表切换成功   setIsChanging=0代表切换失败或者没有切换
        editors.commit();
    }

    public String getProjectTypeFinish() {
        return sp.getString("types", "");//true代表在activity界面  false代表不在activity界面
    }

    /**
     * 配置环境 正式  测试  本地 三种环境
     *
     * @param types
     */
    public void setEnvironment(String types) {
        editors.putString("environment", types);//setIsChanging==1代表切换成功   setIsChanging=0代表切换失败或者没有切换
        editors.commit();
    }

    public String getEnvironment() {
        return sp.getString("environment", "https://api.xiaoxitech.cn/v3/Process");//true代表在activity界面  false代表不在activity界面
    }

    /**
     * 配置环境 正式  测试  本地 三种环境  图片等相关文件
     *
     * @param types
     */
    public void setEnvironmentImageDocument(String types) {
        editors.putString("environment_document", types);//setIsChanging==1代表切换成功   setIsChanging=0代表切换失败或者没有切换
        editors.commit();
    }

    public String getEnvironmentImageDocument() {
        return sp.getString("environment_document", "http://xiaoxitech.cn/");//true代表在activity界面  false代表不在activity界面
    }
    /**
     * 配置取相关数据库url的信息
     *
     * @param types
     */
    public void setUrlType(String types) {
        editors.putString("urlType", types);//urlType 对2进行求余，0访问一台服务器、1访问另外一台服务器
        editors.commit();
    }

    public String getUrlType() {
        return sp.getString("urlType", "1");//urlType 对2进行求余，0访问一台服务器、1访问另外一台服务器
    }
}
