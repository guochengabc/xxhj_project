package com.kongtiaoapp.xxhj.afinal;

/**
 * Created by Luoye on 2016-7-20.
 * 说明:  用于存放网络请求方法名的类
 */
public class HttpMethod {
    /**
     * 请求参数key
     */
    public static final String KEY = "para";
    /**
     * 登录
     */
    public static final String LOGIN = "Login";
    /**
     * 退出登录
     */
    public static final String LOGIN_OUT = "Logout";
    /**
     * 注册集团账号
     */
    public static final String SIGNUPGROUP = "SignUpGroup";
    /**
     * 注册企业账号
     */
    public static final String SIGNUPCOMPANY = "SignUpCompany";
    /**
     * 注册个人账号
     */
    public static final String SIGNUPPERSON = "SignUpPerson";
    /**
     * 发送验证码
     */
    public static final String SENDVERIFYCODE = "SendVerifyCode";
    /**
     * 获取用户信息
     */
    public static final String GETUSERINFO = "GetUserInfo";
    /**
     * 获取项目列表
     */
    public static final String LOGININFO = "LoginInfo";
    /**
     * 修改密码
     */
    public static final String UPDATEPWD = "UpdatePwd";
    /**
     * 获取首页模块模板
     */
    public static final String COMMONMENUITEM = "CommonMenuItem";
    /**
     * 获取节能圈消息
     */
    public static final String GETESMESSAGE = "GetESMessage";
    /**
     * 发布能圈消息
     */
    public static final String PUBLISHESMESSAGE = "PublishESMessage";
    /**
     * 获取评论
     */
    public static final String GETESCOMMENT = "GetESComment";
    /**
     * 发表评论
     */
    public static final String PUBLISHESCOMMENT = "PublishESComment";
    /**
     * 删除评论
     */
    public static final String DELETEESCOMMENT = "DeleteESComment";
    /**
     * 删除说说
     */
    public static final String DELETEESMESSAGE = "DeleteESMessage";
    /**
     * 点赞说说
     */
    public static final String WELLDONE = "Welldone";
    /**
     * 收藏信息
     */
    public static final String COLLECTMESSAGE = "CollectMessage";
    /**
     * 获取字典数据（最新）
     */
    public static final String LISTDEVICETYPE = "ListDeviceType";
    /**
     * 获取字典数据（废弃）
     */
    public static final String GETDICTDATA = "GetDictData";
    /**
     * 7.1.3获取设备信息列表
     */
    public static final String GETDEVICEINFOLIST = "GetDeviceInfoList";
    /**
     * 7.1.1获取设备信息参数
     */
    public static final String GETDEVICEINPARAM = "GetDeviceInParam";

    /**
     * 7.1.4获取设备信息详情
     */
    public static final String GETDEVICEINFO = "GetDeviceInfo";
    /**
     * 7.1.4获取室内环境检测参数
     */
    public static final String GETREGINMDATA = "GetReginMData";
    /**
     * 7.1.47.1.2提交设备信息
     */
    public static final String POSTDEVICEINFO = "PostDeviceInfo";
    /**
     * 7.1.5删除设备信息
     */
    public static final String DELETEDEVICEINFO = "DeleteDeviceInfo";
    /**
     * 6.3.2获取项目信息列表
     */
    public static final String GETPROJECTINFOLIST = "GetProjectInfoList";
    /**
     * 5.1.1获取运行首页数据
     */
    public static final String GETRUNNINGHOMEDATA = "GetRunningHomeData";
    /**
     * 5.8.1获取运行设备监控数据
     */
    public static final String GETDEVICESTATUS = "GetDeviceStatus";
    /**
     * 5.8.2获取项目模板列表
     */
    public static final String GETPROJECTTEMPLATE = "GetProjectTemplate";
    /**
     * 6.4.2获取维保记录列表
     */
    public static final String GETMTRECORDLIST = "GetMTRecordList";

    /**
     * 获取首页信息
     */

