package com.sx.trans.transport.home.bean;

import com.sx.trans.base.BaseBean;

/**
 * 作者 : 刘朝,
 * on 2017/9/12,
 * GitHub : https://github.com/liuzhao1006
 * 提醒
 */

public class RemberBean extends BaseBean {
    @Override
    public String toString() {
        return "RemberBean{" +
                "alarmDateTime='" + alarmDateTime + '\'' +
                ", alarmDescription='" + alarmDescription + '\'' +
                ", handleOpinion='" + handleOpinion + '\'' +
                ", targetName='" + targetName + '\'' +
                ", personLiable='" + personLiable + '\'' +
                ", handleMessage='" + handleMessage + '\'' +
                ", handleDatetime='" + handleDatetime + '\'' +
                ", id='" + id + '\'' +
                ", state='" + state + '\'' +
                ", remindCount='" + remindCount + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }

    /**
     * alarmDateTime : 2017-10-17 15:34:16
     * alarmDescription : 请尽快前往主管部门申请续期或重新办理
     * handleOpinion :
     * targetName : 准驾证到期预警
     * personLiable : 李云玲
     * handleMessage : 请尽快前往主管部门申请续期或重新办理
     * handleDatetime : 2017-09-28 19:06:12
     * id : 401
     * state : 已提醒
     * remindCount : 4
     * userName : 王毅
     */

    private String alarmDateTime;
    private String alarmDescription;
    private String handleOpinion;
    private String targetName;
    private String personLiable;
    private String handleMessage;
    private String handleDatetime;
    private String id;
    private String state;
    private String remindCount;
    private String userName;

    public String getAlarmDateTime() {
        return alarmDateTime;
    }

    public void setAlarmDateTime(String alarmDateTime) {
        this.alarmDateTime = alarmDateTime;
    }

    public String getAlarmDescription() {
        return alarmDescription;
    }

    public void setAlarmDescription(String alarmDescription) {
        this.alarmDescription = alarmDescription;
    }

    public String getHandleOpinion() {
        return handleOpinion;
    }

    public void setHandleOpinion(String handleOpinion) {
        this.handleOpinion = handleOpinion;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public String getPersonLiable() {
        return personLiable;
    }

    public void setPersonLiable(String personLiable) {
        this.personLiable = personLiable;
    }

    public String getHandleMessage() {
        return handleMessage;
    }

    public void setHandleMessage(String handleMessage) {
        this.handleMessage = handleMessage;
    }

    public String getHandleDatetime() {
        return handleDatetime;
    }

    public void setHandleDatetime(String handleDatetime) {
        this.handleDatetime = handleDatetime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRemindCount() {
        return remindCount;
    }

    public void setRemindCount(String remindCount) {
        this.remindCount = remindCount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
