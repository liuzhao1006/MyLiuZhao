package com.sx.trans.company.bean;

import com.sx.trans.base.BaseBean;

import java.util.List;

/**
 * 作者: 刘朝
 * 日期: 2017/10/10
 * 描述: 安全目标
 */

public class SpecialTargerBean extends BaseBean {


    /**
     * accidentDeathRate : 0
     * accidentProbability : 0
     * companyName : 陕西亿程交通信息有限公司
     * fileContents : <p>sdf</p>
     * fileInfo : 60363507-A255-4B04-BBBA-AB57EDE8F023.png::/ueditor/jsp/upload/file/20170923/1506146981239035847.png
     * fileTitle : sdf
     * formulateDate : 2017-09-23 14:09:07
     * id : 45
     * issueDate : 2017-09-23 14:09:10
     * kpiInfos : [{"beginTime":1506146961000,"checkType":"1","endTime":1506146963000,"id":129,"keyId":45,"kpiName":"zxc"}]
     * personnelInjuryRate : 0
     * pointName : sdf
     * propertyLoss : 0
     * questionMark : XXX发（2017）1号
     * safetyAccidentCount : 0
     * use : true
     * years : 2017
     */

    private String accidentDeathRate;
    private String accidentProbability;
    private String companyName;
    private String fileContents;
    private String fileInfo;
    private String fileTitle;
    private String formulateDate;
    private String id;
    private String issueDate;
    private String personnelInjuryRate;
    private String pointName;
    private String propertyLoss;
    private String questionMark;
    private String safetyAccidentCount;
    private boolean use;
    private String years;
    private List<KpiInfosBean> kpiInfos;

    public String getAccidentDeathRate() {
        return accidentDeathRate;
    }

    public void setAccidentDeathRate(String accidentDeathRate) {
        this.accidentDeathRate = accidentDeathRate;
    }

    public String getAccidentProbability() {
        return accidentProbability;
    }

    public void setAccidentProbability(String accidentProbability) {
        this.accidentProbability = accidentProbability;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getFileContents() {
        return fileContents;
    }

    public void setFileContents(String fileContents) {
        this.fileContents = fileContents;
    }

    public String getFileInfo() {
        return fileInfo;
    }

    public void setFileInfo(String fileInfo) {
        this.fileInfo = fileInfo;
    }

    public String getFileTitle() {
        return fileTitle;
    }

    public void setFileTitle(String fileTitle) {
        this.fileTitle = fileTitle;
    }

    public String getFormulateDate() {
        return formulateDate.split("\\s+")[0];
    }

    public void setFormulateDate(String formulateDate) {
        this.formulateDate = formulateDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIssueDate() {
        return issueDate.split("\\s+")[0];
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getPersonnelInjuryRate() {
        return personnelInjuryRate;
    }

    public void setPersonnelInjuryRate(String personnelInjuryRate) {
        this.personnelInjuryRate = personnelInjuryRate;
    }

    public String getPointName() {
        return pointName;
    }

    public void setPointName(String pointName) {
        this.pointName = pointName;
    }

    public String getPropertyLoss() {
        return propertyLoss;
    }

    public void setPropertyLoss(String propertyLoss) {
        this.propertyLoss = propertyLoss;
    }

    public String getQuestionMark() {
        return questionMark;
    }

    public void setQuestionMark(String questionMark) {
        this.questionMark = questionMark;
    }

    public String getSafetyAccidentCount() {
        return safetyAccidentCount;
    }

    public void setSafetyAccidentCount(String safetyAccidentCount) {
        this.safetyAccidentCount = safetyAccidentCount;
    }

    public boolean isUse() {
        return use;
    }

    public void setUse(boolean use) {
        this.use = use;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }

    public List<KpiInfosBean> getKpiInfos() {
        return kpiInfos;
    }

    public void setKpiInfos(List<KpiInfosBean> kpiInfos) {
        this.kpiInfos = kpiInfos;
    }

    public static class KpiInfosBean extends BaseBean {
        /**
         * beginTime : 1506146961000
         * checkType : 1
         * endTime : 1506146963000
         * id : 129
         * keyId : 45
         * kpiName : zxc
         */

        private long beginTime;
        private String checkType;
        private long endTime;
        private int id;
        private int keyId;
        private String kpiName;

        public long getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(long beginTime) {
            this.beginTime = beginTime;
        }

        public String getCheckType() {
            return checkType;
        }

        public void setCheckType(String checkType) {
            this.checkType = checkType;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getKeyId() {
            return keyId;
        }

        public void setKeyId(int keyId) {
            this.keyId = keyId;
        }

        public String getKpiName() {
            return kpiName;
        }

        public void setKpiName(String kpiName) {
            this.kpiName = kpiName;
        }
    }
}
