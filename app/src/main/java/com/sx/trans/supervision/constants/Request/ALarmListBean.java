package com.sx.trans.supervision.constants.Request;

import java.io.Serializable;

/**
 * Created by xxxxxx on 2017/12/18.
 */

public class ALarmListBean implements Serializable {

    @Override
    public String toString() {
        return "ALarmListBean{" +
                "alarmCount=" + alarmCount +
                ", alarmVehicleCount=" + alarmVehicleCount +
                ", alarmVehicleRate=" + alarmVehicleRate +
                ", companyIdOne=" + companyIdOne +
                ", companyIdTwo=" + companyIdTwo +
                ", companyName='" + companyName + '\'' +
                ", handleCount=" + handleCount +
                ", handleCountOther=" + handleCountOther +
                ", handleCountOtherRate=" + handleCountOtherRate +
                ", handleCountOverspeed=" + handleCountOverspeed +
                ", handleCountOverspeedRate=" + handleCountOverspeedRate +
                ", handleCountRate=" + handleCountRate +
                ", handleCountTired=" + handleCountTired +
                ", handleCountTiredRate=" + handleCountTiredRate +
                ", id=" + id +
                ", otherAlarmCount=" + otherAlarmCount +
                ", overSpeedCount=" + overSpeedCount +
                ", overSpeedCountAvg=" + overSpeedCountAvg +
                ", timeOne='" + timeOne + '\'' +
                ", tiredCount=" + tiredCount +
                ", tiredCountAvg=" + tiredCountAvg +
                ", vehicleAccessCount=" + vehicleAccessCount +
                ", vehicleAccessRate=" + vehicleAccessRate +
                ", vehicleCount=" + vehicleCount +
                ", vehicleOnlineCount=" + vehicleOnlineCount +
                ", vehicleOnlineRate=" + vehicleOnlineRate +
                '}';
    }

    /**
     * alarmCount : 6
     * alarmVehicleCount : 5
     * alarmVehicleRate : 0
     * companyIdOne : 5
     * companyIdTwo : 0
     * companyName : 长顺县兴隆汽运有限责任公司
     * handleCount : 0
     * handleCountOther : 2
     * handleCountOtherRate : 0.33
     * handleCountOverspeed : 0
     * handleCountOverspeedRate : 0
     * handleCountRate : 0
     * handleCountTired : 0
     * handleCountTiredRate : 0
     * id : 5
     * otherAlarmCount : 14
     * overSpeedCount : 7
     * overSpeedCountAvg : 0
     * timeOne : 2017-12-18
     * tiredCount : 9
     * tiredCountAvg : 0
     * vehicleAccessCount : 0
     * vehicleAccessRate : 0
     * vehicleCount : 0
     * vehicleOnlineCount : 0
     * vehicleOnlineRate : 0
     */

    private int alarmCount;
    private int alarmVehicleCount;
    private int alarmVehicleRate;
    private int companyIdOne;
    private int companyIdTwo;
    private String companyName;
    private int handleCount;
    private int handleCountOther;
    private double handleCountOtherRate;
    private int handleCountOverspeed;
    private int handleCountOverspeedRate;
    private int handleCountRate;
    private int handleCountTired;
    private int handleCountTiredRate;
    private int id;
    private int otherAlarmCount;
    private int overSpeedCount;
    private int overSpeedCountAvg;
    private String timeOne;
    private int tiredCount;
    private int tiredCountAvg;
    private int vehicleAccessCount;
    private int vehicleAccessRate;
    private int vehicleCount;
    private int vehicleOnlineCount;
    private int vehicleOnlineRate;

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

    public int getAlarmVehicleRate() {
        return alarmVehicleRate;
    }

    public void setAlarmVehicleRate(int alarmVehicleRate) {
        this.alarmVehicleRate = alarmVehicleRate;
    }

    public int getCompanyIdOne() {
        return companyIdOne;
    }

    public void setCompanyIdOne(int companyIdOne) {
        this.companyIdOne = companyIdOne;
    }