    public static final String GETRUNNINGHOMEINFO = "GetRunningHomeInfo";
    /**
     * 5.1.2获取运行设备列表
     */
    public static final String GETRUNNINGDEVICELIST = "GetRunningDeviceList";
    /**
     * 7.3.3获取项目信息详情
     */
    public static final String GETPROJECTINFO = "GetProjectInfo";
    /**
     * 7.3.1提交项目信息
     */
    public static final String POSTPROJECTINFO = "PostProjectInfo";
    /**
     * 7.3.4删除项目信息
     */
    public static final String DELETEPROJECTINFO = "DeleteProjectInfo";
    /**
     * 7.3.4获取最新版本号
     */
    public static final String GETLATESYSVER = "GetLatestSysVer";
    /**
     * 7.6改变token
     */
    public static final String UPDATETOKEN = "UpdateToken";
    /**
     * 8.2获取用户列表
     */
    public static final String GETUSERINFOLIST = "GetUserInfoList";
    /**
     * 8.2获取调度员用户列表
     */
    public static final String GETDISPATCHERINFO = "GetDispatcherInfo";
    /**
     * 5.4.1提交值班记录
     */
    public static final String POSTDUTYRECORD = "PostDutyRecord";
    /**
     * 5.4.2修改值班记录
     */
    public static final String UPDATEDUTYRECORD = "UpdateDutyRecord";

    /**
     * 5.4.3查询值班记录列表
     */
    public static final String QUERYDUTYRECORDLIST = "QueryDutyRecordList";
    /**
     * 获取最近交接班记录
     */
    public static final String GETLATELYDUTYRECORD = "GetLatelyDutyRecord";
    /**
     * 获取值班记录详情
     */
    public static final String GETDUTYRECORD = "GetDutyRecord";
    /**
     * 5.5.1获取天气信息
     */
    public static final String GETWEATHERINFO = "GetWeatherInfo";
    /**
     * 4.1.1发布讨论帖
     */
    public static final String PUBLISHDISCUSSTHEME = "PublishDiscussTheme";
    /**
     * 4.1.1刚进入界面会掉的接口
     */
    public static final String GETPROJECTHOMEINFO = "GetProjectHomeInfo";
    /**
     * 4.1.1新版刚进入界面会掉的接口  2018-5-11
     */
    //  public static final String GETNEWHOMEINFO = "GetNewHvacHomeInfo";//老版本  2018-5-21以前的
    public static final String GETNEWHVACTESTHOMEINFO = "GetNewHvacTestHomeInfo";//新版本
    /**
     * 4.1.2获取讨论帖列表
     */
    public static final String GETDISCUSSTHEMELIST = "GetDiscussThemeList";
    /**
     * 4.1.获取参数设置   变配电报警阈值
     */
    public static final String GETPARAMSETINFO = "GetParamSetInfo";
    /**
     * 4.1.修改参数设置
     */
    public static final String UPDATEPARAMSETINFO = "UpdateParamSetInfo";
    /**
     * 4.1.3删除讨论帖
     */
    public static final String DELETEDISCUSSTHEME = "DeleteDiscussTheme";
    /**
     * 4.1.5获取集团项目列表
     */
    public static final String GETGROUPPROJECTLIST = "GetGroupProjectList";
    /**
     * 4.6接班
     */
    public static final String SUCCEED = "Succeed";
    /**
     * 4.7.获取图表分析诊断
     */
    public static final String GETRUNNINGCHARTEVAL = "GetRunningChartEval";
    /**
     * 4.7.6提交项目评论内容GetRunningChartEval
     */
    public static final String POSTPROJECTCOMMENT = "PostProjectComment";
    /**
     * 4.7.7获取留言列表
     */
    public static final String GETPROJECTCOMMENTLIST = "GetProjectCommentList";
    /**
     * 4.7.8删除项目评论内容
     */
    public static final String DELETEPROJECTCOMMENT = "DeleteProjectComment";
    /**
     * 4.8.3查看值班信息
     */
    public static final String GETCURRDUTYRECORD = "GetCurrDutyRecord";
    /**
     * 4.8.3查看设备信息
     */
    public static final String GETCURRDEVICEINFO = "GetCurrDeviceInfo";
    /**
     * 5.2.获取台账铭牌信息
     */
    public static final String GETNAMEPLATEPARAINFO = "GetNameplateParaInfo";
    /**
     * 5.2.1获取运行设备参数
     */
    public static final String GETRUNNINGPARAM = "GetRunningParam";
    /**
     * 5.2.4获取运行系统记录
     */
    public static final String GETRUNNINGSYSDATA = "GetRunningSysData";
    /**
     * 5.2.3获取运行设备记录
     */
    public static final String GETRUNNINGDATA = "GetRunningData";
    /**
     * 5.2.8提交运行参数顺序
     */
    public static final String POSTRUNNINGORDERPARAM = "PostRunningOrderParam";
    /**
     * 4.2.10提交系统运行参数顺序
     */
    public static final String POSTRUNSYSORDERPARAM = "PostRunSysOrderParam";
    /**
     * 4.2.10获取整体运行评价
     */
    public static final String GETRUNNINGEVAL = "GetRunningEval";

