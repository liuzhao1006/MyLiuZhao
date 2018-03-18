package com.sx.trans.supervision.constants;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by mr_wang on 2017/8/31.
 * 区域分析详细
 */

public class RegionAnalsisInfo implements Serializable {
    private String areaCode;
    private int alarmCnt;//今日报警
    private int overSpCnt;//超速报警
    private int tiredCnt;//今日疲劳报警
    private int otherAlarmCnt;//今日疲劳报警
    private int onLineVehCnt;//当前在线
    private int regVehCnt;//区域入网车辆数
    private int vehCnt;//区域车辆数
    private ArrayList<RegTrent>regTrent;
    private ArrayList<TradeList>trade;

    public static class RegTrent{
        public int getCnt() {
            return cnt;
        }

        public void setCnt(int cnt) {
            this.cnt = cnt;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        private int cnt;
        private String month;

    }

    public int getAlarmCnt() {
        return alarmCnt;
    }

    public void setAlarmCnt(int alarmCnt) {
        this.alarmCnt = alarmCnt;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public int getOnLineVehCnt() {
        return onLineVehCnt;
    }

    public void setOnLineVehCnt(int onLineVehCnt) {
        this.onLineVehCnt = onLineVehCnt;
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

    public ArrayList<RegTrent> getRegTrent() {
        return regTrent;
    }

    public void setRegTrent(ArrayList<RegTrent> regTrent) {
        this.regTrent = regTrent;
    }

    public ArrayList<TradeList> getTrade() {
        return trade;
    }

    public void setTrade(ArrayList<TradeList> trade) {
        this.trade = trade;
    }

    public int getTiredCnt() {
        return tiredCnt;
    }

    public void setTiredCnt(int tiredCnt) {
        this.tiredCnt = tiredCnt;
    }

    public int getOtherAlarmCnt() {
        return otherAlarmCnt;
    }

    public void setOtherAlarmCnt(int otherAlarmCnt) {
        this.otherAlarmCnt = otherAlarmCnt;
    }

    public int getOverSpCnt() {
        return overSpCnt;
    }

    public void setOverSpCnt(int overSpCnt) {
        this.overSpCnt = overSpCnt;
    }
}
