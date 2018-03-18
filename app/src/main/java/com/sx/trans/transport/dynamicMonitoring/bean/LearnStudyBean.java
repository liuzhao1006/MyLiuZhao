package com.sx.trans.transport.dynamicMonitoring.bean;

import com.sx.trans.base.BaseBean;

/**
 * 作者 : 刘朝,
 * on 2017/9/12,
 * GitHub : https://github.com/liuzhao1006
 */

public class LearnStudyBean extends BaseBean {


    @Override
    public String toString() {
        return "LearnStudyBean{" +
                "Id='" + Id + '\'' +
                ", StudyPlanName='" + StudyPlanName + '\'' +
                ", StudyMonth='" + StudyMonth + '\'' +
                ", IndustryId='" + IndustryId + '\'' +
                ", StudyBatch='" + StudyBatch + '\'' +
                ", CompanyId='" + CompanyId + '\'' +
                ", BasicsHours='" + BasicsHours + '\'' +
                ", ValidHours='" + ValidHours + '\'' +
                ", IsFinish='" + IsFinish + '\'' +
                ", TrainOwnership='" + TrainOwnership + '\'' +
                '}';
    }

    /**
     * Id : 1
     * StudyPlanName : 测试学习计划
     * StudyMonth : 201705
     * IndustryId : 2042
     * StudyBatch : 1
     * CompanyId : 26
     * BasicsHours : 5
     * ValidHours : 2
     * IsFinish : 0
     * TrainOwnership : 2
     */

    private String Id;
    private String StudyPlanName;
    private String StudyMonth;
    private String IndustryId;
    private String StudyBatch;
    private String CompanyId;
    private String BasicsHours;
    private String ValidHours;
    private String IsFinish;
    private String TrainOwnership;

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getStudyPlanName() {
        return StudyPlanName;
    }

    public void setStudyPlanName(String StudyPlanName) {
        this.StudyPlanName = StudyPlanName;
    }

    public String getStudyMonth() {
        return StudyMonth;
    }

    public void setStudyMonth(String StudyMonth) {
        this.StudyMonth = StudyMonth;
    }

    public String getIndustryId() {
        return IndustryId;
    }

    public void setIndustryId(String IndustryId) {
        this.IndustryId = IndustryId;
    }

    public String getStudyBatch() {
        return StudyBatch;
    }

    public void setStudyBatch(String StudyBatch) {
        this.StudyBatch = StudyBatch;
    }

    public String getCompanyId() {
        return CompanyId;
    }

    public void setCompanyId(String CompanyId) {
        this.CompanyId = CompanyId;
    }

    public String getBasicsHours() {
        return BasicsHours;
    }

    public void setBasicsHours(String BasicsHours) {
        this.BasicsHours = BasicsHours;
    }

    public String getValidHours() {
        return ValidHours;
    }

    public void setValidHours(String ValidHours) {
        this.ValidHours = ValidHours;
    }

    public String getIsFinish() {
        return IsFinish;
    }

    public void setIsFinish(String IsFinish) {
        this.IsFinish = IsFinish;
    }

    public String getTrainOwnership() {
        return TrainOwnership;
    }

    public void setTrainOwnership(String TrainOwnership) {
        this.TrainOwnership = TrainOwnership;
    }
}