    /**
     * 5.3.1获取诊断记录
     */
    public static final String GETDIAGDATA = "GetDiagData";

    /**
     * 5.3.3获取设备诊断记录
     */
    public static final String GETDEVICEDIAGDATA = "GetDeviceLRData";
    /**
     * 5.3.4获取水系统诊断记录
     */
    public static final String GETDEVICEWTDDATA = "GetDeviceWTData";
    /**
     * 5.3.5获取设备能耗记录图表
     */
    public static final String GETDEVICEECDATA = "GetDeviceECData";
    /**
     * 5.3.6获取设备制冷效率记录
     */
    public static final String GETDEVICECOPDATA = "GetDeviceCOPData";
    /**
     * 5.3.7获取能耗能效监测记录（暖）
     */
    public static final String GETHEATECEEDATA = "GetHeatEcEeData";
    /**
     * 5.3.8获取用户供能监测记录（暖）
     */
    public static final String GETHEATUEDDATA = "GetHeatUEData";
    /**
     * 5.3.9获取热源设备安全监测记录（暖）
     */
    public static final String GETHEATSAFEDATA = "GetHeatSafeData";
    /**
     * 5.3.10获取热量输送监测记录（暖）
     */
    public static final String GETHEATTRANSFERDATA = "GetHeatTransferData";
    /**
     * 4.3.9获取诊断图的数据
     */
    public static final String GETMONITORDATA = "GetMonitorData";
    /**
     * 4.3.获取饼状图图表
     */
    public static final String GETCHARTDATASOURCES = "GetChartDataSources";

    /**
     * 5.7获取报告
     */
    public static final String GETRUNREPORT = "GetRunReportData";

    /**
     * 提交设备
     */
    public static final String POSTRUNNINGDATA = "PostRunningData";
    /**
     * 提交设备
     */
    public static final String GETRUNNINGSYSPARAM = "GetRunningSysParam";
    /**
     * 诊断记录列表
     */
    public static final String GETDIAGDATALIST = "GetDiagDataList";


    /**
     * 5.4.2获取领导值班记录
     */
    public static final String GETLEADERDUTYRECORDS = "GetLeaderDutyRecords";
    /**
     * 8.3获取设备列表
     */
    public static final String GETDEVICELIST = "GetDeviceList";

    /**
     * 4.1.5获取讨论帖回复
     */
    public static final String GETDISCUSSANSWER = "GetDiscussAnswer";

    /**
     * 4.1.4提交讨论帖回复
     */
    public static final String PUBLISHDISCUSSANSWER = "PublishDiscussAnswer";
    /**
     * 4.1.删除讨论帖回复
     */
    public static final String DELETEDISCUSSANSWER = "DeleteDiscussAnswer";
    /**
     * 4.1.点赞讨论帖
     */
    public static final String WELLDONEDISCUSS = "WelldoneDiscuss";
    /**
     * 6.1.2提交用户信息
     */
    public static final String POSTUSERINFO = "PostUserInfo";
    /**
     * 6.3.5获取项目设备列表
     */
    public static final String GETPROJECTDEVICELIST = "GetProjectDeviceList";
    /**
     * 5.6.1获取实时优化信息
     */
    public static final String GETOPTSUGGESTION = "GetOptSuggestion";
    /**
     * 5.7.4获取邀请码
     */
    public static final String GETMYINVITECODE = "GetMyInviteCode";
    /**
     * 5.7.5重置邀请码
     */
    public static final String RESETINVITECODE = "ResetInviteCode";
    /**
     * 5.7.6修改密码
     */
    public static final String MODIFYPWD = "ModifyPwd";
    /**
     * 5.8.3获取运行记录默认参数
     */
    public static final String GETRDATADEFAULTPARAM = "GetRDataDefaultParam";

