package com.sx.trans.supervision.constants;

import java.io.Serializable;

/**
 * Created by mr_wang on 2017/8/31.
 * 入网车辆比例
 */

public class TradeList implements Serializable {

    private int id;// 行业id,
    private String name;//行业名称,
    private int vehCnt=0;//车辆总数,
    private int  noRegVehCnt=0;//未入网总数
    private int totalRegVehCnt=0;//全省入网总数
    private int regVehCnt=0;//入网总数

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

    public int getVehCnt() {
        return vehCnt;
    }

    public void setVehCnt(int vehCnt) {
        this.vehCnt = vehCnt;
    }

    public int getNoRegVehCnt() {
        return noRegVehCnt;
    }

    public void setNoRegVehCnt(int noRegVehCnt) {
        this.noRegVehCnt = noRegVehCnt;
    }

    public int getTotalRegVehCnt() {
        return totalRegVehCnt;
    }

    public void setTotalRegVehCnt(int totalRegVehCnt) {
        this.totalRegVehCnt = totalRegVehCnt;
    }

    public int getRegVehCnt() {
        return regVehCnt;
    }

    public void setRegVehCnt(int regVehCnt) {
        this.regVehCnt = regVehCnt;
    }
}
