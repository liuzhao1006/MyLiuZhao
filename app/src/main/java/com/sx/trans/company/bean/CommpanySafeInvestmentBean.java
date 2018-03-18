package com.sx.trans.company.bean;

import com.sx.trans.base.BaseBean;

/**
 * Created by Administrator on 2017/9/14.
 */

public class CommpanySafeInvestmentBean extends BaseBean {


    /**
     * amount : 5.6
     * createDate : 1484161890000
     * createUser : 549d321508db446e9bcaa477835fe5f1
     * createUserGroupid : 1
     * groupId : 1
     * id : 2
     * isDelete : false
     * isUse : false
     * month : 4
     * purpose : <p>测试</p>
     * ratio : 4.5
     * remark : 测试
     * surplusMoney : 5.5
     * year : 2017
     */

    private double amount;
    private long createDate;
    private String createUser;
    private int createUserGroupid;
    private int groupId;
    private int id;
    private boolean isDelete;
    private boolean isUse;
    private int month;
    private String purpose;
    private double ratio;
    private String remark;
    private double surplusMoney;
    private String year;

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public void setCreateUserGroupid(int createUserGroupid) {
        this.createUserGroupid = createUserGroupid;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    public void setIsUse(boolean isUse) {
        this.isUse = isUse;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setSurplusMoney(double surplusMoney) {
        this.surplusMoney = surplusMoney;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public double getAmount() {
        return amount;
    }

    public long getCreateDate() {
        return createDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public int getCreateUserGroupid() {
        return createUserGroupid;
    }

    public int getGroupId() {
        return groupId;
    }

    public int getId() {
        return id;
    }

    public boolean getIsDelete() {
        return isDelete;
    }

    public boolean getIsUse() {
        return isUse;
    }

    public int getMonth() {
        return month;
    }

    public String getPurpose() {
        return purpose;
    }

    public double getRatio() {
        return ratio;
    }

    public String getRemark() {
        return remark;
    }

    public double getSurplusMoney() {
        return surplusMoney;
    }

    public String getYear() {
        return year;
    }
}
