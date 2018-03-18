package com.sx.trans.company.bean;

import com.sx.trans.base.BaseBean;

/**
 * Created by liuchao on 2017/9/26.
 * 安全风险指数
 */

public class CommpanyRiskBean extends BaseBean {

    @Override
    public String toString() {
        return "CommpanyRiskBean{" +
                "averageRisk=" + averageRisk +
                ", handleRisk=" + handleRisk +
                ", moderateRisk=" + moderateRisk +
                ", riskValue=" + riskValue +
                ", riskValueP=" + riskValueP +
                ", riskValueYesterday=" + riskValueYesterday +
                ", significantRisk=" + significantRisk +
                '}';
    }

    /**
     * averageRisk : 0
     * handleRisk : 0
     * moderateRisk : 0
     * riskValue : 0.0
     * riskValueP : 0.0
     * riskValueYesterday : 0.0
     * significantRisk : 0
     * <p>
     * “平均风险”：0，
     * “处理风险”：0，
     * “中等风险”：0，
     * “风险价值”：0，
     * “风险价值p”：0，
     * “风险价值昨天”：0，
     * “重大风险”：0
     */

    private int averageRisk;
    private int handleRisk;
    private int moderateRisk;
    private double riskValue;
    private double riskValueP;
    private double riskValueYesterday;
    private int significantRisk;

    public int getAverageRisk() {
        return averageRisk;
    }

    public void setAverageRisk(int averageRisk) {
        this.averageRisk = averageRisk;
    }

    public int getHandleRisk() {
        return handleRisk;
    }

    public void setHandleRisk(int handleRisk) {
        this.handleRisk = handleRisk;
    }

    public int getModerateRisk() {
        return moderateRisk;
    }

    public void setModerateRisk(int moderateRisk) {
        this.moderateRisk = moderateRisk;
    }

    public double getRiskValue() {
        return riskValue;
    }

    public void setRiskValue(double riskValue) {
        this.riskValue = riskValue;
    }

    public double getRiskValueP() {
        return riskValueP;
    }

    public void setRiskValueP(double riskValueP) {
        this.riskValueP = riskValueP;
    }

    public double getRiskValueYesterday() {
        return riskValueYesterday;
    }

    public void setRiskValueYesterday(double riskValueYesterday) {
        this.riskValueYesterday = riskValueYesterday;
    }

    public int getSignificantRisk() {
        return significantRisk;
    }

    public void setSignificantRisk(int significantRisk) {
        this.significantRisk = significantRisk;
    }
}
