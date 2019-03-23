package com.kongtiaoapp.xxhj.afinal;

/**
 * Created by Shinelon on 2016/6/6.
 */
public class ConstantValue {

    // V3正式环境
    public static String HTTP_URL = "http://api.xiaoxitech.cn/v3/Process";
    public static String HTTP_URLS = "https://api.xiaoxitech.cn/v3/Process";
    public static String URL = "http://xiaoxitech.cn/";
    public static String URLS = "https://xiaoxitech.cn/";
    // 测试环境
    /*public static String HTTP_URL = "http://api.xiaoxitech.cn/v3_test/Process";
    public static String HTTP_URLS = "https://api.xiaoxitech.cn/v3_test/Process";
    public static String URL = "http://xiaoxitech.cn/";
    public static String URLS = "https://xiaoxitech.cn/";*/
    // V3控制环境环境  在ui/view/DialogPrompt里面控制
    /*public static String HTTP_URL = App.sp.getEnvironment();//"http://api.xiaoxitech.cn/v3_test/Process";
    public static String HTTP_URLS = App.sp.getEnvironment();
    public static String URL = "http://xiaoxitech.cn/";//"http://xiaoxitech.cn/";
    public static String URLS = "http://xiaoxitech.cn/";*/

    // 本地环境
   /* public static String HTTP_URL = "http://10.0.2.2:8080/ktjnAPP/Process";
    public static String HTTP_URLS = "http://10.0.2.2:8080/ktjnAPP/Process";
  //  public static String HTTP_URLS = "http://192.168.1.132:8080/ktjnAPP/Process"; 我的本地IP地址
    public static String URL = "http://192.168.1.141/";
    public static String URLS = "https://192.168.1.141/";*/


  /*public static String HTTP_URL = "http://192.168.1.101:8080/ktjnAPP/Process";
  public static String HTTP_URLS = "http://192.168.1.101:8080/ktjnAPP/Process";
  //  public static String HTTP_URLS = "http://192.168.1.132:8080/ktjnAPP/Process"; 我的本地IP地址
  public static String URL = "http://192.168.1.141/";
  public static String URLS = "https://192.168.1.141/";*/

    //本地手机测试
    // 本地环境
  /*  public static String HTTP_URL = "http://192.168.1.132:8080/ktjnAPP/Process";
    public static String HTTP_URLS = "http://192.168.1.132:8080/ktjnAPP/Process";
    //  public static String HTTP_URLS = "http://192.168.1.132:8080/ktjnAPP/Process"; 我的本地IP地址
    public static String URL = "http://192.168.1.141/";
    public static String URLS = "https://192.168.1.141/";*/
    //APP下载地址
    public static String APP_URL = "http://files.xiaoxitech.cn/";

    /*-------------------------   网络请求码    -------------------------------*/
    public static final int LOGIN = 0x0001;
    public static final int SIGNUPCOMPANY = 0x00011;
    public static final int SIGNUPPERSON = 0x00012;
    public static final int GETUSERINFO = 0x0002;
    public static final int SENDVERIFYCODE = 0x0003;
    public static final int GETESMESSAGE = 0x0004;//获取朋友圈状态
    public static final int PUBLISHESMESSAGE = 0x0005;//发布状态
    public static final int GETESCOMMENT = 0x0006;//获取评论
    public static final int PUBLISHESCOMMENT = 0x0007;//发表评论
    public static final int DELETEESMESSAGE = 0x0008;//删除说说
    public static final int WELLDONE = 0x0009;//点赞
    public static final int GETDICTDATA = 0x000a;//获取字典数据
    public static final int GETDEVICEINFOLIST = 0x000b;//7.1.3获取设备信息列表
    public static final int GETDEVICEINPARAM = 0x000c;//7.1.1获取设备信息参数
    public static final int GETDEVICEINFO = 0x000d;//7.1.4获取设备信息详情
    public static final int POSTDEVICEINFO = 0x000d;//7.1.2提交设备信息

    public static final int DELETEDEVICEINFO = 0x000e;//7.1.5删除设备信息
    public static final int GETPROJECTINFOLIST = 0x000f;//6.3.2获取项目信息列表
    public static final int GETRUNNINGHOMEDATA = 0x0010;//5.1.1获取运行首页数据
    public static final int GETMTRECORDLIST = 0x0011;//6.4.2获取维保记录列表
    public static final int DELETEMTRECORD = 0x0012;//6.4.4删除维保记录
    public static final int GETRUNNINGDEVICELIST = 0x0013;//5.1.2获取运行设备列表

