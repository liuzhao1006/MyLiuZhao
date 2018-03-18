package com.sx.trans.supervision.constants;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by mr_wang on 2017/8/29.
 */

public class WarmAnaysisInfo implements Serializable {


    private int vehCnt = 0;//车辆数,
    private int onLineVehCnt = 0;//当前在线车辆数,
    private int overSpCnt = 0;//今日超速,
    private int tiredCnt = 0;// 今日疲劳,
    private int otherCnt = 0;//其他报警,
    //    {"alarmCnt":101.0,"handerStatistic":[
//        {"cnt":195.0,"handlerCnt":195.0,"name":"超速报警","type":1.0},
//        {"cnt":3.0,"handlerCnt":3.0,"name":"疲劳驾驶报警","type":2.0},
//        {"cnt":5.0,"handlerCnt":5.0,"type":3.0}],
//        "onLineVehCnt":458.0,"otherAlarmCnt":3.0,"otherCnt":3.0,"overSpCnt":100.0,
//            "tiredCnt":2.0,"vehCnt":6379.0}
    private ArrayList<HanderStattis> handerStatistic;


    public class HanderStattis {

        private int cnt = 0;
        private int handlerCnt = 0;
        private String name = "-";
        private int type = 0;

        public int getCnt() {
            return cnt;
        }

        public void setCnt(int cnt) {
            this.cnt = cnt;
        }

        public int getHandlerCnt() {
            return handlerCnt;
        }

        public void setHandlerCnt(int handlerCnt) {
            this.handlerCnt = handlerCnt;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }


    }

    public int getVehCnt() {
        return vehCnt;
    }

    public void setVehCnt(int vehCnt) {
        this.vehCnt = vehCnt;
    }

    public int getOnLineVehCnt() {
        return onLineVehCnt;
    }

    public void setOnLineVehCnt(int onLineVehCnt) {
        this.onLineVehCnt = onLineVehCnt;
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

    public int getOtherCnt() {
        return otherCnt;
    }

    public void setOtherCnt(int otherCnt) {
        this.otherCnt = otherCnt;
    }


    public ArrayList<HanderStattis> getHanderStatistic() {
        return handerStatistic;
    }

    public void setHanderStatistic(ArrayList<HanderStattis> handerStatistic) {
        this.handerStatistic = handerStatistic;
    }


}
