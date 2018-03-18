package com.sx.trans.company.bean;

import com.sx.trans.base.BaseBean;

/**
 * Created by Administrator on 2017/9/13.
 */

public class CommpanyEmployeeInfoBean extends BaseBean {
    @Override
    public String toString() {
        return "CommpanyEmployeeInfoBean{" +
                "accidentAllCount='" + accidentAllCount + '\'' +
                ", accidentCountNo='" + accidentCountNo + '\'' +
                ", age='" + age + '\'' +
                ", carType='" + carType + '\'' +
                ", companyName='" + companyName + '\'' +
                ", driverNumber='" + driverNumber + '\'' +
                ", driverNumberLimited='" + driverNumberLimited + '\'' +
                ", driver_year='" + driver_year + '\'' +
                ", employmentType='" + employmentType + '\'' +
                ", licenseLimited='" + licenseLimited + '\'' +
                ", phone='" + phone + '\'' +
                ", safeInfoCount='" + safeInfoCount + '\'' +
                ", travelLine='" + travelLine + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }

    /**
     * accidentAllCount : 1
     * accidentCountNo : 0
     * age : 13
     * carType :
     * companyName : 河北亿程交通信息有限公司
     * driverNumber :
     * driverNumberLimited :
     * driver_year :
     * employmentType :
     * licenseLimited :
     * phone : 17691006969
     * safeInfoCount : 1
     * travelLine : 西安-宝鸡
     * userName : 李四
     */

    private String accidentAllCount;
    private String accidentCountNo;
    private String age;
    private String carType;
    private String companyName;
    private String driverNumber;
    private String driverNumberLimited;
    private String driver_year;
    private String employmentType;
    private String licenseLimited;
    private String phone;
    private String safeInfoCount;
    private String travelLine;
    private String userName;

    public String getAccidentAllCount() {
        return accidentAllCount;
    }

    public void setAccidentAllCount(String accidentAllCount) {
        this.accidentAllCount = accidentAllCount;
    }

    public String getAccidentCountNo() {
        return accidentCountNo;
    }

    public void setAccidentCountNo(String accidentCountNo) {
        this.accidentCountNo = accidentCountNo;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDriverNumber() {
        return driverNumber;
    }

    public void setDriverNumber(String driverNumber) {
        this.driverNumber = driverNumber;
    }

    public String getDriverNumberLimited() {
        return driverNumberLimited;
    }

    public void setDriverNumberLimited(String driverNumberLimited) {
        this.driverNumberLimited = driverNumberLimited;
    }

    public String getDriver_year() {
        return driver_year;
    }

    public void setDriver_year(String driver_year) {
        this.driver_year = driver_year;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    public String getLicenseLimited() {
        return licenseLimited;
    }

    public void setLicenseLimited(String licenseLimited) {
        this.licenseLimited = licenseLimited;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSafeInfoCount() {
        return safeInfoCount;
    }

    public void setSafeInfoCount(String safeInfoCount) {
        this.safeInfoCount = safeInfoCount;
    }

    public String getTravelLine() {
        return travelLine;
    }

    public void setTravelLine(String travelLine) {
        this.travelLine = travelLine;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
