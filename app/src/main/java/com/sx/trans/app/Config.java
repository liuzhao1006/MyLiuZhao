package com.sx.trans.app;

/**
 * 作者 : 刘朝,
 * on 2017/9/7,
 * GitHub : https://github.com/liuzhao1006
 */

public class Config {

    // 图片标题集合
    public static String[] mImageDes = {"安全只有起点,没有终点", "安全文化是企业文化的基础",
            "倡导安全文化,加强安全法制", "树立企业安全形象,促进安全文明生产", "交通法规记心中,人身安全在手中"};

    //http://47.92.29.57:14806/GetDataService.asmx/LoginByStudent?Role=1&UserName=test_qyfr&PassWord=1
    public static String LogIn = "/GetDataService.asmx/LoginByStudent?";

    public static String HomeInfo = "/GetDataService.asmx/IndexInfo?";//  首页汇总
    public static String NianFenJiHua = "/GetDataService.asmx/GetStudyPlan?";   //   根据年份获取学习计划

    public static String StudyYear = "/GetDataService.asmx/GetStudyYearList?";//  获取年份

    public static String FuWuDanwei = "/GetDataService.asmx/SetTransferCompany?";//  设置服务单位数据

    public static String setPass = "/GetDataService.asmx/UpdateUserPassword?";// 修改密码

    public static String GetGongsiLieBiao = "/GetDataService.asmx/GetCompanyList?";//   获取送死列表

    public static String GetKejina = "/GetDataService.asmx/GetStudyPlanToCourseware?";  //获取课件
    public static String GetMouth = "/GetDataService.asmx/GetStudyMonthList?";//9.获取学习月份列表

    public static String GetDangYuKeJian = "/GetDataService.asmx/GetStudyMonthToCourseware?";//  获取当月可见信息

    public static String ShangChuanXueShi = "/GetDataService.asmx/SetStudyPlanVideoPlay?";//  上传学时

    public static String WangjiMiMA = "/GetDataService.asmx/IsCertificateNo?";//  忘记密码
    public static String TongZhi = "/GetDataService.asmx/GetNews?";//11.获取通知公告信息

    public static String GenXinTongZhi = "/GetDataService.asmx/SetNewsClickNum?";//  更新通知
    //"http://192.168.103.108:34230"
    public static String UpLoadPhote = "/GetDataService.asmx/UploadJPG?";//  上传图片
    //    public static String AppUpData = URL+ "/GetDataService.asmx/GetAppUpdate?";//  版本迭代
    public static String AppUpData = "http://47.92.124.79:8080/appCommon/apk/updateApk.do?";//  版本迭代


    public static String GetVidoTime = "/GetDataService.asmx/GetVideoTimer?";//  获取断点播放时间
    public static String InPutBreakPoint = "/GetDataService.asmx/SetVideoTimer?";// 插入断点
    public static String UpDataBreatPoint = "/GetDataService.asmx/UpdateVideoTimer?";//  更新断点
    public static String GetPhotoTiem = "/GetDataService.asmx/GetPotoTime?";//获取拍照时间间隔

    public static String GetVideoSetTime = "/GetDataService.asmx/GetVideoSetTime";//  获取断点插入时间间隔
    public static String JianGuanList1 = "/GetDataService.asmx/GetCompanyProgress?";// 监管列表
    // http://47.92.29.57:14806/GetDataService.asmx/GetCompanyProgress?CompanyId=1&strYearMonth=201709
    public static String JianGuanList = "/GetDataService.asmx/GetHomeData";// 监管列表
    public static String EnterpriseUrl = "/GetDataService.asmx/GetStuLearHoursRatio?";//企业列表
    public static String PeopleUrl = "/GetDataService.asmx/GetStuVideoLearList?";//学院详细列表
    public static String DataUrl = "/GetDataService.asmx/GetCompanyProgress?";//学院详细列表


    public static String ZheXianList = "/GetDataService.asmx/GetHomeDataByCompany?";  //折线统计图数据


    public static final String PhoneInfo = "http://47.92.124.79:8080/appCommon/apk/installApk.do";//  上传用户手机信息
    public static final String AppFunction = "http://47.92.124.79:8080/appCommon/apk/functionVisit.do";// app功能访问接口


    public static final String LZ_HOME_LEANRN = "/GetDataService.asmx/IndexInfo?";// 学习从业
    public static final String LZ_HOME_ACCIDENT = "/app/appEmpLawsRegulation?";// 事故从业


}
