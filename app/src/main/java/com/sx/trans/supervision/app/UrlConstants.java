package com.sx.trans.supervision.app;

/**
 * 接口常量
 */
public class UrlConstants {


//    public static final String BASE_URL = "http://58.67.161.51:28781/app/api/";//外网接口platform/
    public static final String BASE_URL = "http://47.92.29.57:8016/api/";//正式服

    /**
     * 登录
     */
    public static final String LOGIN=BASE_URL+"open/user/login";


    /**
     * 获得首页数据
     */
    public static final String HOMEDATE=BASE_URL+"index/";

    /*
     *搜索车辆列表
     *
     */
    public static final String SEARCHVEHICLE = BASE_URL + "vehicle/";

    /**
     * 车辆定位
     */
    public static final String VEHICLEPOSITION = BASE_URL + "vehicle/position/";

    /**
     * 车辆轨迹查询
     */
    public static final String GETTRACK = BASE_URL + "getTrack/";

    /**
     * 修改密码
     */
    public static final String UPDATE_PWD = BASE_URL+"user/info/";

    /**
     * 车辆详情
     */
    public static final String VEHICLE_DETAIL = BASE_URL + "vehicle/info/";

    /**
     * 上传头像
     *
     */
    public static final String UPDATE_IMAGE=BASE_URL+"user/avatar/";

    /**
     * 业户主页列表
     */
    public static final String GET_TRADE_LIST = BASE_URL + "unit/list/";

    /**
     * 业户公司列表
     */
    public static final String GET_COMPANY_LIST = BASE_URL + "unit/trade/";

    /**
     * 搜索业户公司列表
     */
    public static final String GET_SEARCH_COMPANY_LIST = BASE_URL + "unit/matching/";

    /**
     * 业户详情
     */
    public static final String GET_COMPANY_DETAIL = BASE_URL + "unit/";

    /**
     * 报警趋势分析
     */
    public static final String ALARMANALYSE = BASE_URL + "alarm/getAlarmAnalyse/";

    /**
     * 行业趋势分析 getIndustryAnalyse
     */
    public static final String GETINDUSTYYANALYSE = BASE_URL + "/industry/getMapAnalyse/";

    /**
     * 公司-车辆分布
     */
    public static final String COMPANY_VEHICLE_POSITION = BASE_URL + "unit/postion/";

    /**
     * 平台分析列表
     */
    public static final String GET_PLATFORM_LIST = BASE_URL + "platform/";

    /**
     * 地图区域getMapData
     */
    public static final String GETMAPDATA = BASE_URL + "area/getMapData/";

    /**
     * 详细区域数据
     */
    public static final String GETMAPMOREDATE = BASE_URL + "/area/getData/";

    /**
     * 查岗问题
     */
    public static final String GETQUESTION = BASE_URL + "/postcheck/question/";

    /**
     * 查岗记录
     * checkStatistic
     */
    public static final String CHECKSTATISTIC = BASE_URL + "postcheck/checkStatistic/";

    /**
     * 查岗记录详情
     * checkDetail
     */
    public static final String CHECKDETAIL = BASE_URL + "postcheck/checkDetail/";

    /**
     * 下发岗位指令
     * sendCmd
     */
    public static final String SENDCMD = BASE_URL + "postcheck/sendCmd/";

}
