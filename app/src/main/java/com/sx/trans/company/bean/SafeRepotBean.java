package com.sx.trans.company.bean;

import com.sx.trans.base.BaseBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xxxxxx on 2017/11/22.安全报告
 */

public class SafeRepotBean extends BaseBean {

    /**
     * code : 0
     * data : [{"alarmCount":6,"alarmVehicleCount":5,"alarmVehicleRate":0.8333,"analysisDate":"2017-09-26","companyId":5,"companyName":"长顺县兴隆汽运有限责任公司","id":1,"overSpeedCount":7,"overSpeedHandler":0,"prohibitDrivingCount":1,"prohibitDrivingHandler":0,"speed160Count":5,"tiredCount":9,"tiredDuration":10,"tiredHandler":0,"vehicleAccessCount":3,"vehicleOnlineRate":2}]
     * message : 有数据
     */

    private int code;
    private String message;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * 日期：analysisDate
         入网车数：vehicleAccessCount
         在线率：vehicleOnlineRate



         疲劳驾驶
         报警数：tiredCount
         时长：tiredDuration
         已处理：tiredHandler
         2-5点行车
         报警数：prohibitDrivingCount
         已处理：prohibitDrivingHandler
         * alarmCount : 6 报警总数
         * alarmVehicleCount : 5  报警车辆数
         * alarmVehicleRate : 0.8333 报警率
         * analysisDate : 2017-09-26
         * companyId : 5
         * companyName : 长顺县兴隆汽运有限责任公司
         * id : 1
         * 超速
         * overSpeedCount : 7 报警数
         * overSpeedHandler : 0 已处理
         * prohibitDrivingCount : 1
         * prohibitDrivingHandler : 0
         * speed160Count : 5 超过160KM/H
         * tiredCount : 9
         * tiredDuration : 10
         * tiredHandler : 0
         * vehicleAccessCount : 3
         * vehicleOnlineRate : 2.0
         */

        private int alarmCount;
        private int alarmVehicleCount;
        private double alarmVehicleRate;
        private String analysisDate;
        private int companyId;
        private String companyName;
        private int id;
        private int overSpeedCount;
        private int overSpeedHandler;
        private int prohibitDrivingCount;
        private int prohibitDrivingHandler;
        private int speed160Count;
        private int tiredCount;
        private int tiredDuration;
        private int tiredHandler;
        private int vehicleAccessCount;
        private double vehicleOnlineRate;

        public int getAlarmCount() {
            return alarmCount;
        }

        public void setAlarmCount(int alarmCount) {
            this.alarmCount = alarmCount;
        }

        public int getAlarmVehicleCount() {
            return alarmVehicleCount;
        }

        public void setAlarmVehicleCount(int alarmVehicleCount) {
            this.alarmVehicleCount = alarmVehicleCount;
        }

        public double getAlarmVehicleRate() {
            return alarmVehicleRate;
        }

        public void setAlarmVehicleRate(double alarmVehicleRate) {
            this.alarmVehicleRate = alarmVehicleRate;
        }

        public String getAnalysisDate() {
            return analysisDate;
        }

        public void setAnalysisDate(String analysisDate) {
            this.analysisDate = analysisDate;
        }

        public int getCompanyId() {
            return companyId;
        }

        public void setCompanyId(int companyId) {
            this.companyId = companyId;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getOverSpeedCount() {
            return overSpeedCount;
        }

        public void setOverSpeedCount(int overSpeedCount) {
            this.overSpeedCount = overSpeedCount;
        }

        public int getOverSpeedHandler() {
            return overSpeedHandler;
        }

        public void setOverSpeedHandler(int overSpeedHandler) {
            this.overSpeedHandler = overSpeedHandler;
        }

        public int getProhibitDrivingCount() {
            return prohibitDrivingCount;
        }

        public void setProhibitDrivingCount(int prohibitDrivingCount) {
            this.prohibitDrivingCount = prohibitDrivingCount;
        }

        public int getProhibitDrivingHandler() {
            return prohibitDrivingHandler;
        }

        public void setProhibitDrivingHandler(int prohibitDrivingHandler) {
            this.prohibitDrivingHandler = prohibitDrivingHandler;
        }

        public int getSpeed160Count() {
            return speed160Count;
        }

        public void setSpeed160Count(int speed160Count) {
            this.speed160Count = speed160Count;
        }

        public int getTiredCount() {
            return tiredCount;
        }

        public void setTiredCount(int tiredCount) {
            this.tiredCount = tiredCount;
        }

        public int getTiredDuration() {
            return tiredDuration;
        }

        public void setTiredDuration(int tiredDuration) {
            this.tiredDuration = tiredDuration;
        }

        public int getTiredHandler() {
            return tiredHandler;
        }

        public void setTiredHandler(int tiredHandler) {
            this.tiredHandler = tiredHandler;
        }

        public int getVehicleAccessCount() {
            return vehicleAccessCount;
        }

        public void setVehicleAccessCount(int vehicleAccessCount) {
            this.vehicleAccessCount = vehicleAccessCount;
        }

        public double getVehicleOnlineRate() {
            return vehicleOnlineRate;
        }

        public void setVehicleOnlineRate(double vehicleOnlineRate) {
            this.vehicleOnlineRate = vehicleOnlineRate;
        }
    }
}