    public static final int GETPROJECTINFO = 0x0014;//7.3.3获取项目信息详情
    public static final int POSTPROJECTINFO = 0x0015;//7.3.3获取项目信息详情
    public static final int DELETEPROJECTINFO = 0x0016;//7.3.4删除项目信息
    public static final int GETLATESYSVER = 0x0016A;//7.3.4获取最新版本号
    public static final int GETUSERINFOLIST = 0x0017;//8.2获取用户列表
    public static final int POSTDUTYRECORD = 0x0018;//5.4.1提交值班记录
    public static final int QUERYDUTYRECORDLIST = 0x0019;//5.4.3查询值班记录列表
    public static final int GETDUTYRECORD = 0x001a;//获取值班记录详情
    public static final int GETWEATHERINFO = 0x001b;//5.5.1获取天气信息
    public static final int PUBLISHDISCUSSTHEME = 0x001c;//4.1.1发布讨论帖
    public static final int GETDISCUSSTHEMELIST = 0x001d;//4.1.2获取讨论帖列表
    public static final int DELETEDISCUSSTHEME = 0x001e;//4.1.2获取讨论帖列表
    public static final int GETRUNNINGPARAM = 0x001f;//5.2.1获取运行设备参数
    public static final int GETDIAGDATA = 0x0020;//5.3.1获取诊断记录
    public static final int GETDEVICEDIAGDATA = 0x0020AA;//5.3.3获取设备诊断记录
    public static final int GETDEVICEDIAGDATA_MONTH = 0x0020AB;//5.3.3获取设备诊断记录
    public static final int GETDEVICEECDATA = 0x0020AB;//5.3.5获取设备能耗记录
    public static final int GETHEATECEEDATA = 0x0020ACAA;//5.3.7获取能耗能效监测记录（暖）
    public static final int GETHEATUEDDATA = 0x0020A538;//5.3.8获取用户供能监测记录（暖）
    public static final int GETHEATECEEDATA1 = 0x0020ACAA1;//5.3.7获取燃气消耗监测记录1（暖）
    public static final int GETHEATSAFEDATA = 0x0020ACAA39;//5.3.9获取热源设备安全监测记录（暖）
    public static final int GETHEATTRANSFERDATA = 0x0020ACAA31;//5.3.10获取热量输送监测记录（暖）
    public static final int GETRUNREPORT = 0x0057;//5.7获取报告的内容
    public static final int POSTRUNNINGDATA = 0x0021;//提交设备
    public static final int GETDIAGDATALIST = 0x0022;//诊断记录列表
    public static final int GETMTRECORD = 0x0023;//维保记录详情
    public static final int POSTMTRECORD = 0x0024;//添加维保记录详情
    public static final int UPDATEPWD = 0x0025;//修改密码
    public static final int GETLEADERDUTYRECORDS = 0x0026;//5.4.2获取领导值班记录
    public static final int GETDEVICELIST = 0x0027;//8.3获取设备列表
    public static final int DELETEESCOMMENT = 0x0028;//删除评论
    public static final int GETDISCUSSANSWER = 0x0029;//4.1.5获取讨论帖回复
    public static final int PUBLISHDISCUSSANSWER = 0x002a;//4.1.4提交讨论帖回复
    public static final int DELETEDISCUSSANSWER = 0x002b;//4.1.4删除讨论帖回复
    public static final int WELLDONEDISCUSS = 0x002c;//4.1.4点赞讨论帖
    public static final int POSTUSERINFO = 0x002d;//6.1.2提交用户信息
    public static final int GETPROJECTDEVICELIST = 0x002e;//6.3.5获取项目设备列表
    public static final int GETOPTSUGGESTION = 0x002f;//5.6.1获取实时优化信息
    public static final int GETPROJECTTEMPLATE = 0x0020f;//5.8.2获取项目模板列表
    public static final int GETENERGYHISTORY = 0x0030;//5.1.3获取能耗历史记录
    public static final int GETENERGYINFO = 0x0031;//5.1.4获取能耗详细信息
    public static final int GETEXHIBITIONLIST = 0x0032;//4.4.1获取节能展会列表
    public static final int GETEXHIBITIONINFO = 0x0033;//4.4.1获取节能展会详情
    public static final int GETHOMESYSMESSAGE = 0x0034;//8.1.1获取首页消息
    public static final int GETSYSMESSAGEINFO = 0x0035;//8.2.1获取系统消息详情
    public static final int GETFRIENDMESSAGEINFO = 0x0036;//8.2.2获取好友消息详情
    public static final int COLLECTMESSAGE = 0x0037;//7.4收藏信息
    public static final int GETSELECTIONLIST = 0x0038;//4.2.1获取精选列表
    public static final int GETSPECIALINFOLIST = 0x0039;//4.3.1获取专栏列表
    public static final int GETSELECTIONINFO = 0x003a;//4.2.3获取精选详情
    public static final int PUBLISHSELECTIONCOMMENT = 0x003b;//4.2.2提交精选评论
    public static final int DELETESELECTIONCOMMENT = 0x003c;//4.2.4删除精选评论
    public static final int EXRUNNINGDATA = 0x00369c;//6.9导出运行记录
    public static final int GETFRIENDLIST = 0x003d;//8.3.2获取我的好友列表
    public static final int GETFRIENDINFO = 0x003e;//8.3.3获取我的好友详情
    public static final int SHIELDFRIEND = 0x003f;//8.3.4屏蔽我的好友
    public static final int DELETEFRIEND = 0x0040;//8.3.5删除我的好友
    public static final int ADDFRIEND = 0x0041;//8.3.1添加好友
    public static final int GETCOMMUNITYESMESSAGE = 0x0042;//3.3.4获取社区信息
    public static final int GETPRODUCTLIST = 0x0043;//4.5.2获取产品列表
    public static final int GETSPECIALARTICLELIST = 0x0044;//4.3.2获取专栏文章列表
    public static final int GETABOUTUS = 0x0045;//6.8.1关于我们
    public static final int GETMYESMESSAGELIST = 0x0046;//6.6.1获取我的动态发布列表
    public static final int GETMYDISCUSSTHEMELIST = 0x0047;//6.6.2获取我的帖子发布列表
    public static final int GETESMCOLLECTIONLIST = 0x0048;//6.7.1获取动态收藏列表
    public static final int GETDTCOLLECTIONLIST = 0x0049;//6.7.2获取资讯收藏列表
    public static final int GETCHATMESSAGEINFO = 0x004a;//8.2.4获取聊天消息
    public static final int SENDCHATMESSAGE = 0x004b;//8.2.5发送聊天消息
    public static final int AGREEADDFRIEND = 0x004c;//8.2.3同意添加好友
    public static final int POSTFEEDBACK = 0x004d;//6.8.2意见反馈

