package com.sx.trans.transport.me.bean;

import com.sx.trans.base.BaseBean;

/**
 * 第三方:
 * 作者: 刘朝
 * 日期: 2017/10/16
 * 描述: 车辆信息
 */

public class MeVehicleInfoBean extends BaseBean {
    @Override
    public String toString() {
        return "MeVehicleInfoBean{" +
                "allQuality='" + allQuality + '\'' +
                ", annualValidity='" + annualValidity + '\'' +
                ", checkPerson='" + checkPerson + '\'' +
                ", classLineName='" + classLineName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", defendValTime='" + defendValTime + '\'' +
                ", drivingLicenseExpiryDate='" + drivingLicenseExpiryDate + '\'' +
                ", shippingAnnualDate='" + shippingAnnualDate + '\'' +
                ", vehicleNo='" + vehicleNo + '\'' +
                ", vehicleNoColor='" + vehicleNoColor + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", vheicleUseYear='" + vheicleUseYear + '\'' +
                '}';
    }

    /**
     * allQuality : 4569.55
     * annualValidity : 2017-10-21 00:00:00
     * checkPerson : 20
     * classLineName : S—SD
     * companyName : 西安华辰交通设施有限责任公司
     * defendValTime : 2017-03-27 00:00:00
     * drivingLicenseExpiryDate : 2017-06-20 00:00:00
     * shippingAnnualDate : 2018-08-29 00:00:00
     * vehicleNo : 贵HA0222
     * vehicleNoColor : 黄色
     * vehicleType : 中型专项作业车
     * vheicleUseYear : 8
     */

    private String allQuality;
    private String annualValidity;
    private String checkPerson;
    private String classLineName;
    private String companyName;
    private String defendValTime;
    private String drivingLicenseExpiryDate;
    private String shippingAnnualDate;
    private String vehicleNo;
    private String vehicleNoColor;
    private String vehicleType;
    private String vheicleUseYear;

    public String getAllQuality() {
        return allQuality;
    }

    public void setAllQuality(String allQuality) {
        this.allQuality = allQuality;
    }

    public String getAnnualValidity() {
        return annualValidity.split("\\s+")[0];
    }

    public void setAnnualValidity(String annualValidity) {
        this.annualValidity = annualValidity;
    }

    public String getCheckPerson() {
        return checkPerson;
    }

    public void setCheckPerson(String checkPerson) {
        this.checkPerson = checkPerson;
    }

    public String getClassLineName() {
        return classLineName;
    }

    public void setClassLineName(String classLineName) {
        this.classLineName = classLineName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDefendValTime() {
        return defendValTime;
    }

    public void setDefendValTime(String defendValTime) {
        this.defendValTime = defendValTime;
    }

    public String getDrivingLicenseExpiryDate() {
        return drivingLicenseExpiryDate;
    }

    public void setDrivingLicenseExpiryDate(String drivingLicenseExpiryDate) {
        this.drivingLicenseExpiryDate = drivingLicenseExpiryDate;
    }

    public String getShippingAnnualDate() {
        return shippingAnnualDate;
    }

    public void setShippingAnnualDate(String shippingAnnualDate) {
        this.shippingAnnualDate = shippingAnnualDate;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getVehicleNoColor() {
        return vehicleNoColor;
    }

    public void setVehicleNoColor(String vehicleNoColor) {
        this.vehicleNoColor = vehicleNoColor;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVheicleUseYear() {
        return vheicleUseYear;
    }

    public void setVheicleUseYear(String vheicleUseYear) {
        this.vheicleUseYear = vheicleUseYear;
    }
}
