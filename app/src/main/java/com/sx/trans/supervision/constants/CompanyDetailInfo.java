package com.sx.trans.supervision.constants;

/**
 * 业户分析-公司
 */

public class CompanyDetailInfo {

    /**
     * alarm : 100
     * alarmRatio : 98
     * avgSpeed : 100
     * avgTired : 100
     * handler : 100
     * handlerRatio : 98
     * id : 11
     * name : 企业AAA
     * online : 100
     * onlineRatio : 98
     * speedAlarm : 200
     * tiredAlarm : 200
     * vehCnt : 91
     */

    /*vehCnt (integer, optional): 企业总数,
    regVehCnt (integer, optional): 入网车辆总数,
    id (integer, optional): 行业id,
    name (string, optional): 行业名称,
    online (integer, optional): 上线总数,
    onlineRatio (integer, optional): 上线率,
    alarm (integer, optional): 报警总数,
    alarmRatio (integer, optional): 报警率,
    speedAlarm (integer, optional): 超速报警条数,
    avgSpeed (integer, optional): 平均超速次数,
    tiredAlarm (integer, optional): 疲劳驾驶条数,
    avgTired (integer, optional): 平均疲劳驾驶次数,
    handler (integer, optional): 报警处理数 ,
    handlerRatio (integer, optional): 报警处理率*/

    private int alarm=0;
    private int alarmRatio=0;
    private int avgSpeed=0;
    private int avgTired=0;
    private int handler=0;
    private int handlerRatio=0;
    private int id=0;
    private String name;
    private int online=0;
    private int onlineRatio=0;
    private int speedAlarm=0;
    private int tiredAlarm=0;
    private int vehCnt=0;
    private int regVehCnt=0;

    private double regVehRatio;//入网率

    public int getAlarm() {
        return alarm;
    }

    public void setAlarm(int alarm) {
        this.alarm = alarm;
    }

    public int getAlarmRatio() {
        return alarmRatio;
    }

    public void setAlarmRatio(int alarmRatio) {
        this.alarmRatio = alarmRatio;
    }

    public int getAvgSpeed() {
        return avgSpeed;
    }

    public void setAvgSpeed(int avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    public int getAvgTired() {
        return avgTired;
    }

    public void setAvgTired(int avgTired) {
        this.avgTired = avgTired;
    }

    public int getHandler() {
        return handler;
    }

    public void setHandler(int handler) {
        this.handler = handler;
    }

    public int getHandlerRatio() {
        return handlerRatio;
    }

    public void setHandlerRatio(int handlerRatio) {
        this.handlerRatio = handlerRatio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public int getOnlineRatio() {
        return onlineRatio;
    }

    public void setOnlineRatio(int onlineRatio) {
        this.onlineRatio = onlineRatio;
    }

    public int getSpeedAlarm() {
        return speedAlarm;
    }

    public void setSpeedAlarm(int speedAlarm) {
        this.speedAlarm = speedAlarm;
    }

    public int getTiredAlarm() {
        return tiredAlarm;
    }

    public void setTiredAlarm(int tiredAlarm) {
        this.tiredAlarm = tiredAlarm;
    }

    public int getVehCnt() {
        return vehCnt;
    }

    public void setVehCnt(int vehCnt) {
        this.vehCnt = vehCnt;
    }

    public int getRegVehCnt() {
        return regVehCnt;
    }

    public void setRegVehCnt(int regVehCnt) {
        this.regVehCnt = regVehCnt;
    }

    public double getRegVehRatio() {
        return regVehRatio;
    }

    public void setRegVehRatio(double regVehRatio) {
        this.regVehRatio = regVehRatio;
    }
}