    /**
     * 4.1.6获取能耗监测数据
     */
    public static final String GETMONITORENERGYDATA = "GetMonitorEnergyData";
    /**
     * 5.1.4获取能耗详细信息
     */
    public static final String GETENERGYINFO = "GetEnergyInfo";
    /**
     * 3.3.1获取热门列表
     */
    public static final String GETHOTESMESSAGE = "GetHotESMessage";
    /**
     * 3.3.2获取同城列表
     */
    public static final String GETSAMECITYESMESSAGE = "GetSameCityESMessage";
    /**
     * 3.3.3获取同行列表
     */
    public static final String GETSAMEWORKESMESSAGE = "GetSameWorkESMessage";
    /**
     * 4.2.9查询运行历史记录
     */
    public static final String QUERYRUNNINGDATA = "QueryRunningData";
    /**
     * 4.4.1获取节能展会列表
     */
    public static final String GETEXHIBITIONLIST = "GetExhibitionList";

    /**
     * 4.4.2获取节能展会详情
     */
    public static final String GETEXHIBITIONINFO = "GetExhibitionInfo";
    /**
     * 4.4.5获取接班信息
     */
    public static final String GETOFFDUTYRECORD = "GetOffDutyRecord";
    /**
     * 6.9导出运行记录
     */
    public static final String EXRUNNINGDATA = "ExpRunningData";


    /**
     * 8.1.1获取首页消息
     */
    public static final String GETHOMESYSMESSAGE = "GetHomeSysMessage";

    /**
     * 8.2.1获取系统消息详情
     */
    public static final String GETSYSMESSAGEINFO = "GetSysMessageInfo";

    /**
     * 8.2.2获取好友消息详情
     */
    public static final String GETFRIENDMESSAGEINFO = "GetFriendMessageInfo";

    /**
     * 4.2.1获取精选列表
     */
    public static final String GETSELECTIONLIST = "GetSelectionList";


    /**
     * 4.3.1获取专栏列表
     */
    public static final String GETSPECIALINFOLIST = "GetSpecialInfoList";


    /**
     * 4.2.3获取精选详情
     */
    public static final String GETSELECTIONINFO = "GetSelectionInfo";
    /**
     * 4.2.2提交精选评论
     */
    public static final String PUBLISHSELECTIONCOMMENT = "PublishSelectionComment";
    /**
     * 4.2.2删除精选评论
     */
    public static final String DELETESELECTIONCOMMENT = "DeleteSelectionComment";
    /**
     * 4.2.2删除精选评论
     */
    public static final String GETFRIENDLIST = "GetFriendList";
    /**
     * 8.3.3获取我的好友详情
     */
    public static final String GETFRIENDINFO = "GetFriendInfo";
    /**
     * 8.3.4屏蔽我的好友
     */
    public static final String SHIELDFRIEND = "ShieldFriend";
    /**
     * 8.3.5删除我的好友
     */
    public static final String DELETEFRIEND = "DeleteFriend";
    /**
     * 8.3.1添加好友
     */
    public static final String ADDFRIEND = "AddFriend";

    /**
     * 3.3.4获取社区信息
     */
    public static final String GETCOMMUNITYESMESSAGE = "GetCommunityESMessage";

    /**
     * 4.5.2获取产品列表
     */
    public static final String GETPRODUCTLIST = "GetProductList";


    /**
     * 4.3.2获取专栏文章列表
     */
    public static final String GETSPECIALARTICLELIST = "GetSpecialArticleList";


    /**
     * 6.8.1关于我们
     */
    public static final String GETABOUTUS = "GetAboutUs";


