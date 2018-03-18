package com.sx.trans.supervision.constants.Request;

import java.io.Serializable;

/**
 * Created by xxxxxx on 2017/12/18.
 */

public class HomeAlarBean implements Serializable {

    @Override
    public String toString() {
        return "HomeAlarBean{" +
                "alarmCount=" + alarmCount +
                ", alarmVehicleCount=" + alarmVehicleCount +
                ", alarmVehicleRate=" + alarmVehicleRate +
                ", cId=" + cId +
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
                ", timeAnalysis='" + timeAnalysis + '\'' +
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
     * alarmCount : 20
     * alarmVehicleCount : 19
     * alarmVehicleRate : 0
     * cId : 0
     * handleCount : 0
     * handleCountOther : 3
     * handleCountOtherRate : 0.15
     * handleCountOverspeed : 2
     * handleCountOverspeedRate : 0.1
     * handleCountRate : 0
     * handleCountTired : 1
     * handleCountTiredRate : 0.05
     * id : 0
     * otherAlarmCount : 18
     * overSpeedCount : 22
     * overSpeedCountAvg : 0
     * timeAnalysis : 2017-12-18
     * tiredCount : 11
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
    private int cId;
    private int handleCount;
    private int handleCountOther;
    private double handleCountOtherRate;
    private int handleCountOverspeed;
    private double handleCountOverspeedRate;
    private int handleCountRate;
    private int handleCountTired;
    private double handleCountTiredRate;
    private int id;
    private int otherAlarmCount;
    private int overSpeedCount;
    private int overSpeedCountAvg;
    private String timeAnalysis;
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

    public int getCId() {
        return cId;
    }

    public void setCId(int cId) {
        this.cId = cId;
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

    public double getHandleCountOverspeedRate() {
        return handleCountOverspeedRate;
    }

    public void setHandleCountOverspeedRate(double handleCountOverspeedRate) {
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

    public double getHandleCountTiredRate() {
        return handleCountTiredRate;
    }

    public void setHandleCountTiredRate(double handleCountTiredRate) {
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

    public String getTimeAnalysis() {
        return timeAnalysis;
    }

    public void setTimeAnalysis(String timeAnalysis) {
        this.timeAnalysis = timeAnalysis;
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
