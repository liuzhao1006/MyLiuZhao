package com.sx.trans.company.bean;

import com.sx.trans.base.BaseBean;

/**
 * Created by liuchao on 2017/9/25.
 */

public class CommpanyVehicItemBean extends BaseBean {


    @Override
    public String toString() {
        return "CommpanyVehicItemBean{" +
                "accidentReportCount='" + accidentReportCount + '\'' +
                ", accidentreportCountNo='" + accidentreportCountNo + '\'' +
                ", allQuality='" + allQuality + '\'' +
                ", annualValidity='" + annualValidity + '\'' +
                ", checkPerson='" + checkPerson + '\'' +
                ", classLineName='" + classLineName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", defendValTime='" + defendValTime + '\'' +
                ", drivingLicenseAnnualDate='" + drivingLicenseAnnualDate + '\'' +
                ", mileage='" + mileage + '\'' +
                ", shippingExpiryDate='" + shippingExpiryDate + '\'' +
                ", use_year='" + use_year + '\'' +
                ", vehicleNo='" + vehicleNo + '\'' +
                ", vehicleSafeInfo='" + vehicleSafeInfo + '\'' +
                '}';
    }

    /**
     * accidentReportCount : 1
     * accidentreportCountNo : 1
     * allQuality : 12
     * annualValidity : 2017-09-04 22:00:06
     * checkPerson : 12
     * classLineName : ee—gg
     * companyName : 陕西亿程交通信息有限公司
     * defendValTime : 2017-09-04 22:00:22
     * drivingLicenseAnnualDate : 2017-09-04 21:59:39
     * mileage : 154
     * shippingExpiryDate :
     * use_year : 2017-09-04 22:01:07
     * vehicleNo : 陕A22222
     * vehicleSafeInfo : 1
     */

    private String accidentReportCount;
    private String accidentreportCountNo;
    private String allQuality;
    private String annualValidity;
    private String checkPerson;
    private String classLineName;
    private String companyName;
    private String defendValTime;
    private String drivingLicenseAnnualDate;
    private String mileage;
    private String shippingExpiryDate;
    private String use_year;
    private String vehicleNo;
    private String vehicleSafeInfo;

    public String getAccidentReportCount() {
        return accidentReportCount;
    }

    public void setAccidentReportCount(String accidentReportCount) {
        this.accidentReportCount = accidentReportCount;
    }

    public String getAccidentreportCountNo() {
        return accidentreportCountNo;
    }

    public void setAccidentreportCountNo(String accidentreportCountNo) {
        this.accidentreportCountNo = accidentreportCountNo;
    }

    public String getAllQuality() {
        return allQuality;
    }

    public void setAllQuality(String allQuality) {
        this.allQuality = allQuality;
    }

    public String getAnnualValidity() {
        return annualValidity;
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

    public String getDrivingLicenseAnnualDate() {
        return drivingLicenseAnnualDate;
    }

    public void setDrivingLicenseAnnualDate(String drivingLicenseAnnualDate) {
        this.drivingLicenseAnnualDate = drivingLicenseAnnualDate;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getShippingExpiryDate() {
        return shippingExpiryDate;
    }

    public void setShippingExpiryDate(String shippingExpiryDate) {
        this.shippingExpiryDate = shippingExpiryDate;
    }

    public String getUse_year() {
        return use_year;
    }

    public void setUse_year(String use_year) {
        this.use_year = use_year;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getVehicleSafeInfo() {
        return vehicleSafeInfo;
    }

    public void setVehicleSafeInfo(String vehicleSafeInfo) {
        this.vehicleSafeInfo = vehicleSafeInfo;
    }
}