    /**
     * 4.3.3获取专栏文章详情
     */
    public static final String GETSPECIALARTICLE = "GetSpecialArticle";
    /**
     * 4.3.4提交专栏文章评论
     */
    public static final String PUBLISHSPECIALCOMMENT = "PublishSpecialComment";
    /**
     * 4.3.5删除专栏文章评论
     */
    public static final String DELETESPECIALCOMMENT = "DeleteSpecialComment";
    /**
     * 5.2.6获取手工录入参数
     */
    public static final String GETRUNNINGMDATA = "GetRunningMData";
    /**
     * 5.2.7提交手工录入参数
     */
    public static final String POSTRUNNINGMDATA = "PostRunningMData";
    /**
     * 6.6.1获取我的动态发布列表
     */
    public static final String GETMYESMESSAGELIST = "GetMyESMessageList";
    /**
     * 6.6.2获取我的帖子发布列表
     */
    public static final String GETMYDISCUSSTHEMELIST = "GetMyDiscussThemeList";
    /**
     * 6.7.1获取动态收藏列表
     */
    public static final String GETESMCOLLECTIONLIST = "GetESMCollectionList";
    /**
     * 6.7.2获取资讯收藏列表
     */
    public static final String GETDTCOLLECTIONLIST = "GetDTCollectionList";
    /**
     * 8.2.4获取聊天消息
     */
    public static final String GETCHATMESSAGEINFO = "GetChatMessageInfo";

    /**
     * 8.2.5发送聊天消息
     */
    public static final String SENDCHATMESSAGE = "SendChatMessage";

    /**
     * 8.2.3同意添加好友
     */
    public static final String AGREEADDFRIEND = "AgreeAddFriend";
    /**
     * 6.8.2意见反馈
     */
    public static final String POSTFEEDBACK = "PostFeedback";
    /**
     * 用户管理
     */
    public static final String LISTUSERINFO = "ListUserInfo";
    /**
     * 设置用户管理权限
     */
    public static final String MODIFYAUTHORITY = "ModifyAuthority";


    /**
     * 获取工单列表
     */
    public static final String LISTDISPATCHFORM = "ListDispatchForm";
    /**
     * 获取我的工单列表
     */
    public static final String LISTMYDISPATCHFORM = "ListMyDispatchForm";
    /**
     * 获取我的未完成工单列表
     */
    public static final String LISTUNFINISHHDFORM = "ListUnFinishDForm";

    /**
     * 抢单
     */
    public static final String ACCEPTREPAIRINFO = "AcceptRepairInfo";
    /**
     * 流程追踪
     */
    public static final String LISTREPAIRFOLW = "ListRepairFlow";


    /**
     * 上传报修单
     */
    public static final String POSTREPAIRINFO = "PostRepairInfo";
    /**
     * 修改报修单
     */
    public static final String UPDATEREPAIRINFO = "UpdateRepairInfo";

    /**
     * 获取报修单详细信息
     */
    public static final String GETDISPATCHDETAIL = "GetDispatchDetail";
    /**
     * 上传勘察单
     */
    public static final String POSTPROSPECTINFO = "PostProspectInfo";
    /**
     * 上传提交单
     */
    public static final String POSTFINISHINFO = "PostFinishInfo";
    /**
     * 质检员评价
     */
    public static final String POSTEVALUATEINFO = "PostEvaluateInfo";
    /**
     * 导出工单表格
     */
    public static final String EXPDISPATCHFORM = "ExpDispatchForm";
    /**
     * 获取报修单状态
     */
    public static final String GETDFORMSTATE = "GetDFormState";
    /**
     * 工单统计
     */
    public static final String GETWORKSTATISTICS = "GetWorkStatistics";
    /**
     * 工程师统计
     */
    public static final String GETENGINEERWORKSTATS = "GetEngineerWorkStats";

    public static final String GETREPAIRWORKSTATS = "GetRepairWorkStats";
    /**
     * 获取各设备图表
     */
    public static final String GETDEVICEMDATA = "GetDeviceMData";
    /**
     * 报警管理列表
     */
    public static final String LISTALARMINFO = "ListAlarmInfo";
    /**
     * 报警管理详情
     */
    public static final String GETALARMINFO = "GetAlarmInfo";

    /**
     * 获取交接班类别
     */
    public static final String GETCSTYPE = "GetCSType";

    /**
     * 提交电子签名信息
     */
    public static final String SAVESIFNATUREINFO = "SaveSignaTureInfo";

