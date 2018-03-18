package com.sx.trans.supervision.constants;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by mr_wang on 2017/8/22.
 * 车辆信息
 */

public class VehicleInfo extends DataSupport implements Serializable {


    //车辆数据库序列id
    private int id;
    //速度
    private double  gpsSpeed;
    private String  gpsTime;
    //纬度
    private double  latitude;
    //经度
    private double  longitude;
    //里程
    private double  mileage;
    //方向
    private int  head=0;
    //车辆id
    private int vid=-1;
    //车辆颜色
    private String colorName;
    //所属企业
    private String entName;
    //所属平台
    private String platName;
    // 车牌号
    private String regisNo;
    //车牌号的颜色Id
    private int color=-1;
    //报警信息
    private String alarm;
    //入网时间
    private String netTime;
    //设备ID号
    private String commNo;
    //本地历史搜索记录
    private int Search=-1;//0表示已存在该搜索记录
    //判断是否可观看视频
    private String videoParam;
    //视频播放类型
    private int videoType=-1;

    private int status=0;//状态(1 在线;0 离线;2 报警)

    private String accStatus;//车辆状态

    public String getRegisNo() {
        return regisNo;
    }

    public void setRegisNo(String regisNo) {
        this.regisNo = regisNo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }


    public int getSearch() {
        return Search;
    }

    public void setSearch(int search) {
        Search = search;
    }

    public String getParamValue() {
        return videoParam;
    }

    public void setParamValue(String vedioParam) {
        this.videoParam = vedioParam;
    }

    public int getVideoType() {
        return videoType;
    }

    public void setVideoType(int videoType) {
        this.videoType = videoType;
    }




    public String getRegistrationNo() {
        return regisNo;
    }

    public void setRegistrationNo(String registrationNo) {
        this.regisNo = registrationNo;
    }


    public String getCommNo() {
        return commNo;
    }

    public void setCommNo(String commNo) {
        this.commNo = commNo;
    }




    public double getGpsSpeed() {
        return gpsSpeed;
    }

    public void setGpsSpeed(double gpsSpeed) {
        this.gpsSpeed = gpsSpeed;
    }


    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }


    public int getHead() {
        return head;
    }

    public void setHead(int head) {
        this.head = head;
    }

    public String getGpsTime() {
        return gpsTime;
    }

    public void setGpsTime(String gpsTime) {
        this.gpsTime = gpsTime;
    }

    public String getAlarm() {
        return alarm;
    }

    public void setAlarm(String alarm) {
        this.alarm = alarm;
    }

    public String getNetTime() {
        return netTime;
    }

    public void setNetTime(String netTime) {
        this.netTime = netTime;
    }

    public int getVid() {
        return vid;
    }

    public void setVid(int vid) {
        this.vid = vid;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getEntName() {
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName;
    }

    public String getPlatName() {
        return platName;
    }

    public void setPlatName(String platName) {
        this.platName = platName;
    }

    public String getAccStatus() {
        return accStatus;
    }

    public void setAccStatus(String accStatus) {
        this.accStatus = accStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