    /*社区更多按钮*/
    public static final String COMMUNITY_HOT = "community_hot";//热门
    public static final String COMMUNITY_SAME_CITY = "community_same_city";//同城
    public static final String COMMUNITY_SAME_HANG = "community_same_hang";//同行

    //刷新
    public static final int SHUAXIN_SUCESS = 0x0001;//刷新
    public static final int JIAZAI_SUCESS = 0x0002;//

    //页面中作为参数传递的KEY
    public static final String PROJECTID = "projectID";//项目ID
    public static final String PROJECTTYPE = "projectType";//项目类型
    public static final String AUTO = "auto";//项目是否自动采集

    //参数数据类型
    public static final String STRING = "string";//字符串
    public static final String ENUM = "enum";//枚举
    public static final String FLOAT = "float";//小数
    public static final String DATETIME = "datetime";//时间类型
    public static final String INT = "int";//整数类型

    /*-------------------------  收藏  -----------------------*/

    public static final String MOMENT = "A";    //A 动态
    public static final String TALKING = "B";   //B 讨论帖
    public static final String SELECTION = "C"; //C 精选
    public static final String SPECIAL = "D";   //D 专栏
    public static final String SHOW = "E";      //E 展会

    /*--------------能源计量-------------*/
    public static final String SPLIT_ENERGY = "--";//分割能源计量数据
    public static final String ENERGY_AUTO = "auto";//能源管理数据自动检测
    public static final String ENERGY_manu = "manu";//能源管理数据手动

    /*--------------时间显示--------------*/
    public static final String DATEMONTH = "dateMonth";//显示年月
    public static final String DATEDAY = "dateDay";//显示年月日
    public static final String DATEHOUR = "dateHour";//显示到小时
    public static final String DATEMINUTE = "dateMinute";//显示到分钟
    public static final String DATESECOND = "dateSecond";//显示到秒
    public static final String PLEASE_TIME = "请选择时间";//请选择时间
}
