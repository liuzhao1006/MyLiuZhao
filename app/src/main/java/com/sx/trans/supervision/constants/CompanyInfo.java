package com.sx.trans.supervision.constants;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * 业户分析-公司
 */

public class CompanyInfo extends DataSupport implements Serializable {

    /**
     * id : 1
     * name : 企业AAA
     * regVehCnt : 0
     * vehCnt : 23
     */

    private int id;
    private String name;
    private int regVehCnt=0;
    private int vehCnt=0;
    private int uid=0;

    //本地历史搜索记录
    private int search=-1;//0表示已存在该搜索记录

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

    public int getVehCnt() {
        return vehCnt;
    }

    public void setVehCnt(int vehCnt) {
        this.vehCnt = vehCnt;
    }

    public int getSearch() {
        return search;
    }

    public void setSearch(int search) {
        this.search = search;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
