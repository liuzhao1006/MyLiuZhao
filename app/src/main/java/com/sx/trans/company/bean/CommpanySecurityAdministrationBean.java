package com.sx.trans.company.bean;

import com.sx.trans.base.BaseBean;

/**
 * Created by Administrator on 2017/9/14.
 */

public class CommpanySecurityAdministrationBean extends BaseBean {


    /**
     * createDate : 1504329193000
     * createUser : admin
     * executeDate : 1504329187000
     * groupId : 1
     * id : 7
     * institutionName : test3
     * institutionType : 157
     * isDelete : false
     * isUse : false
     * publishDate : 1504329185000
     * publishPerson : admin
     * remark : aaaa
     * seNo : 6
     * uUseDate : 1505359802000
     * uUseMan : admin
     */

    private long createDate;
    private String createUser;
    private long executeDate;
    private int groupId;
    private int id;
    private String institutionName;
    private int institutionType;
    private boolean isDelete;
    private boolean isUse;
    private long publishDate;
    private String publishPerson;
    private String remark;
    private String seNo;
    private long uUseDate;
    private String uUseMan;

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public void setExecuteDate(long executeDate) {
        this.executeDate = executeDate;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public void setInstitutionType(int institutionType) {
        this.institutionType = institutionType;
    }

    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    public void setIsUse(boolean isUse) {
        this.isUse = isUse;
    }

    public void setPublishDate(long publishDate) {
        this.publishDate = publishDate;
    }

    public void setPublishPerson(String publishPerson) {
        this.publishPerson = publishPerson;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setSeNo(String seNo) {
        this.seNo = seNo;
    }

    public void setUUseDate(long uUseDate) {
        this.uUseDate = uUseDate;
    }

    public void setUUseMan(String uUseMan) {
        this.uUseMan = uUseMan;
    }

    public long getCreateDate() {
        return createDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public long getExecuteDate() {
        return executeDate;
    }

    public int getGroupId() {
        return groupId;
    }

    public int getId() {
        return id;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public int getInstitutionType() {
        return institutionType;
    }

    public boolean getIsDelete() {
        return isDelete;
    }

    public boolean getIsUse() {
        return isUse;
    }

    public long getPublishDate() {
        return publishDate;
    }

    public String getPublishPerson() {
        return publishPerson;
    }

    public String getRemark() {
        return remark;
    }

    public String getSeNo() {
        return seNo;
    }

    public long getUUseDate() {
        return uUseDate;
    }

    public String getUUseMan() {
        return uUseMan;
    }
}
