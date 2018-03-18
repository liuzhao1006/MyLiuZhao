package com.sx.trans.transport.home.bean;

import com.sx.trans.base.BaseBean;

/**
 * 作者 : 刘朝,
 * on 2017/9/15,
 * GitHub : https://github.com/liuzhao1006
 */

public class StopBean extends BaseBean {


    /**
     * alarmDescription : 请尽快向行业资质主管部门申请续期或重新办理
     * createDate : 1505232000000
     * createUser : admin
     * functionParamsValue : 4
     * id : 1
     * lastRemindDate : 1501257600000
     * personLiable :
     * referenceTime : 1501257600000
     * remindCount : 1
     * state : 1
     * targetCode : ZH_AQTX_018
     * targetCompanyId : 21
     */

    private String alarmDescription;
    private long createDate;
    private String createUser;
    private String functionParamsValue;
    private int id;
    private long lastRemindDate;
    private String personLiable;
    private long referenceTime;
    private int remindCount;
    private int state;
    private String targetCode;
    private String targetCompanyId;

    public String getAlarmDescription() {
        return alarmDescription;
    }

    public void setAlarmDescription(String alarmDescription) {
        this.alarmDescription = alarmDescription;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getFunctionParamsValue() {
        return functionParamsValue;
    }

    public void setFunctionParamsValue(String functionParamsValue) {
        this.functionParamsValue = functionParamsValue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getLastRemindDate() {
        return lastRemindDate;
    }

    public void setLastRemindDate(long lastRemindDate) {
        this.lastRemindDate = lastRemindDate;
    }

    public String getPersonLiable() {
        return personLiable;
    }

    public void setPersonLiable(String personLiable) {
        this.personLiable = personLiable;
    }

    public long getReferenceTime() {
        return referenceTime;
    }

    public void setReferenceTime(long referenceTime) {
        this.referenceTime = referenceTime;
    }

    public int getRemindCount() {
        return remindCount;
    }

    public void setRemindCount(int remindCount) {
        this.remindCount = remindCount;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getTargetCode() {
        return targetCode;
    }

    public void setTargetCode(String targetCode) {
        this.targetCode = targetCode;
    }

    public String getTargetCompanyId() {
        return targetCompanyId;
    }

    public void setTargetCompanyId(String targetCompanyId) {
        this.targetCompanyId = targetCompanyId;
    }
}
