package com.sx.trans.supervision.app;

import android.os.Environment;

/**
 * 系统常量
 * Created by Administrator on 2016/3/14.
 */
public class IConstants {
    public static final String LOG_TAG = "<<foton_log>>";
    public static final String BASE_FILE_PATH = "/mnt/sdcard/foton/file/";
    public static final int IMAGE_PIX = 480 * 800;
    public static final int MAX_IMAGE_SIZE = 200;//kb
    public static final int SCALE_WIDTH  = 360;
    public static final int SCALE_HEIGHT = 640;
    public static String IMAGETAMPPATH = Environment.getExternalStorageDirectory() + "/cache/";

    public static class mSpre_Constants {
        public static String WELCOME = "welcome";
        public static String IMAGE_NAME = "imageName";
        public static String IMAGE_URL = "imageUrl";
        public static String USER_NAME = "userName";
        public static String WORKUNITNAME = "workUnitName";
        public static String LOGIN_ACCOUNT = "login_account";
        public static String LOGIN_PWD = "userPWD";
        public static String UID = "userId";
        public static String USER_PHONE = "uesrphone";

        public static String USER_AREACODE = "areaCode";
        public static String USER_AREANAME = "areaName";
        public static String ALIAS = "alias";
        public static String USER_AUTH = "auth";
        public static String CREATE_TIME = "createTime";
        public static String OVER_TIME = "overTime";
        public static String REMENBER_PWD = "remenberPwd";
        public static String UUID = "UUID";
        public static String CUSTOM_CODE = "customCODE";

        public static String GPS_STARTIME = "starttime";
        public static String GPS_ENDTIME = "endtime";
        public static String GPS_DATA = "gpsdata";
        public static String GPS_ORDERID = "gps_orderId";
        public static String ORDER_POSITION = "orderPosition";
        public static String PAGETYPE = "pageType";
        public static String ADDRESS_INFO = "addressInfo";
        public static String REPAIRSTORE_NAME = "repairStoreName";
        public static String IS_LOCATION = "isLocation";
        public static String IS_HAS_OUTSIDE = "isHasOutSide";//pms是否有外出单

        public static String CARTEAMNAME="carteamname";//车队名称
        public static String UNITID="unitId";//单位ID
        public static String ALARMTYPE="alarmtype";//报警类型
        public static String ALARMMESSAGE="alarmmessage";//报警通知
        public static String SEARCHINTETN="SearchIntent";//搜索功能类型
        public static String SEARCHINFO="SearchInfo";//搜索车辆
        public static String SCREENINFOLIST="ScreenInfoList";//筛选车辆集合
        public static String SEARCHRECORD="SearchRecord";//查岗记录集合

        public static String TRACK_VIDEOId="Track_videoID";//车辆轨迹
        public static String TRACK_STARTTIME="Track_starttime";//车辆轨迹时间
        public static String TRACK_ENDTIME="Track_endtime";//车辆轨迹时间

        public static String COMPANY_INFO="CompanyInfo";//企业

        public static String UPDATE="update";//升级通知
        public static String AREACODE="areaCode";//升级通知
        public static String POSTCHECK="postcheck";//查岗问题选择


        public static String COLOR="color";//车辆颜色
        public static String VEHICLENOMBER="vehiclenomber";//车牌号

    }

    public static class mSpre_mapName{
        public static String GuiYang = "2742";//贵阳市
        public static String LiuPanShui = "2754";//六盘水市
        public static String ZunYi = "2759";//遵义市
        public static String AnShun = "2775";//安顺市
        public static String TongRen = "2783";//铜仁地区
        public static String BiJie = "2803";//毕节地区
        public static String XingYi = "2794";//黔西南布依族苗族自治州
        public static String DuYun = "2829";//黔东南苗族侗族自治州
        public static String KaiLi = "2812";//黔南布依族苗族自治州
    }
}
