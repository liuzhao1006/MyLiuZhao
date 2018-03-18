package com.sx.trans.supervision.constants;

/**
 * 业户分析
 */

public class OwnerInfo {
    /**
     * id : 1
     * name : 班线客运
     * regVehCnt : 23
     * unitCnt : 1
     *
     */

    private int id;//行业id
    private String name;//行业名称
    private int regVehCnt=0;//入网车辆总数
    private int unitCnt=0;//企业总数


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

    public int getRegVehCnt() {
        return regVehCnt;
    }

    public void setRegVehCnt(int regVehCnt) {
        this.regVehCnt = regVehCnt;
    }

    public int getUnitCnt() {
        return unitCnt;
    }

    public void setUnitCnt(int unitCnt) {
        this.unitCnt = unitCnt;
    }

}
