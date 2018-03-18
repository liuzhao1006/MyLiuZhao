package com.sx.trans.transport.home.bean;

import com.sx.trans.base.BaseBean;

/**
 * Created by xxxxxx on 2017/11/27.
 */

public class YearPlanBean extends BaseBean {

    @Override
    public String toString() {
        return "YearPlanBean{" +
                "ProfessionName='" + ProfessionName + '\'' +
                ", IndustryId='" + IndustryId + '\'' +
                '}';
    }

    /**
     * ProfessionName : 危险货物运输驾驶
     * IndustryId : 2044
     */

    private String ProfessionName;
    private String IndustryId;

    public String getProfessionName() {
        return ProfessionName;
    }

    public void setProfessionName(String ProfessionName) {
        this.ProfessionName = ProfessionName;
    }

    public String getIndustryId() {
        return IndustryId;
    }

    public void setIndustryId(String IndustryId) {
        this.IndustryId = IndustryId;
    }
}
