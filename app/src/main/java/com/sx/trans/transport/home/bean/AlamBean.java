package com.sx.trans.transport.home.bean;

import com.sx.trans.base.BaseBean;

/**
 * Created by liuchao on 2017/9/27.
 * 报警
 */

public class AlamBean extends BaseBean {

    @Override
    public String toString() {
        return "AlamBean{" +
                "aLLNORisk=" + aLLNORisk +
                ", aLLRisk=" + aLLRisk +
                ", dayRisk=" + dayRisk +
                '}';
    }

    /**
     * aLLNORisk : 0
     * aLLRisk : 0
     * dayRisk : 0
     */

    private int aLLNORisk;
    private int aLLRisk;
    private int dayRisk;

    public int getALLNORisk() {
        return aLLNORisk;
    }

    public void setALLNORisk(int aLLNORisk) {
        this.aLLNORisk = aLLNORisk;
    }

    public int getALLRisk() {
        return aLLRisk;
    }

    public void setALLRisk(int aLLRisk) {
        this.aLLRisk = aLLRisk;
    }

    public int getDayRisk() {
        return dayRisk;
    }

    public void setDayRisk(int dayRisk) {
        this.dayRisk = dayRisk;
    }
}
