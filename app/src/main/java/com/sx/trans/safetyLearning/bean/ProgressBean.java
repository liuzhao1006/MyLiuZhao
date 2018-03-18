package com.sx.trans.safetyLearning.bean;

import com.sx.trans.base.BaseBean;

/**
 * 作者 : 刘朝,
 * on 2017/9/7,
 * GitHub : https://github.com/liuzhao1006
 */

public class ProgressBean extends BaseBean {

    @Override
    public String toString() {
        return "ProgressBean{" +
                "CompanyName='" + CompanyName + '\'' +
                ", CompanyId='" + CompanyId + '\'' +
                ", stuNum='" + stuNum + '\'' +
                ", BasicsHours='" + BasicsHours + '\'' +
                ", Finish='" + Finish + '\'' +
                ", NoFinish='" + NoFinish + '\'' +
                ", hoursRatio='" + hoursRatio + '\'' +
                ", FinishRatio='" + FinishRatio + '\'' +
                ", ranking='" + ranking + '\'' +
                '}';
    }

    /**
     * CompanyName : 陕西测试单位
     * CompanyId : 5152
     * stuNum : 4
     * BasicsHours : 3
     * Finish : 1
     * NoFinish : 3
     * hoursRatio : 0.25
     * FinishRatio : 0.25
     * ranking : 1
     */

    private String CompanyName;
    private String CompanyId;
    private String stuNum;
    private String BasicsHours;
    private String Finish;
    private String NoFinish;
    private String hoursRatio;
    private String FinishRatio;
    private String ranking;

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String CompanyName) {
        this.CompanyName = CompanyName;
    }

    public String getCompanyId() {
        return CompanyId;
    }

    public void setCompanyId(String CompanyId) {
        this.CompanyId = CompanyId;
    }

    public String getStuNum() {
        return stuNum;
    }

    public void setStuNum(String stuNum) {
        this.stuNum = stuNum;
    }

    public String getBasicsHours() {
        return BasicsHours;
    }

    public void setBasicsHours(String BasicsHours) {
        this.BasicsHours = BasicsHours;
    }

    public String getFinish() {
        return Finish;
    }

    public void setFinish(String Finish) {
        this.Finish = Finish;
    }

    public String getNoFinish() {
        return NoFinish;
    }

    public void setNoFinish(String NoFinish) {
        this.NoFinish = NoFinish;
    }

    public String getHoursRatio() {
        return hoursRatio;
    }

    public void setHoursRatio(String hoursRatio) {
        this.hoursRatio = hoursRatio;
    }

    public String getFinishRatio() {
        return FinishRatio;
    }

    public void setFinishRatio(String FinishRatio) {
        this.FinishRatio = FinishRatio;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }
}
