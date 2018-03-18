package com.sx.trans.supervision.constants;

import java.util.ArrayList;

/**
 * Created by mr_wang on 2017/8/29.
 * 行业分析
 */

public class IndustryAnalysisInfo {


    private int vehCnt=0 ;//车辆数,
    private int lineCnt=0;//班线,
    private int travleCnt=0;//旅线,
    private int dangerCnt=0;// 危运,
    private int otherCnt=0;//其他,
    private ArrayList<RegTrend> lineRegTrend ;//班线客运每月新增入网趋势,
    private ArrayList<RegTrend> travelRegTrend ;//旅线客运每月新增入网趋势,
    private ArrayList<RegTrend> dangerRegTrend ;// 危运车辆每月新增入网趋势,
    private ArrayList<RegTrend> otherRegTrend ;//其他车辆每月新增入网趋势
    private ArrayList<TradeList> trade;



    public static class  RegTrend{
        String month;//月份
        int cnt;//统计数

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }
        public int getCnt() {
            return cnt;
        }

        public void setCnt(int cnt) {
            this.cnt = cnt;
        }
    }

    public int getVehCnt() {
        return vehCnt;
    }

    public void setVehCnt(int vehCnt) {
        this.vehCnt = vehCnt;
    }

    public int getLineCnt() {
        return lineCnt;
    }

    public void setLineCnt(int lineCnt) {
        this.lineCnt = lineCnt;
    }

    public int getTravleCnt() {
        return travleCnt;
    }

    public void setTravleCnt(int travleCnt) {
        this.travleCnt = travleCnt;
    }

    public int getDangerCnt() {
        return dangerCnt;
    }

    public void setDangerCnt(int dangerCnt) {
        this.dangerCnt = dangerCnt;
    }

    public int getOtherCnt() {
        return otherCnt;
    }

    public void setOtherCnt(int otherCnt) {
        this.otherCnt = otherCnt;
    }

    public ArrayList<TradeList> getTrade() {
        return trade;
    }

    public void setTrade(ArrayList<TradeList> trade) {
        this.trade = trade;
    }



    public ArrayList<RegTrend> getLineRegTrend() {
        return lineRegTrend;
    }

    public void setLineRegTrend(ArrayList<RegTrend> lineRegTrend) {
        this.lineRegTrend = lineRegTrend;
    }

    public ArrayList<RegTrend> getTravelRegTrend() {
        return travelRegTrend;
    }

    public void setTravelRegTrend(ArrayList<RegTrend> travelRegTrend) {
        this.travelRegTrend = travelRegTrend;
    }

    public ArrayList<RegTrend> getDangerRegTrend() {
        return dangerRegTrend;
    }

    public void setDangerRegTrend(ArrayList<RegTrend> dangerRegTrend) {
        this.dangerRegTrend = dangerRegTrend;
    }

    public ArrayList<RegTrend> getOtherRegTrend() {
        return otherRegTrend;
    }

    public void setOtherRegTrend(ArrayList<RegTrend> otherRegTrend) {
        this.otherRegTrend = otherRegTrend;
    }
}
