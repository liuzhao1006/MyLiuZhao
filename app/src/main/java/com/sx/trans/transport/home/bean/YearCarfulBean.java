package com.sx.trans.transport.home.bean;

import com.sx.trans.base.BaseBean;

/**
 * 作者 : 刘朝,
 * on 2017/9/11,
 * GitHub : https://github.com/liuzhao1006
 */

public class YearCarfulBean extends BaseBean {


    /**
     * employmentType : 危货押运员
     * licenseYearDate : 1505120486000
     * licenseYearLimited : 1505120488000
     * certificateNumber : M232121
     * employeeId : 9
     * userName : 押运员2
     * licenseDate : 1505120484000
     */

    private String employmentType;
    private long licenseYearDate;
    private long licenseYearLimited;
    private String certificateNumber;
    private int employeeId;
    private String userName;
    private long licenseDate;

    public String getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    public long getLicenseYearDate() {
        return licenseYearDate;
    }

    public void setLicenseYearDate(long licenseYearDate) {
        this.licenseYearDate = licenseYearDate;
    }

    public long getLicenseYearLimited() {
        return licenseYearLimited;
    }

    public void setLicenseYearLimited(long licenseYearLimited) {
        this.licenseYearLimited = licenseYearLimited;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getLicenseDate() {
        return licenseDate;
    }

    public void setLicenseDate(long licenseDate) {
        this.licenseDate = licenseDate;
    }
}
