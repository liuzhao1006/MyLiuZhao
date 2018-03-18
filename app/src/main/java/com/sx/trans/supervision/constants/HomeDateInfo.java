package com.sx.trans.supervision.constants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by mr_wang on 2017/8/29.
 */

public class HomeDateInfo implements Serializable {
    private int alarmCnt=0;//报警总数
    private int onlineCnt=0;//在线总数
    private int platformCnt=0;//接入平台总数
    private int regVehCnt=0;//入网总数
    private int vehCnt=0;//车辆总数
    private Map<String,MapList> map;
    private ArrayList<TradeList> trade;


    private int otherCnt;//其他报警
    private int overSpCnt;// 超速报警
    private int tiredCnt;// 疲劳报警



    public int getAlarmCnt() {
        return alarmCnt;
    }

    public void setAlarmCnt(int alarmCnt) {
        this.alarmCnt = alarmCnt;
    }

    public int getOnlineCnt() {
        return onlineCnt;
    }

    public void setOnlineCnt(int onlineCnt) {
        this.onlineCnt = onlineCnt;
    }

    public int getPlatformCnt() {
        return platformCnt;
    }

    public void setPlatformCnt(int platformCnt) {
        this.platformCnt = platformCnt;
    }

    public int getRegVehCnt() {
        return regVehCnt;
    }

    public void setRegVehCnt(int regVehCnt) {
        this.regVehCnt = regVehCnt;
    }

    public int getVehCnt() {
        return vehCnt;
    }

    public void setVehCnt(int vehCnt) {
        this.vehCnt = vehCnt;
    }

    public Map<String,MapList> getMap() {
        return map;
    }

    public void setMap(Map<String,MapList> map) {
        this.map = map;
    }

    public ArrayList<TradeList> getTrade() {
        return trade;
    }

    public void setTrade(ArrayList<TradeList> trade) {
        this.trade = trade;
    }

    public int getOtherCnt() {
        return otherCnt;
    }

    public void setOtherCnt(int otherCnt) {
        this.otherCnt = otherCnt;
    }

    public int getOverSpCnt() {
        return overSpCnt;
    }

    public void setOverSpCnt(int overSpCnt) {
        this.overSpCnt = overSpCnt;
    }

    public int getTiredCnt() {
        return tiredCnt;
    }

    public void setTiredCnt(int tiredCnt) {
        this.tiredCnt = tiredCnt;
    }

}
