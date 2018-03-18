package com.sx.trans.transport.dynamicMonitoring.bean;

import com.sx.trans.base.BaseBean;

/**
 * Created by liuchao on 2017/9/26.
 * 企业规章制度
 */

public class ReadPdfBean extends BaseBean {
    @Override
    public String toString() {
        return "ReadPdfBean{" +
                "companyName='" + companyName + '\'' +
                ", documentInfo='" + documentInfo + '\'' +
                ", executeDate='" + executeDate + '\'' +
                ", fileInfo='" + fileInfo + '\'' +
                ", institutionId='" + institutionId + '\'' +
                ", institutionName='" + institutionName + '\'' +
                ", institutionType='" + institutionType + '\'' +
                ", publishDate='" + publishDate + '\'' +
                ", publishPerson='" + publishPerson + '\'' +
                ", seNo='" + seNo + '\'' +
                ", uUseDate='" + uUseDate + '\'' +
                ", uUseMan='" + uUseMan + '\'' +
                ", use=" + use +
                '}';
    }

    /**
     * companyName :
     * documentInfo :
     * executeDate : 2017-09-02 13:13:07
     * fileInfo :
     * institutionId : 7
     * institutionName : test3
     * institutionType : 安全责任体系
     * publishDate : 2017-09-02 13:13:05
     * publishPerson : admin
     * seNo : 6
     * uUseDate : 2017-09-14 11:30:02
     * uUseMan : admin
     * use : false
     */

    private String companyName;
    private String documentInfo;
    private String executeDate;
    private String fileInfo;
    private String institutionId;
    private String institutionName;
    private String institutionType;
    private String publishDate;
    private String publishPerson;
    private String seNo;
    private String uUseDate;
    private String uUseMan;
    private boolean use;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDocumentInfo() {
        return documentInfo;
    }

    public void setDocumentInfo(String documentInfo) {
        this.documentInfo = documentInfo;
    }

    public String getExecuteDate() {
        return executeDate;
    }

    public void setExecuteDate(String executeDate) {
        this.executeDate = executeDate;
    }

    public String getFileInfo() {
        return fileInfo;
    }

    public void setFileInfo(String fileInfo) {
        this.fileInfo = fileInfo;
    }

    public String getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(String institutionId) {
        this.institutionId = institutionId;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getInstitutionType() {
        return institutionType;
    }

    public void setInstitutionType(String institutionType) {
        this.institutionType = institutionType;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getPublishPerson() {
        return publishPerson;
    }

    public void setPublishPerson(String publishPerson) {
        this.publishPerson = publishPerson;
    }

    public String getSeNo() {
        return seNo;
    }

    public void setSeNo(String seNo) {
        this.seNo = seNo;
    }

    public String getUUseDate() {
        return uUseDate;
    }

    public void setUUseDate(String uUseDate) {
        this.uUseDate = uUseDate;
    }

    public String getUUseMan() {
        return uUseMan;
    }

    public void setUUseMan(String uUseMan) {
        this.uUseMan = uUseMan;
    }

    public boolean isUse() {
        return use;
    }

    public void setUse(boolean use) {
        this.use = use;
    }
}