    public int getCompanyIdTwo() {
        return companyIdTwo;
    }

    public void setCompanyIdTwo(int companyIdTwo) {
        this.companyIdTwo = companyIdTwo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getHandleCount() {
        return handleCount;
    }

    public void setHandleCount(int handleCount) {
        this.handleCount = handleCount;
    }

    public int getHandleCountOther() {
        return handleCountOther;
    }

    public void setHandleCountOther(int handleCountOther) {
        this.handleCountOther = handleCountOther;
    }

    public double getHandleCountOtherRate() {
        return handleCountOtherRate;
    }

    public void setHandleCountOtherRate(double handleCountOtherRate) {
        this.handleCountOtherRate = handleCountOtherRate;
    }

    public int getHandleCountOverspeed() {
        return handleCountOverspeed;
    }

    public void setHandleCountOverspeed(int handleCountOverspeed) {
        this.handleCountOverspeed = handleCountOverspeed;
    }

    public int getHandleCountOverspeedRate() {
        return handleCountOverspeedRate;
    }

    public void setHandleCountOverspeedRate(int handleCountOverspeedRate) {
        this.handleCountOverspeedRate = handleCountOverspeedRate;
    }

    public int getHandleCountRate() {
        return handleCountRate;
    }

    public void setHandleCountRate(int handleCountRate) {
        this.handleCountRate = handleCountRate;
    }

    public int getHandleCountTired() {
        return handleCountTired;
    }

    public void setHandleCountTired(int handleCountTired) {
        this.handleCountTired = handleCountTired;
    }

    public int getHandleCountTiredRate() {
        return handleCountTiredRate;
    }

    public void setHandleCountTiredRate(int handleCountTiredRate) {
        this.handleCountTiredRate = handleCountTiredRate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOtherAlarmCount() {
        return otherAlarmCount;
    }

    public void setOtherAlarmCount(int otherAlarmCount) {
        this.otherAlarmCount = otherAlarmCount;
    }

    public int getOverSpeedCount() {
        return overSpeedCount;
    }

    public void setOverSpeedCount(int overSpeedCount) {
        this.overSpeedCount = overSpeedCount;
    }

    public int getOverSpeedCountAvg() {
        return overSpeedCountAvg;
    }

    public void setOverSpeedCountAvg(int overSpeedCountAvg) {
        this.overSpeedCountAvg = overSpeedCountAvg;
    }

    public String getTimeOne() {
        return timeOne;
    }

    public void setTimeOne(String timeOne) {
        this.timeOne = timeOne;
    }

    public int getTiredCount() {
        return tiredCount;
    }

    public void setTiredCount(int tiredCount) {
        this.tiredCount = tiredCount;
    }

    public int getTiredCountAvg() {
        return tiredCountAvg;
    }

    public void setTiredCountAvg(int tiredCountAvg) {
        this.tiredCountAvg = tiredCountAvg;
    }

    public int getVehicleAccessCount() {
        return vehicleAccessCount;
    }

    public void setVehicleAccessCount(int vehicleAccessCount) {
        this.vehicleAccessCount = vehicleAccessCount;
    }

    public int getVehicleAccessRate() {
        return vehicleAccessRate;
    }

    public void setVehicleAccessRate(int vehicleAccessRate) {
        this.vehicleAccessRate = vehicleAccessRate;
    }

    public int getVehicleCount() {
        return vehicleCount;
    }

    public void setVehicleCount(int vehicleCount) {
        this.vehicleCount = vehicleCount;
    }

    public int getVehicleOnlineCount() {
        return vehicleOnlineCount;
    }

    public void setVehicleOnlineCount(int vehicleOnlineCount) {
        this.vehicleOnlineCount = vehicleOnlineCount;
    }

    public int getVehicleOnlineRate() {
        return vehicleOnlineRate;
    }

    public void setVehicleOnlineRate(int vehicleOnlineRate) {
        this.vehicleOnlineRate = vehicleOnlineRate;
    }
}