    /**
     * 获取设备信息分类
     */
    public static final String GETDEVICEINFOCATEGORY = "GetDeviceInfoCategory";
    /**
     * 运行指导
     */
    public static final String GETRUNNINGGUIDE = "GetRunningGuide";
    /**
     * 获取巡检项
     */
    public static final String GETINSPECTIONPARAM = "GetInspectionParam";
    /**
     * 提交巡检项
     */
    public static final String POSTDEVICESTATUS = "PostDeviceStatus";
    /**
     * 获取维保记录列表
     */
    public static final String getmtrecordlist = "GetMTRecordList";
    /**
     * 获取维保记录详情
     */
    public static final String GETMTRECORD = "GetMTRecord";
    /**
     * 添加维保记录
     */
    public static final String POSTMTRECORD = "PostMTRecord";
    /**
     * 修改维保记录详情
     */
    public static final String UPDATEMTRECORD = "UpdateMTRecord";
    /**
     * 删除维保记录详情
     */
    public static final String DELETEMTRECORD = "DeleteMTRecord";
    /**
     * 获取暖通空调设备列表
     */
    public static final String GETDEVICEPARA = "GetDevicePara";
    /**
     * 获取能源管理设备列表
     */
    public static final String GETSENSORPARAM = "GetSensorParam";
    //往下都是能源管理接口
    /**
     * 能源管理模块顶部
     */
    public static final String GETENERGYMETERHOMEINFO = "GetEnergyMeterHomeInfo";
    /**
     * 能源数据录入
     */
    public static final String GETFORMENTRYITEM = "GetFormEntryItem";
    /**
     * 能源数据录入获取下面的值  服务器交互
     */
    public static final String GETFORMCOMPARAM = "GetFormCompParam";
    /**
     * 能源数据  上一页/下一页
     */
    public static final String GETENTRYLISTINFO = "getEntryListInfo";
    /**
     * 能源数据录入获取设备列表的标题
     */
    public static final String GETENERCATEGINFO = "GetEnerCategInfo";
    /**
     * 录前检测
     */
    public static final String CHECK_BEFORE = "RecordingCheckTime";
    /**
     * 能源数据录入获取设备列表
     */
    public static final String GETENERCATEGINFOD = "GetCategInfoByType";
    /**
     * 能源数据录入提交服务器
     */
    public static final String POSTENTRYDATA = "PostEleEntryData";
    /**
     * 联网自动上传服务器
     */
    public static final String POSTBATCHENTRYINFO = "PostBatchEntryInfo";


    /**以下是变配电的相关接口*/
    /**
     * 变配电首页
     */
    public static final String GETPOWERHOMEINFO = "GetPowerHomeInfo";

    /**
     * 获取变配电数据录入列表
     */
    public static final String GETPOWERDEVICEINFO = "GetPowerDeviceInfo";
    /**
     * 获取变配电数据录入详情
     */
    public static final String GETRUNDEVICEPARAMINFO = "GetRunDeviceParamInfo";
    /**
     * 提交变配电数据
     */
    public static final String POSTPOWERPARAMDATA = "PostPowerParamData";
    /**
     * 变配电报警信息
     */
    public static final String LISTPOWERALARMINFO = "ListPowerAlarmInfo";
    /**
     * 变配电图表信息
     */
    public static final String GETPOWERMONITORDATA = "GetPowerMonitorData";
    /**
     * 变配电图表信息TAB
     */
    public static final String GETPOWERCHARTDATASOURCES = "GetPowerChartDataSources";
    /**
     * 变配电周报月报
     */
    public static final String GETPOWERREPORTDATA = "GetPowerReportData";
    /**
     * 变配电周报月报详情
     */
    public static final String GETPOWERREPORTDETAILS = "GetPowerReportDetails";

    /**
     * 以下是记录表单模块
     */
    public static final String JLBDMENUITEM = "JlbdMenuItem";

    /**
     * 录入表单查询接口
     */
    public static final String GETINPUTRECORD = "GetInputRecord";
    /**
     * 录入详情接口
     */
    public static final String GETINPUTRECORDDETAILS = "GetInputRecordDetails";
    /**
     * 保存或者修改录入详情接口
     */
    public static final String SAVEINPUTRECORDDETAILS = "SaveInputRecordDetails";
    /**
     * 撤回录入详情接口
     */
    public static final String CANCELINPUTRECORD = "CancelInputRecord";

}



